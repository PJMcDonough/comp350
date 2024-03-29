package com.example.comp350;
import java.sql.SQLException;
import java.util.*;
public class SpaReservation
{
    private static final int TIMES_OPEN = 25; //1 = 30 mins, 25 = 12hrs
    private static final int OPEN_TIME = 8; // 8 am
    private static final int CLOSE_TIME = 20; // 8pm
    private static final int MAX_TIME_LENGTH = 5;
    private static final int HALF_HOUR = 30;
    private static final int HOUR = 60;

    public static boolean[] availableTime = new boolean[TIMES_OPEN]; // 12hrs available
    private static Scanner scan = new Scanner(System.in);
    private static LinkedList<Reservation> totalReservation = new LinkedList<>();
    public static double totalRevenue = 0;
    public static boolean managerMode = false;


    /*
        Made considerable changes for the JavaFx usage, most would not run on JavaFx page
    */

    private static boolean closedHours(int result)
    {
        return result < OPEN_TIME || result > CLOSE_TIME;
    }

    /*
        Customers can choose which spa service they can choose from the 4 (HIGHLIGHT: For JavaFx)
    */
    public static Reservation spaServices(double start,String customerName,String spaTypeInput,String specificTypeInput,double duration) {
        // Either massage, facial, special treatment, or bath
        if(spaTypeInput == null)
        {
            System.out.println("Sorry looks like the spaTypeInput is null");
            return null;
        }

        if(specificTypeInput == null)
        {
            System.out.println("Sorry looks like the specificInput is null");
            return null;
        }

        if(customerName == null)
        {
            System.out.println("Sorry looks like the custerName is null");
            return null;
        }

        SpaType spa = SpaType.valueOf(spaTypeInput);
        SpecialType specialMassage = SpecialType.valueOf(specificTypeInput);

        try {
            //set the string name to the user name

            //adds a new reservation into the database
            new SpaReservationSQL().getInsertionCustomerOp(customerName, " ", start, start + duration);
        }catch (Exception e)
        {
            System.out.println("Sorry couldn't add to database");
        }

        return new Reservation(start,customerName,spa,null,specialMassage, (int) duration,spa.price);
    }

    /*
        Customers can add reservation or manager can manipulate to add reservations (Terminal Use)
    */
    public static Reservation addReservation(double appointment, String spaType,String name) {
        //Manager can try to add here
        //Note: start time = 3.5 => 3:30  &  duration = 30 => half an hour
        Reservation newRes = spaServices(appointment,name,spaType,"",0.0);
        assert newRes != null;
        markTime(appointment,newRes.getTime());
        totalReservation.add(newRes);
        makePayment(newRes);
        totalRevenue += newRes.getPrice();

        //Managers can force edits
        if(!checkTime(newRes.getStartTime(),newRes.getTime()))
            removeReservation(newRes);

        return newRes;
    }

    private static int[] cardInfo()
    {
        System.out.print("Card Number: ");
        int cardNum = scan.nextInt();
        System.out.println();

        System.out.println("Card Expiration");
        System.out.print("Month: ");
        int cardExpMonth = scan.nextInt();
        System.out.print("\t");
        System.out.print("Year: ");
        int cardExpYear = scan.nextInt();
        System.out.println();

        System.out.print("CVV: ");
        int cvv = scan.nextInt();
        System.out.println();

        return new int[]{cardNum,cardExpMonth,cardExpYear,cvv};
    }

    /*
        Payment system for card payment (Terminal Use)
    */
    private static void cardPayment(double remainingBalance,int index)
    {
        int[] card = cardInfo();
        System.out.println("Would you like to save this for future visits?");

        if(scan.next().toLowerCase().equals("yes"))
            totalReservation.get(index).setBank(new Banking(card[0], card[1], card[2], card[3]));

        System.out.printf("Amount paid to the card: %f\n\n",remainingBalance);
    }

    /*
        Payment system for cash payment
        Note: decreases over the remaining balance (Terminal Use)
    */
    private static void cashPayment(double remainingBalance)
    {
        while(remainingBalance > 0.0)
        {
            System.out.print("Please input each bill one at a time\t");
            System.out.printf("Remaining Balance: $%.2f\n",remainingBalance);
            remainingBalance -= scan.nextInt();
        }

        if (remainingBalance < 0.0) //give them back change
            System.out.printf("Here is your change: $%.2f\n", Math.abs(remainingBalance));
    }

