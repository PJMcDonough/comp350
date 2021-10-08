package com.example.comp350;
import java.util.*;
//import java.sql.*;//FOR SQL STUFF?
public class SpaReservation
{
    private static final int TIMES_OPEN = 25; //1 = 30 mins, 25 = 12hrs
    private static final int OPEN_TIME = 8; // 8 am
    private static final int CLOSE_TIME = 20; // 8pm
    private static final int MAX_TIME_LENGTH = 5;
    private static final int HALF_HOUR = 30;
    private static final int HOUR = 60;
    private static final int HOUR_HALF = 90;

    public static boolean[] availableTime = new boolean[TIMES_OPEN]; // 12hrs available
    private static Scanner scan = new Scanner(System.in);
    private static LinkedList<Reservation> totalReservation = new LinkedList<>();
    //private static SpaReservationSQL database = new SpaReservationSQL();
    public static double totalRevenue = 0;

    /*
        Customers can choose a time duration from 3 options: 30 ,60, 90 mins
    */
    private static int chooseTimeDuration(boolean userChoiceType)
    {
        int input = 0;

        int choose1 = userChoiceType ? HALF_HOUR : HOUR;
        int choose2 = userChoiceType ? HOUR : HOUR_HALF;

        while(input != choose1 && input != choose2)
        {
            try {
                System.out.printf("Would you prefer your reservation to be at %d or %d?\n", choose1,choose2);
                input = scan.nextInt();

            }catch (InputMismatchException ime)
            {
                System.out.println("Sorry you didn't select a correct number can you try again");
            }
        }

        return input;
    }

    /*
       Customers can choose which type of a spa treatment they can choose
       Note: they have to choose the right spa service before choosing the treatment
   */
    private static SpecialType specialMassageCare()
    {
        //Ask for their specific treatment
        String input = customerResponse("massage specials").toUpperCase();

        switch (input)
        {
            case "SWEDISH": case "SHIATSU": case "DEEP_TISSUE":
            break;
            default:
                System.out.println("Sorry, there is no special care!");
                specialMassageCare();
        }

        return SpecialType.valueOf(input);
    }

    private static SpecialType specialFacialCare()
    {
        //Ask for their specific treatment
        String input = customerResponse("facial specials").toUpperCase();

        switch (input)
        {
            case "NORMAL": case "COLLAGEN":
            break;

            default:
                System.out.println("Sorry it looks like you choose the wrong option");
                specialFacialCare(); //wrong input, go back
        }

        return SpecialType.valueOf(input);
    }

    private static SpecialType specialTreatmentCare()
    {
        //Ask for their specific treatment
        String input = customerResponse("special treatment specials").toUpperCase();

        switch (input.toUpperCase())
        {
            case "HOT STONE": case "SUGAR SCRUB":
                case "HERBAL BODY WRAP": case "BOTANICAL MUD WRAP":
            break;

            default:
                System.out.println("Sorry it looks like you choose the wrong option");
                specialTreatmentCare();
        }

        return SpecialType.valueOf(input);
    }

    /*
        Customers can choose which spa service they can choose from the 4
    */
    private static Reservation spaServices(double start,String customerName,String spaTypeInput)
    {
        // Either massage, facial, special treatment, or bath
        SpaType spa = SpaType.valueOf(spaTypeInput);
        boolean spaTypeChoice = true;
        SpecialType specialMassage = null;

        switch (spaTypeInput)
        {
            case "MASSAGE":
                specialMassage = specialMassageCare();
                break;

            case "FACIALS":
                specialMassage = specialFacialCare();
                break;

            case "SPECIAL TREATMENT":
                specialMassage = specialTreatmentCare();

            case "MINERAL BATH":
                spaTypeChoice = false;
                break;

            default:
                System.out.println("Sorry something went wrong!");
                System.out.println("Please input your spa type");
                spaServices(start,customerName, scan.next() );
        }

        System.out.println();
        int duration = chooseTimeDuration(spaTypeChoice);

        return new Reservation(start,customerName,SpaType.valueOf(spaTypeInput),null,specialMassage,duration,spa.price);
    }

