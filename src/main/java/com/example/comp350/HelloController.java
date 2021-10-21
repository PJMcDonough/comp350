package com.example.comp350;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class HelloController {

    private String spa;
    private String spaType;
    private double time;
    private String name;
    private double duration;


   /* public Button setReservation;

    @FXML
    private Label welcomeText;
    @FXML
    public Button makeReservation;

    @FXML
    public TextField nameOrDate;

    public void onMakeReservationClick() throws SQLException {
        SpaReservation.makeSpaReservation();
    }

    @FXML
    public void onViewReservationClick() {
        SpaReservation.displayReservation();
    }

    public void onRemoveReservationClick() throws SQLException {SpaReservation.removeReservation();}
*/
    /* @FXML
     protected void onHelloButtonClick() {
         welcomeText.setText("Welcome to JavaFX Application!");
     }
   /*public Button Bookingbtn;
     public void bookingAction() {
         Booking book = new Booking();
         book.book();
     }*/


    public void makePage(String fxml, String title, ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent root1 = fxmlLoader.load();
        //Replaces existing window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        int HEIGHT = 1920;
        int WIDTH = 1080;
        stage.setScene(new Scene(root1, HEIGHT, WIDTH));
        stage.show();
    }

    @FXML
    private void handleButtonActionHome(ActionEvent event)
    {
        try {
            String name = "hello-view.fxml";
            String title = "MiYe";
            makePage(name, title,event);
        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void handleButtonActionForMake (ActionEvent event)
    {
        //if Cancelled, resets their input
        spaType = "";
        spa = "";
        time = 0.0;
        name = "";
        duration = 0;

        try {
            String name = "make-reservation-page.fxml";
            String title = "Make Reservation";
            makePage(name, title,event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void buttonForTime1()
    {
        duration = .5;
    }

    private void print(double duration) {
        System.out.println(duration);
    }

    @FXML
    private void buttonForTime2(int num)
    {
        duration = 1.0;
    }
    @FXML
    private void buttonForTime3()
    {
        duration = 1.5;
    }


    @FXML
    private void buttonForMassage(ActionEvent event)
    {
        try {
            String name = "massage-page.fxml";
            String title = "Massage";
            makePage(name, title,event);
            spa = title.toUpperCase();

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void buttonForM1()
    {
        spaType = "Swedish";
    }

    @FXML
    private void buttonForM2()
    {
        spaType = "Shiatsu";
    }

    @FXML
    private void buttonForM3()
    {
        spaType = "Deep Tissue";
    }


    @FXML
    private void buttonForFacial(ActionEvent event)
    {
        try {
            String name = "facial-page.fxml";
            String title = "Facial";
            makePage(name, title,event);
            spa = title.toUpperCase();

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void buttonForF1()
    {
        spaType = "Normal";
    }

    @FXML
    private void buttonForF2()
    {
        spaType = "Collagen";
    }

    @FXML
    private void buttonForSpecialTreat(ActionEvent event)
    {
        try {
            String name = "special-treatment-page.fxml";
            String title = "Special Treatment";
            makePage(name, title,event);
            spa = title.toUpperCase();

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void buttonForST1()
    {
        spaType = "Hot Stone";
    }
    @FXML
    private void buttonForST2()
    {
        spaType = "Sugar Scrub";
    }
    @FXML
    private void buttonForST3()
    {
        spaType = "Herbal Body Wrap";
    }
    @FXML
    private void buttonForST4()
    {
        spaType = "Botanical Mud Wrap";
    }


    @FXML
    private void buttonForBath(ActionEvent event)
    {
        String title = "Mineral Bath";
        spa = title.toUpperCase();
        spaType = "";

        try {
            String name = "mineral-bath-page.fxml";
            makePage(name, title,event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }

    }

    // Options With Reservation

    @FXML
    private void handleButtonActionForView (ActionEvent event)
    {
        try {
            String name = "view-reservation-page.fxml";
            String title = "View Reservation";
            makePage(name, title,event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }
    @FXML
    private void handleButtonActionForRemove (ActionEvent event)
    {
        try {
            String name = "remove-reservation-page.fxml";
            String title = "Remove Reservation";
            makePage(name, title,event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void handleButtonActionForSubmit (ActionEvent event)
    {
        Reservation rs = SpaReservation.spaServices(time,name,spa,spaType);

        //Add detail to the payment page and add to database

        try {
            String name = "payment-page.fxml";
            String title = "Payment";
            makePage(name, title,event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }
}