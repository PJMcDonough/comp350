package com.example.comp350;
import java.util.*;

public class SpaReservation
{
    public static boolean[] availableTime = new boolean[24]; // 12hrs available = 720 / 30
    private static Scanner scan = new Scanner(System.in);
    private static LinkedList<Reservation> totalRes = new LinkedList<>();
    public static double totalRevenue = 0;

    private static final int OPEN_TIME = 8; // 8 am
    private static final int CLOSE_TIME = 20; // 8pm
    private static final int MAX_TIME_LENGTH = 5;
    private static final int HALF_HOUR = 30;
    private static final int HOUR = 60;
    private static final int HOUR_HALF = 90;

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
        System.out.println("Which type of massage would you like?");
        displaySpecialType(0,3);
        String input = scan.next().toUpperCase(); //Ask for their specific treatment

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
        System.out.println("Which type of facial would you like?");
        displaySpecialType(3,5);
        String input = scan.next().toUpperCase(); //Ask for their specific treatment

        switch (input.toUpperCase())
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
        System.out.println("Which type of special treatment would you like?");
        displaySpecialType(5,SpecialType.values().length);
        String input = scan.next().toUpperCase(); //Ask for their specific treatment

        switch (input.toUpperCase())
        {
            case "HOT STONE": case "SUGAR SCRUB": case "HERBAL BODY WRAP": case "BOTANICAL MUD WRAP":
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
    public static Reservation spaServices(int start,String customerName,String spaTypeInput)
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

        int duration = chooseTimeDuration(spaTypeChoice);

        return new Reservation(start,customerName,null,specialMassage,duration,spa.price);
    }

    /*
        Customers can make their reservation
    */
    public static void makeSpaReservation()
    {
        int appointmentInput = makeAppointment();
        String spaTypeInput = selectingSpaType();

        System.out.println("Please enter your name for the reservation");
        String name = scan.next();

        Reservation newRes = spaServices(appointmentInput,name,spaTypeInput.toUpperCase());
        markTime(appointmentInput,newRes.getTime());
        totalRes.add(newRes);

        makePayment(newRes);
        totalRevenue += newRes.getPrice();
    }


    /*
        start in an introductory UI
        3 options: make reservation, view reservation, cancel,
        have some kind of clicking interface
        if make - > chose from available time (no overlap), maybe linked list
        if view -> display the linked list of reservations
        if cancel -> remove from linked list and add on
    */

    /*public static void main(String[]args)
    {
        System.out.println("Hello, how can we help you");
        switch (scan.next())
        {
            case "MAKE":
                makeSpaReservation();
                break;

            case "VIEW":
                displayReservation();
                break;
            case "CANCEL":
                ///NEEDS WORK!
                //ask for info: number, time, or name
                removeReservation(scan.next());
                break;

            default:
                System.out.println("Sorry something went wrong!");
        }*/

    /*
        Payment system for card payment
    */
    private static void cardPayment(double remainingBalance)
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

        System.out.println("Would you like to save this for future visits?");

        if(scan.next().toLowerCase().equals("yes"))
            //customerFinancialInfo.add(new Banking(cardNum, cardExpMonth, cardExpYear, cvv));

        System.out.printf("Amount paid to the card: %f",remainingBalance);
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
            case "CREDIT CARD": case "DEBIT CARD":
                cardPayment(balance);
                break;

            case "CASH":
               cashPayment(balance);
                break;

            default:
                System.out.println("Sorry, something went wrong?");
                makePayment(appointment);
        }
    }

    /*
        Finds the name from the input, returns null if not found
    */
    private static Reservation findName(String name)
    {
        Reservation result = null;

        for(int i = 0; i < totalRes.size() && result == null; i++)
        {
            if(totalRes.get(i).getName().equals(name))
                result = totalRes.get(i);
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
        for(int i = 0; i < totalRes.size() && result == null; i++)
        {
            Reservation reservation = totalRes.get(i);
            int hour = timeInt[0], min = timeInt[1];

            if(reservation.getStartTime() == hour)
            {
                result = reservation;
                for(int j = 0; j < (min/30); j++) //Marking all as free
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

    /*
        Removes the given reservation from the list
        Note: uses both find's to find it and erase it
    */
    public static void removeReservation(String input)
    {
        System.out.println("Please enter your ID number, your time, or your name");
        Reservation res = findName(input);

        if(res != null || (res = findTime(input)) != null)
        {
            totalRes.remove(res);
            if(totalRevenue > res.getPrice())
                totalRevenue -= res.getPrice();
        }
    }

    /*
        Uses user input to find the spa service, repeats until it finds the spa service
    */
    private static String selectingSpaType() {
        String temp;

        System.out.println("What type of spa would you like?");
        displaySpaType();
        temp = scan.next();

        //if it found a spa type return
        for (SpaType spaType : SpaType.values())
            if (spaType.label.equals(temp.toUpperCase()))
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
    private static int makeAppointment()
    {
        int result = 0;
        String temp;

        do {
            System.out.println("At what time would you like to make your appointment?");
            int[] time = getTime(temp = scan.next());

            if(time == null)
                continue;

            result = time[0] + time[1];

        }while(closedHours(result) || temp.length() > MAX_TIME_LENGTH);

        if(result == OPEN_TIME && temp.endsWith("pm"))
            result += 12;

        return  result;
    }

    /*
       Displays each customer's info
    */
    public static void displayReservation()
     {
         Reservation[] a = (Reservation[]) totalRes.toArray();

         if(a.length == 0)
         {
             System.out.println("Sorry there are no available reservations");
             return;
         }

        for(int i = 0; i < a.length; i++)
        {
            System.out.println("Customer: " + (1+i) );
            System.out.println("Name: " + a[i].getName());
            System.out.println("Price: " + a[i].getPrice());
            System.out.println("Starting Time: " + a[i].getStartTime());
            System.out.println("Duration: " + a[i].getTime());
            System.out.println("\n");
        }
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

            if (!b)
            {
                int j = i /2,k = i % 2;
                int hour = OPEN_TIME + j, min = HALF_HOUR * k;

                if(min == HOUR_HALF) {
                    hour++;
                    min = HALF_HOUR;
                }

                System.out.print(hour + " : " + min);
                System.out.print("\t");
            }

            i++;
        }

        System.out.println();
    }

    /*
        Flips the time from the available time to be free or not
        Reservations to avoid overlap: 1 = free, 0 = N/A
    */
    private static void markTime(int startTime,int duration) {
        //Check if each are available before marking it
        /*for(int i = startTime - OPEN_TIME; i < (duration/30); i++)
            if(availableTime[i])
            {
                System.out.println("Sorry it looks like this time is unavailable");
                return false;
            }
*/
        //Marks all time from startTime to be marked as un/available
        for(int i = startTime - OPEN_TIME; i < (duration/30); i++)
            availableTime[i] = !availableTime[i];
    }

}