package com.example.comp350;

import javafx.application.Application;

import java.util.Scanner;

public class RoomService {

        public double c_Price =25.00;
        public String[] f_menu = {"steaks" ,"burgers", "salads"};
        public boolean cleaning = false;
        public boolean foodservice = false;
        public int roomNumber;
        public boolean ordering;
        public String order;
        public int whatService = 0;
        Scanner scan = new Scanner(System.in);
        public void cleaningbill(){
            System.out.println("what room is this for: ");
            roomNumber = scan.nextInt();
            System.out.println("adds"+ c_Price +"to room #"+roomNumber);
        }
        public void orderfood(){
            System.out.println("what room is this for: ");
            roomNumber = scan.nextInt();
            System.out.println("Menu");
            for(int i = 0; i < f_menu.length; i++){
                System.out.println("     "+f_menu[i]+"     $10");

            }
            while(ordering){
                System.out.println("what would you like: ");
                order = scan.nextLine();
                if(order.equals("quit")) ordering = false;
                else System.out.println(order+" was ordered for 10$");
            }

        }
        public void whichservice(){
            System.out.println("What service would you like(please enter its number):");
            System.out.println("1. ordering food");
            System.out.println("2. cleaning service");
            whatService = scan.nextInt();
            if(whatService == 1) cleaning = true;
            if(whatService == 2) ordering = true;
            if(cleaning) cleaningbill();
            if(foodservice) orderfood();

        }

    }





