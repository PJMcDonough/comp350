import java.util.*;
public class SpaReservation
{
    private enum  userChoice {MAKE,VIEW,CANCEL}
    public static boolean[] availableTime = new boolean[24]; // 12hrs available = 720 / 30

    private static LinkedList<Reservation> totalRes = new LinkedList<Reservation>();
    private double totalRevenue = 0;
    private static final int OPEN_TIME = 8; // 8 am
    private static final int CLOSE_TIME = 20; // 8pm

    public static int chooseTimeDuration(int type)
    {
        Scanner scan = new Scanner(System.in);
        int[] result = {30,60,90}; int input = 0;

        while(input != result[type] && input != result[type + 1])
        {
            try {
                System.out.printf("Would you prefer your reservation to be at %d or %d?", result[type], result[type + 1]);
                input = scan.nextInt();

            }catch (InputMismatchException ime)
            {
                System.out.println("Sorry you didn't select a correct number can you try again");
            }
        }

        return input;
    }

    public static specialType specialityCare(String customerInput)
    {
        if (customerInput.toLowerCase().equals("mineral bath"))
            return null;
        System.out.printf("Which type of %s would you like\n", customerInput.toLowerCase());
        String input = new Scanner(System.in).next(); //Ask for their specific treatment

        switch (input.toUpperCase())
        {
            case "SWEDISH": case "SHIATSU": case "DEEP_TISSUE":
                if(!customerInput.equals("MASSAGE"))
                {
                    System.out.println("Sorry it looks like you choose the wrong option");
                    specialityCare(customerInput); //wrong input, go back
                }
                break;
            case "NORMAL": case "COLLAGEN":
                if(!customerInput.equals("FASCIALS"))
                {
                    System.out.println("Sorry it looks like you choose the wrong option");
                    specialityCare(customerInput); //wrong input, go back
                }
            break;
            case "HOT STONE": case "SUGAR SCRUB": case "HERBAL BODY WRAP": case "BOTANICAL MUD WRAP":
                if(!customerInput.equals("SPECIAL TREATMENT"))
                {
                    System.out.println("Sorry it looks like you choose the wrong option");
                    specialityCare(customerInput); //wrong input, go back
                }
            break;
            default:
                specialityCare(customerInput);
        }

        return specialType.valueOf(input);
    }

    public static Reservation spaServices(int start,String customerName,String spaTypeInput)
    {
        spaType spa = spaType.valueOf(spaTypeInput);// Either massage, facial, special treatment, or bath

        switch (spaTypeInput){ //Ensures that it has a correct input
            case "MASSAGE":case "FACIALS":case "SPECIAL TREATMENT": case "MINERAL BATH":
                break;
            default:
                System.out.println("Sorry something went wrong!");
                System.out.println("Please input your spa type");
                String input = new Scanner(System.in).next();
                spaServices(start,customerName,input);
        }

        int duration = chooseTimeDuration(spa.label.equals("FACIALS") || spa.label.equals("MASSAGE") ? 0 : 1);
        specialType specialMassage = specialityCare(spaTypeInput);

        return new Reservation(start,customerName,specialMassage,duration,spa.price);
    }

    //Reservations to avoid overlap: 1 = free, 0 = N/A

    public static void main(String[]args){

        Scanner scan = new Scanner(System.in);

        //Start in an introductory UI
        //3 options: make reservation, view reservation, cancel,
        //have some kind of clicking interface
        // if make - > chose from available time (no overlap), maybe linked list
        // if view -> display the linked list of reservations
        // if cancel -> remove from linked list and add on
        System.out.println("Hello, how can we help you");
        switch (scan.next())
        {
            case "MAKE":
                int appointmentInput = makeAppointment();
                String spaTypeInput = selectingSpaType();

                System.out.println("Please enter your name for the reservation");
                String name = scan.next();

                Reservation newRes = spaServices(appointmentInput,name,spaTypeInput);
                markTime(appointmentInput,newRes.getTime());
                totalRes.add(newRes);
                break;

            case "VIEW":
                displayReservation((Reservation[]) totalRes.toArray());
                break;
            case "CANCEL":
                ///NEEDS WORK!
                //ask for info: number, time, or name
                System.out.println("Please enter your ID number, your time, or your name");
                removeReservation(scan.next());
                break;

            default:
                System.out.println("Sorry something went wrong!");
        }
    }

    public static void removeReservation(String input)
    {
        Reservation[] arr = (Reservation[]) totalRes.toArray();
        for(int i = 0; i < arr.length; i++)
        {
            if (arr[i].getName().equals(input)) // if a name
            {
                arr[i] = null;
                break;
            }

            // a number
            if(!input.contains(":"))
            {
                System.out.println("Sorry we didn't find a time or a name that you requested.");
                return;
            }

            input.split(" : ");
            Scanner scanner = new Scanner(input);

            if (!scanner.hasNextInt()) {
                System.out.println("Sorry we didn't detect any number associated with time.");
                return;
            }

            int hour = scanner.nextInt(), min = scanner.hasNextInt() ? scanner.nextInt() : 0;

            // 1st want to find and remove it and set available time to false
            if(arr[i].getStartTime() == hour)
            {
                if(arr[i].getTime() == min)
                {
                    arr[i] = null;
                    break;

                }
            }
        }
    }

    private static String selectingSpaType() {
        String temp;
        Scanner scan = new Scanner(System.in);

        System.out.println("What type of spa would you like?");
        temp = scan.next();

        //if it found a spa type return
        for (spaType spaType : spaType.values())
            if (spaType.label.equals(temp))
                return temp;

        // if not correct, return with recursion
        System.out.println("Sorry your spa request is invalid!\n");
        return selectingSpaType();
    }

    private static int makeAppointment()
    {
        int result;
        String temp;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("At what time would you like to make your appointment?");
            temp = scan.next();
            //how to separate ':' to just get hr and min
            result = temp.charAt(0);

        }while(result < SpaReservation.OPEN_TIME || result > SpaReservation.CLOSE_TIME || temp.length() > 5);

        if(temp.endsWith("pm"))
            result += 12;

        return  result;
    }

    private static void displayReservation(Reservation[] a)
    {
        if(a == null)
            return;

        for(int i = 0; i < a.length; i++)
        {
            System.out.println("Customer: " + (1+i) );
            System.out.println("Name: " + a[i].getName());
            System.out.println("Price: " + a[i].getPrice());
            System.out.println("Starting Time: " + a[i].getStartTime());
            System.out.println("Duration: " + a[i].getTime());
            System.out.println("\n");
        }

        if(a.length == 0)
            System.out.println("Sorry there are no available reservations");
    }

    private static void markTime(int startTime,int duration) {
        //Marks all time from startTime to be unavailable
        for(int i = startTime - OPEN_TIME; i < (duration/30); i++)
            availableTime[i] = true;
    }

}