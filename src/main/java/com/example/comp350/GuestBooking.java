package com.example.comp350;

import java.util.Scanner;
// rooms available (key:number of room, Value: True if its available)
public class GuestBooking {

    public static Map<Integer, Boolean> rooms;

    Static {
        rooms = new Hashmap<>();
        rooms.put(1, true);
        rooms.put(2, true);
        rooms.put(3, true);
        rooms.put(4, true);
        rooms.put(5, true);
        rooms.put(6, true);
        rooms.put(7, true);
        rooms.put(8, true);
        rooms.put(9, true);
    }

    public void bookguest() {
        String name;
        Int room;
        Scanner scan = new Scanner(system.ini)
        System.out.println("what is the guest name?");
        name = scan.nextLine();
        System.out.println();
        // will check what rooms are available ask wich one to book
        room = FillRoom();
        //use info to make guest
        Guest guest = new guest(name, room);
        //put guest in guestitenarary


    }

    // sets up guest account giving name, room, and bill from employee side
    public int fillroom() {
        int room
        for (Map.entry<Integer, boolean> set : rooms.entryset()) {
            if (set.getValue() == true) {
                System.out.println("room " + set.getkey + " is available");
            }

        }
        System.out.println("what room would you like to fill? please enter the room number:")
        room = scan.nextInt();
        for (Map.entry<Integer, boolean> set : rooms.entryset()) {
            if (set.getkey() == room) {
                set.replace(set.getkey, set.getvalue, false);
            }
        }
        return room;
    }
}