package com.example.comp350;

import java.util.Scanner;
import java.util.Random;

public class Booking {
    //limits only five guest per a room;
    public String[] guestNames = {null, null, null, null, null};
    public int guestNumber;
    Scanner scan = new Scanner(System.in);
    Random ran = new Random();
    private static SpaReservationSQL database;
    public int roomType = 0;
    public int roomNumber = 0;

    private final int MAX_GUESTS = 5;


    private void seriesOfGuests()
    {
        /*//Check if the number of guest is under max guests?
        if(guestNumber < MAX_GUESTS)
        {
            System.out.println("Sorry too many guests");
            return;
        }*/

        for(int i = 0; i < guestNumber; i++) {
            System.out.println("please enter the guest's name: ");
            guestNames[i] = scan.nextLine();
            System.out.println(guestNames[i]+" recorded");
        }
    }

    public void book(){
        System.out.println("how many guests will be staying:");
        guestNumber = scan.nextInt();
        seriesOfGuests();

        System.out.println("what kind of room would you like:");
        System.out.println("\t1. single queen bed");
        System.out.println("\t2. double queen bed");
        roomType = scan.nextInt();
        roomNumber = assignroom();
        System.out.println(returnRoomType(roomType)+" room "+roomNumber+ " assigned");
    }


    public String returnRoomType(int roomtype){
        if(roomtype == 1) return "single queen bed";

        if(roomtype == 2) return "double queen bed";
        return null;
    }
    // will need to check database for room number not taken during visiting dates
    // assigns random number until hooked up to database
    public int assignroom(){


        return ran.nextInt(10);
    }
}