    /*
        Customers can add reservation or manager can manipulate to add reservations
    */
    public static void addReservation(double appointment, String spaType,String name,boolean managerMode)
    {
        //Manager can try to add here
        //Note: start time = 3.5 => 3:30  &  duration = 30 => half an hour
        Reservation newRes = spaServices(appointment,name,spaType);
        markTime(appointment,newRes.getTime());
        totalReservation.add(newRes);
        makePayment(newRes);
        totalRevenue += newRes.getPrice();

        //Managers can force edits
        if(!checkTime(newRes.getStartTime(),newRes.getTime(),managerMode))
            removeReservation(newRes);

        //database
    }

    /*
        Customers can make their reservation
    */
    public static void makeSpaReservation()
    {
        double appointmentInput = makeAppointment();
        String spaTypeInput = selectingSpaType();

        System.out.println("Please enter your name for the reservation");
        String name = scan.next();

        //Customer wants to make the spa reservation
       addReservation(appointmentInput,spaTypeInput.toUpperCase(),name,false);
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
        Payment system for card payment
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
        Note: decreases over the remaining balance
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
        Customers can choose which form of payment
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
        int[] timeInt = getTime(time);

        if(timeInt == null)
            return null;

        // 1st want to find and remove it and set available time to false
        for(int i = 0; i < totalReservation.size() && result == null; i++)
        {
            Reservation reservation = totalReservation.get(i);
            int hour = timeInt[0], min = timeInt[1];

            if(reservation.getStartTime() == hour)
            {
                result = reservation;
                for(int j = 0; j < (min/HALF_HOUR); j++) //Marking all as free
                    availableTime[hour - OPEN_TIME + j] = false;
            }
        }

        return result;
    }

    /*
        Gets the time from the input, returns null if input has no number
        Note: uses split() to find the hour and min
    */
    private static int[] getTime(String string)
    {
        String[] time = string.split(":");
        int hour,min;

        try {
            hour = Integer.parseInt(time[0]);
            min = Integer.parseInt(time[1]);
        }catch (NumberFormatException nfee)
        {
            System.out.println("Sorry we didn't detect any number associated with time.");
            return null;
        }

        if (closedHours(hour))
        {
            System.out.println("Sorry we didn't detect a valid hour.");
            return null;
        }

        return new int[]{hour, min};
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
    public static void removeReservation()
    {
        String input;

        if(totalReservation.isEmpty()) //Send customers to make a reservation if there are no reservations
        {
            System.out.println("Sorry it looks like there are no reservations saved.");
            System.out.println("Would you like to make a reservation?");
            if(scan.next().toUpperCase().equals("YES"))
                makeSpaReservation();

            return;
        }

        System.out.println("Please enter your time, or your name"); // your ID number,
        Reservation res;
        if((res = findName(input = scan.next())) != null
                || (res = findTime(input)) != null)
        {
            decrementRevenue(res);
            totalReservation.remove(res);
        }
    }

    /*
        Uses user input to find the spa service, repeats until it finds the spa service
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


    private static boolean closedHours(int result)
    {
        return result < OPEN_TIME || result > CLOSE_TIME;
    }

    /*
        Asks the customer what time they would like to start their appointment
    */
    private static double makeAppointment()
    {
        double result = 0;
        String temp;

        do {
            temp = customerResponse("time");
            int[] time = getTime(temp);

            if(time == null)
                continue;

            result = time[0] + (double) (time[1]/HALF_HOUR);

        }while(closedHours((int)result) || temp.length() > MAX_TIME_LENGTH);

        if(result == OPEN_TIME && temp.endsWith("pm"))
            result += 12;

        return  result;
    }

    /*
       Displays each customer's info
    */
    public static void displayReservation()
     {
         try{
             Reservation[] a = (Reservation[]) totalReservation.toArray();

            for(int i = 0; i < a.length; i++)
            {
                System.out.println("Customer: " + (1+i) );
                System.out.println("Name: " + a[i].getName());
                System.out.println("Price: " + a[i].getPrice());
                System.out.println("Spa: " + a[i].getSpa());
                System.out.println("Special: " + a[i].getSpecial().label);
                System.out.println("Starting Time: " + a[i].getStartTime());
                System.out.println("Duration: " + a[i].getTime());
                System.out.println("\n");
            }
         }catch (ClassCastException cce)
         {
             System.out.println("Sorry there are no available reservations");
             System.out.println("\n");
         }

         //Reading from database
         //database
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

            /*if(min > 0) {
                hour++;
                min = HALF_HOUR;
            }*/

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
        if(!checkTime(startTime,duration,false))
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
    private static boolean checkTime(double startTime, int duration,boolean managerMode)
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