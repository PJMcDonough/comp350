import java.util.*;
public class SpaReservation
{
    private enum  userChoice {MAKE,VIEW,CANCEL}
    public static boolean[] availableTime = new boolean[24]; // 12hrs available = 720 / 30

    private double totalRevenue = 0;
    private static final int START_TIME = 8; // 8 am
    private static final int END_TIME = 20; // 8pm
    //Check if valid before and the end
            /*for(int i = 0; i < chooseTimeDuration/30; i++)
            {
                SpaReservation.availableTime[START_TIME + i] = true;
            }*/


    public static int chooseTimeDuration(int type)
    {
        Scanner scan = new Scanner(System.in);
        int[] result = {30,60,90};

        boolean valid = false;
        int input = 0;
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

    public static Reservation spaServices(int start,String spaTypeInput)
    {
        int duration = 0;
        double price = 0;
        String input;
        specialType specialMassage = null;

        spaTypeInput = spaTypeInput.toUpperCase();
        switch (spaTypeInput)
        {
            case "MASSAGE":
                price = spaType.MASSAGE.price;
                duration = chooseTimeDuration(0);

                System.out.println("Which type of massage would you like");
                input = new Scanner(System.in).next();
                specialMassage = specialType.valueOf(input);

                break;
            case "FACIALS":
                price = spaType.FACIALS.price;
                duration = chooseTimeDuration(0);

                System.out.println("Which type of facials would you like");
                input = new Scanner(System.in).next();
                specialMassage = specialType.valueOf(input);

                break;
            case "SPECIAL TREATMENT":
                price = spaType.SPECIAL_TREATMENT.price;
                duration = chooseTimeDuration(1);

                System.out.println("Which type of special treatment would you like");
                input = new Scanner(System.in).next();
                specialMassage = specialType.valueOf(input);

                break;
            case "MINERAL BATH":
                price = spaType.MINERAL_BATH.price;
                duration = chooseTimeDuration(1);
        }

        return new Reservation(start,specialMassage,duration,price,start + (duration/60));
    }

    //Reservations to avoid overlap: 1 = free, 0 = N/A

    public static void main(String[]args){

        LinkedList<Reservation> totalRes = new LinkedList<Reservation>();
        Scanner scan = new Scanner(System.in);

        //Start in an introductory UI
        //3 options: make reservation, view reservation, cancel,
        System.out.println("Hello, how can we help you");

        //have some kind of clicking interface
        // if make - > chose from available time (no overlap), maybe linked list
        // if view -> display the linked list of reservations
        // if cancel -> remove from linked list and add on

        switch (userChoice)
        {
            case MAKE:
                int input;
                String temp;
                do {
                    System.out.println("At what time would you like to make your appointment?");
                    temp = scan.next();
                    input = temp.charAt(0);

                }while(input < SpaReservation.START_TIME || input > SpaReservation.END_TIME || temp.length() > 5);

                if(temp.endsWith("pm"))
                    input += 12;

                do {
                    System.out.println("What type of spa would you like ?");
                    temp = scan.next();

                }while(!temp.equals(spaType.FACIALS.label) && !temp.equals(spaType.MASSAGE.label) &&
                    !temp.equals(spaType.MINERAL_BATH.label) && !temp.equals(spaType.SPECIAL_TREATMENT.label));

                totalRes.add(spaServices(input,temp));


                break;
            case VIEW:
                Object arr[] = totalRes.toArray();

                for(int i = 0; i < arr.length; i++)
                {

                    System.out.println();
                }
                break;
            case CANCEL:
                //ask for info: number, time, or name
                //if they have id
                //totalRes.remove(num,)

                totalRes.remove();
        }
    }

}