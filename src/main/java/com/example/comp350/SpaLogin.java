package com.example.comp350;
import java.util.*;
public class SpaLogin
{
    public static Scanner scan = new Scanner(System.in);


    /*
        More or less some kind of login page for the customer or for the employee/manager
        Option 1: Person is CUSTOMER
        Option 2: Person is EMPLOYEE or MANAGER
    */
    public static void main(String[] args)
    {
        System.out.println("Hello and Welcome!");
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

    /*
        Managers and Employees would have access to make changes as they see fit
        Note: they would have to through security first
    */
    private static void staffManagement() {
        security();

        System.out.println("What would you like to do?");
        switch (scan.next().toUpperCase())
        {
            case "REMOVE":
                SpaReservation.removeReservation(scan.next());
            break;
            case "REVENUE":
                SpaReservation.displayReservation();
                System.out.println("Total revenue today: " + SpaReservation.totalRevenue);

        }
    }

    /*
        Security would ask them to enter their user name and password
        Note: they only have 10 attempts for security measure
    */
    private static void security()
    {
        String user, password; // user and password are not set
        int attempts = 0;
        while(attempts < 10)
        {
            System.out.println("Please enter your username");
            user = scan.next();

            System.out.println("Please enter your password");
            password = scan.next();

            if(user.equals("user") && password.equals("password"))
            {
                System.out.println("Access Granted");
                return;
            }

            System.out.println("Sorry it looks like your username or password is incorrect");
            attempts++;
        }

        //Something should happen after 10 attempts
        System.out.println("Sorry you have tried too many times!");

    }
}
