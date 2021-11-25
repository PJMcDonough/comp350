package com.example.comp350;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class ServiceController {
    public String spa;
    private String spaType;
    private double time;
    private String name;
    private double duration;
    private String room;
    public String[] order = new String[10];
    public int price;
    private int orderIndex;
    public RoomService roomService = new RoomService();
    //for showing/setting tables
    @FXML
    TextArea usrname;



     //   public String name;
        public String text;
      //  private String room;
       // public String[] order = new String[10];
      //  public int price;
       // private int orderIndex;
       // public RoomService roomService = new RoomService();
       // @FXML
       // TextArea usrname;


        public void setRoom(String room) {

            this.room = room;
        }

        public void makePage(String fxml, String title, ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(fxml));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            int HEIGHT = 1920;
            int WIDTH = 1080;
           // if (fxml.equalsIgnoreCase("dine-in-page.fxml")) {
           //     ServiceController ServiceController = fxmlLoader.getController();
           //     room = usrname.getText();
                //this.room = Integer.parseInt(text);
           //     ServiceController.setRoom(this.room);
            //}

            stage.setScene(new Scene(root1, (double)HEIGHT, (double)WIDTH));
            stage.show();
        }

        @FXML
        private void handleButtonActionHome(ActionEvent event) {
            try {
                String name = "hello-view.fxml";
                String title = "MiYe";
                this.makePage(name, title, event);
            } catch (Exception var4) {
                System.out.println("Can't load a new window");
            }

        }

        @FXML
        private void handleButtonActionForMake(ActionEvent event) {
            try {
                String name = "make-reservation-page.fxml";
                String title = "Make Reservation";
                this.makePage(name, title, event);
            } catch (Exception var4) {
                System.out.println("Can't load a new window");
            }

        }
        @FXML
        private void handleButtonActionForServices(ActionEvent event){
            try{
                String name = "room-services-page.fxml";
                String title = "View Services";
                makePage(name, title, event);
            }catch(Exception e){
                System.out.println("Can't load a new window");
            }

        }

        @FXML
        private void handleButtonForSteaks() {
            order[orderIndex] = "Steak";
            System.out.println(order[orderIndex]);
            this.name = "Dine-in-page.fxml";
            roomService.setOverallPrice(roomService.getOverallPrice() + roomService.getPrice(order[orderIndex]));
            System.out.println(roomService.getOverallPrice());
            System.out.println("To room " + this.room);
            orderIndex++;
        }

        @FXML
        private void handleButtonForBurger() {
            order[orderIndex] = "Burger";
            System.out.println(order[orderIndex]);
            this.name = "Dine-in-page.fxml";
            roomService.setOverallPrice( roomService.getOverallPrice() + roomService.getPrice(order[orderIndex]));
            System.out.println(roomService.getOverallPrice());
            System.out.println("To room " + room);
            orderIndex++;
        }

        @FXML
        private void handleButtonForSalad() {
            order[orderIndex] = "SALAD";
            System.out.println(order[orderIndex]);
            this.name = "Dine-in-page.fxml";
            roomService.setOverallPrice( roomService.getOverallPrice() + roomService.getPrice(order[orderIndex]));
            System.out.println(roomService.getOverallPrice());
            System.out.println("To room " + room);
            orderIndex++;
        }
    @FXML
    private void handleButtonActionForDineIn(ActionEvent event){
        try{
            String name = "dine-in-page.fxml";
            String title = "View Menu";
            makePage(name, title, event);

        }catch(Exception e){
            System.out.println("Can't load a new window");
        }
    }


        @FXML
        private void handleButtonForOrder(ActionEvent event) {
            try {
                String name = "order-page.fxml";
                String title = "order confirmation";
                //this.makePage(name, title, event);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name));
                Parent root1 = fxmlLoader.load();
                //Replaces existing window
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setTitle(title);
                int HEIGHT = 1920, WIDTH = 1080;

                orderConfirmation(290, 370, root1);
                stage.setScene(new Scene(root1, HEIGHT, WIDTH));
                stage.show();
            } catch (Exception var4) {
                System.out.println(var4);
            }

        }




        @FXML
        private void handleButtonActionForView(ActionEvent event) {
            try {
                String name = "view-reservation-page.fxml";
                String title = "View Reservation";
                this.makePage(name, title, event);
            } catch (Exception var4) {
                System.out.println("Can't load a new window");
            }

        }

        @FXML
        private void handleButtonActionForRemove(ActionEvent event) {
            try {
                String name = "remove-reservation-page.fxml";
                String title = "Remove Reservation";
                this.makePage(name, title, event);
            } catch (Exception var4) {
                System.out.println("Can't load a new window");
            }

        }


        private void orderList(int x, int y, Parent r){
            RoomService rs = new RoomService(order);
            Label variableLabel = (Label) r.lookup("#namLbl");
            variableLabel.setFont(new Font(30.00));
            variableLabel.setText(rs.displayOrder(order));
            variableLabel.setLayoutX((double)x);
            variableLabel.setLayoutY((double)y);

        }
        private void orderConfirmation(int x, int y, Parent r){
        roomService.setRoom(this.room);
        Label variableLabel = new Label("#orderLbl");
        variableLabel.setFont(new Font(30.00));
        variableLabel.setText(roomService.orderConfirmation());
        variableLabel.setLayoutX((double)x);
        variableLabel.setLayoutY((double)y);

    }

}


