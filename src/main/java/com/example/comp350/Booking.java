package com.example.comp350;

import java.util.Scanner;
import java.util.Random;

public class Booking {
    //limits only five guest per a room;
public String[] guestNames = {null, null, null, null, null};
public int guestNumber;
Scanner scan = new Scanner(System.in);
Random ran = new Random();
public int roomtype = 0;
public int roomNumber = 0;



    public void book(){
        System.out.println("how many guests will be staying:");
        guestNumber = scan.nextInt();
        for(int i = 0; i < guestNumber; i++) {
            System.out.println("please enter the guest's name: ");
            guestNames[i] = scan.nextLine();
            System.out.println(guestNames[i]+"recorded");
        }
        System.out.println("what kind of room would you like:");
        System.out.println("1. single queen bed");
        System.out.println("2. double queen bed");
        roomtype = scan.nextInt();
        roomNumber = assignroom();
        System.out.println(returnRoomType(roomtype)+" room "+roomNumber+ " assigned");


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