    /*
        Customers can choose which form of payment (Terminal Use)
    */
    private static void makePayment(Reservation appointment) {

        //Balance = price for each min
        double balance = appointment.getPrice() * appointment.getTime();

        //Ask for credit/debit card info, or money
        System.out.println("How would you like to pay?");

        switch (scan.next().toUpperCase())
        {
            case "CREDIT CARD": case "DEBIT CARD": //NEEDS WORK!
                cardPayment(balance, totalReservation.size());
                break;

            case "CARD":
                System.out.println("We saw you chose 'card', which card exactly");
                System.out.print("CREDIT or DEBIT");
                switch(scan.next().toUpperCase())
                {
                    case "CREDIT": case "DEBIT":
                    cardPayment(balance, totalReservation.size());
                    default:
                    System.out.println("Sorry, something went wrong?\n");
                    makePayment(appointment);
                }

            case "CASH":
               cashPayment(balance);
                break;

            default:
                System.out.println("Sorry, something went wrong?\n");
                makePayment(appointment);
        }
    }

    /*
        Finds the name from the input, returns null if not found
    */
    private static Reservation findName(String name)
    {
        Reservation result = null;

        for(int i = 0; i < totalReservation.size() && result == null; i++)
        {
            if(totalReservation.get(i).getName().equals(name))
                result = totalReservation.get(i);
        }

        return result;
    }

    /*
        Finds the time from the input, returns null if not found
        Note: uses getTime() to find the hr and min
    */
    private static Reservation findTime(String time)
    {
        Reservation result = null;
        double timeInt = getTime(time);

        if(timeInt < 0)
            return null;

        // 1st want to find and remove it and set available time to false
        for(int i = 0; i < totalReservation.size() && result == null; i++)
        {
            Reservation reservation = totalReservation.get(i);
            int hour = (int) timeInt; double min = timeInt - hour;

            if(reservation.getStartTime() == hour)
            {
                result = reservation;
            }
        }

        return result;
    }

    /*
        Gets the time from the input, returns null if input has no number
        Note: uses split() to find the hour and min
    */
    public static double getTime(String string)
    {
        if (!Character.isDigit(string.charAt(0)))
            return -1.0;

        String[] time = string.split(":");
        String[] subTime = time[1].split(" ");
        int hour; double min;

        try {
            hour = Integer.parseInt(time[0]);
            min = (double) Integer.parseInt(subTime[0]) / HOUR;
        }catch (NumberFormatException nfee)
        {
            System.out.println("Sorry we didn't detect any number associated with time.");
            return -1.0;
        }

        //if after noon, add 12 hours more
        if (subTime[1].equals("PM") || subTime[1].equals("pm"))
            hour += 12;

        if (closedHours(hour))
        {
            System.out.println("Sorry we didn't detect a valid hour.");
            return -1.0;
        }

        return hour + min;
    }

    private static void decrementRevenue(Reservation res)
    {
        if(totalRevenue > res.getPrice())
            totalRevenue -= res.getPrice();
    }

    /*
        Only managers, can forcefully remove an entry in the total reservation
    */
    public static void removeReservation(Reservation reservation)
    {
        if(reservation == null) {
            System.out.println("Sorry no access");
            return;
        }

        decrementRevenue(reservation);
        totalReservation.remove(reservation);
    }

    /*
        Removes the given reservation from the list
        Note: uses both find's to find it and erase it
    */
    public static Reservation removeReservation(String input)
    {
        Reservation res = null;

        try {
            if ((res = findName(input)) != null)
                new SpaReservationSQL().getOperationName("REMOVE CUSTOMER", res.getName());

            if (res == null && (res = findTime(input)) != null)
                new SpaReservationSQL().getOperationTime("REMOVE", res.getStartTime());
        }catch (Exception e)
        {
            System.out.println("Sorry, could not work");
        }

        if (res == null) // if none found do nothing
            return null;

        totalReservation.remove(res);
        decrementRevenue(res);

        return res;
    }

    /*
        Uses user input to find the spa service, repeats until it finds the spa service (Terminal Use)
    */
    private static String selectingSpaType()
    {
        String temp = customerResponse("spas").toUpperCase();

        //if it found a spa type return
        for (SpaType spaType : SpaType.values())
            if (spaType.label.equals(temp))
                return temp;

        // if not correct, return with recursion
        System.out.println("Sorry your spa request is invalid!\n");
        return selectingSpaType();
    }

