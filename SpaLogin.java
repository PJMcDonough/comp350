import java.util.*;
public class SpaLogin {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Hello and Welcome!");
        //Option 1: Person is CUSTOMER
        //Option 2: Person is EMPLOYEE or MANAGER

        String response = scan.next().toUpperCase();

        switch (response)
        {
            case "CUSTOMER":
                SpaReservation.main(new String[]{""});
            break;
            case "EMPLOYEE": case "MANAGER":
                staffManagement();
        }
    }

    private static void staffManagement() {
        security();

        System.out.println("What would you like to do?");
        switch (scan.next().toUpperCase())
        {
            case "REMOVE":
                SpaReservation.removeReservation(scan.next());
        }

    }

    private static void security()
    {
        String user, password; // user and password are not set
        int attempts = 0;
        do{
            System.out.println("Please enter your username");
            user = scan.next();

            if(user.equals("user"))
            {
                System.out.println("Please enter your password");
                password = scan.next();

                if(!password.equals("password")) {
                    System.out.println("Sorry it looks like your password is incorrect");
                    attempts++;
                }
            }else
                System.out.println("Sorry it looks like your user doesn't match with our record");

        }while(attempts < 10);
        //Something should happen after 10 attempts
    }
}
