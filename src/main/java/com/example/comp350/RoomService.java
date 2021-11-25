package com.example.comp350;

import java.util.Scanner;

public class RoomService {

        public double c_Price =25.00;
        public String[] f_menu = {"steaks" ,"burgers", "salads"};
        public boolean cleaning = false;
        public boolean foodService = false;
        private String roomNumber;
        public boolean ordering;
        public String order;
        public String[] orderList;
        public int whatService = 0;
        private int overallPrice;
        Scanner scan = new Scanner(System.in);

        public RoomService(){}
        public RoomService(String[] order){
            this.orderList = order;

        }
        public void setRoom(String room){
            this.roomNumber = room;
        }
        public String getRoom(){
            return this.roomNumber;
        }
        public void setOverallPrice(int price){
            this.overallPrice = price;
        }
        public int getOverallPrice(){
            return this.overallPrice;

        }

        public void cleaningBill(){
            System.out.println("what room is this for: ");
            roomNumber = scan.nextLine();
            System.out.println("adds "+ c_Price +"to room # "+ roomNumber);
        }

        private void customerOrdering()
        {
            while(ordering){
                System.out.println("what would you like: ");
                order = scan.nextLine();
                if(order.toLowerCase().equals("quit")) ordering = false;
                else System.out.println(order+" was ordered for 10$");
            }
        }

        public void orderFood(){
            System.out.println("what room is this for: ");
            roomNumber = scan.nextLine();
            System.out.println("Menu");

            for(int i = 0; i < f_menu.length; i++){
                System.out.println("     "+f_menu[i]+"     $10");

            }

            customerOrdering();
        }
        public void whichService(){
            System.out.println("What service would you like(please enter its number):");
            System.out.println("1. ordering food");
            System.out.println("2. cleaning service");
            whatService = scan.nextInt();
            if(whatService == 1) cleaning = true;
            if(whatService == 2) ordering = true;
            if(cleaning) cleaningBill();
            if(foodService) orderFood();

        }
        public int getPrice(String order){
            if(order.equals("Steak")) return 15;
            if(order.equals("Burger")) return 12;
            if(order.equals("SALAD")) return 8;
            return 0;
        }
        public String displayOrder(String[] order){
            String orderReturn ="";
            String[] stringHolders = new String[order.length];
            for(int i = 0; i < order.length; i++){
                stringHolders[i] = "meal "+ i +": "+ order[i];
            }
            orderReturn = String.format("%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s \n%s",
                    "your order: ",stringHolders[0], stringHolders[1],stringHolders[2],stringHolders[3],stringHolders[4],
                    stringHolders[5],stringHolders[6],stringHolders[7],stringHolders[8],stringHolders[9]);
            return orderReturn;
        }
        public String orderConfirmation(){
            String confirmationText;
            confirmationText = String.format("%s \n%s%s \n%s%s \n%s",
                    "Order confirmed",
                    "Room Number: ","getRoom()",
                    "Price: ","getOverallPrice()",
                    "Order sent to room");
            return confirmationText;
        }
    }