    /*
        Asks the customer what time they would like to start their appointment (Terminal Use)
    */
    private static double makeAppointment()
    {
        double time = 0;
        String temp;

        do {
            temp = customerResponse("time");
            time = getTime(temp);

        }while(closedHours((int)time) || temp.length() > MAX_TIME_LENGTH);

        return  time;
    }

    /*
       Displays each customer's info
    */
    public static void displayReservation()
    {
        try{
            new SpaReservationSQL().getViewOperation("NAME");
        }catch (ClassCastException cce)
        {
            System.out.println("Sorry there are no available reservations");
            System.out.println("\n");
        }catch (SQLException se){
            System.out.println("Sorry there are no available reservations in the database");
            System.out.println("\n");
        }
    }

    /*
       Displays specified customer's info
    */
    public static String displayReservation(Reservation reservation)
    {
        return //"Customer: " +
                "Name: " + reservation.getName() + "\n" +
                "Price: " + reservation.getPrice() + "\n" +
                "Spa: " + reservation.getSpa() + "\n" +
                "Special: " +reservation.getSpecial() + "\n" +
                "Starting Time: " + reservation.getStartTime() + "\n" +
                "Duration: " + reservation.getTime() + "\n\n";

    }

    private static String customerResponse(String input)
    {

        System.out.printf("All available %s:\n",input);

        switch (input.toLowerCase())
        {
            case "spas":
                displaySpaType();
                break;
            case "time":
                displayAvailableTime();
                 break;
            case "massage specials":
                displaySpecialType(0,3);
                break;
            case "facial specials":
                displaySpecialType(3,5);
                break;
            case "special treatment specials":
                displaySpecialType(5,SpecialType.values().length);
                break;
        }

        if(input.equals("time"))
        {
            System.out.println();
            System.out.println("At what time would you like to make your appointment?");
            return scan.next();
        }

        System.out.println();
        System.out.printf("Which type of %s would you like?\n",input);
        System.out.println();

        return scan.next();
    }

    private static String comma(int i, int j)
    {
        return (i == j ? " " : ", ");
    }

    /*
        Displays all spa type
    */
    private static void displaySpaType()
    {
        int i = 0;
        for (SpaType spaType : SpaType.values()) {
            System.out.print(spaType.label + comma(i,SpaType.values().length - 1));
            i++;
        }
        System.out.println();
    }

    private static void displaySpecialType(int start,int end) {
        for(;start < end; start++)
        {
            SpecialType[] type = SpecialType.values();
            System.out.print(type[start] + comma(start,end - 1));
        }
        System.out.println();
    }

    /*
        Displays all available time
    */
    private static void displayAvailableTime()
    {
        int i = 0;
        for (boolean b : availableTime)
        {
            if((i + 1) % 11 == 0) // add some indention
                System.out.println();

            if (b) {
                i++;
                continue;
            }

            // Only displays all available
            int j = i /2;
            int hour = OPEN_TIME + j, min = i % 2;

            System.out.printf("%d:%s" ,hour,min == 0 ? "00":"30");
            System.out.print("\t");

            i++;
        }

        System.out.println();
    }

    /*
        Flips the time from the available time to be free or not
        Reservations to avoid overlap: 1 = free, 0 = N/A
    */
    private static void markTime(double startTime,int duration) {
        //Check if each are available before marking it
        if(!checkTime(startTime,duration))
            return;

        int i = (int) (startTime - OPEN_TIME) * 2,
            j = duration/HALF_HOUR;

        //Marks all time from startTime to be marked as un/available
        for(; i < j; i++)
            availableTime[i] = !availableTime[i];
    }

    /*
        Checks to see if the time is available,
        True = it's available, False = not available
    */
    private static boolean checkTime(double startTime, int duration)
    {
        boolean result = true; //Initially available
        int i = (int) (startTime - OPEN_TIME) * 2,
            j = duration/HALF_HOUR;

        for(; i < j && result; i++)
            if(result = availableTime[i]) //Switches to unavailable if found
            {
                if(!managerMode) //Display warning to customer only
                    System.out.println("Sorry it looks like this time is unavailable");
            }
        return result;
    }
}
