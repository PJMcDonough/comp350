package com.example.comp350;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
//for tables

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class HelloController {
    public String spa;
    private String spaType;
    private double time;
    private String name;
    private double duration;
    //for showing/setting tables
    @FXML
    TextArea usrname;

    public void makePage(String fxml, String title, ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent root1 = fxmlLoader.load();
        //Replaces existing window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        int HEIGHT = 1920, WIDTH = 1080;
        if (fxml.equalsIgnoreCase("massage-page.fxml")){
            //pass the name to another scene controller
            MassageController massageController = fxmlLoader.getController();
            this.name =usrname.getText();
            massageController.setName(this.name);

        }

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
        /*spaType = "";
        spa = "";
        time = 0.0;
        name = "";
        duration = 0;*/

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
        this.duration = .5;
    }

    @FXML
    private void buttonForTime2()
    {
        this.duration = 1.0;
    }
    @FXML
    private void buttonForTime3()
    {
        this.duration = 1.5;
    }


    @FXML
    private void buttonForMassage(ActionEvent event)
    {
        try {
            String pname = "massage-page.fxml";
            String title = "Massage";
            this.spa = title.toUpperCase();
            System.out.println(this.spa);
            makePage(pname, title,event);


        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }

    }

    @FXML
    //added new stuff here
    //sets the varible names for the new pane
    private void buttonForM1()
    {
        this.spa = "MASSAGE";
        this.name = "massage-page.fxml";
        this.spaType = "Swedish".toUpperCase();
    }

    @FXML
    //added new stuff here
    //sets the varible names for the new pane
    private void buttonForM2()
    {

        this.spa = "MASSAGE";
        this.name = "massage-page.fxml";
        this.spaType = "Shiatsu".toUpperCase();
    }

    @FXML
    //addded new stuff here
    private void buttonForM3()
    {
        this.spa = "MASSAGE";
        this.name = "massage-page.fxml";
        this.spaType = "Deep Tissue".toUpperCase();
    }


    @FXML
    private void buttonForFacial(ActionEvent event)
    {
        try {
            String name = "facial-page.fxml";
            String title = "Facial";
            makePage(name, title,event);
            this.spa = title.toUpperCase();

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void buttonForF1()
    {
        this.spaType = "Normal";
    }

    @FXML
    private void buttonForF2()
    {
        this.spaType = "Collagen";
    }

    @FXML
    private void buttonForSpecialTreat(ActionEvent event)
    {
        try {
            String name = "special-treatment-page.fxml";
            String title = "Special Treatment";
            makePage(name, title,event);
            this.spa = title.toUpperCase();

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void buttonForST1()
    {
        this.spaType = "Hot Stone";
    }
    @FXML
    private void buttonForST2()
    {
        this.spaType = "Sugar Scrub";
    }
    @FXML
    private void buttonForST3()
    {
        this.spaType = "Herbal Body Wrap";
    }
    @FXML
    private void buttonForST4()
    {
        this.spaType = "Botanical Mud Wrap";
    }


    @FXML
    private void buttonForBath(ActionEvent event)
    {
        String title = "Mineral Bath";
        this.spa = title.toUpperCase();
        this.spaType = "";

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
        System.out.println(this.spa == null ? "DID NOT SHOW": "DID SHOW");
        //Reservation rs = SpaReservation.spaServices(this.time,this.name,this.spa,this.spaType,this.duration);
        try {
            String name = "payment-page.fxml";
            String title = "Payment";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name));
            Parent root1 = fxmlLoader.load();
            //Replaces existing window
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(title);


            //save these to name values
            /*TableColumn<Reservation, String> stTime = new TableColumn<>("StartTime");
            stTime.setMinWidth(100);
            stTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
            TableColumn<Reservation, String> cust = new TableColumn<>("Customer");
            cust.setMinWidth(100);
            cust.setCellValueFactory(new PropertyValueFactory<>("customer"));
            TableColumn<Reservation, String> spType = new TableColumn<>("Spa Type");
            spType.setMinWidth(100);
            spType.setCellValueFactory(new PropertyValueFactory<>("special"));
            TableColumn<Reservation, String> banking = new TableColumn<>("Banking");
            TableColumn<Reservation, String> specType = new TableColumn<>("Special Type");
            TableColumn<Reservation, String> timeD = new TableColumn<>("TimeD");
            TableColumn<Reservation, String> val = new TableColumn<>("Value");
            table = new TableView<>();
            table.setItems(getResInfo());
            table.getColumns().addAll(stTime, cust, spType);
            root1.getChildrenUnmodifiable().addAll(table); */

            variable(290,370,root1);
            //show user detail

            // show revervation details

            /*Group group = new Group(variableLabel);
            Scene scene = new Scene(group, 400, 300);*/

            int HEIGHT = 1920, WIDTH = 1080;
            stage.setScene(new Scene(root1, HEIGHT, WIDTH));
            stage.show();

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    //Try to display variables that were passed in
    private void variable(int x, int y,Parent r)
    {
        Reservation rs = SpaReservation.spaServices(this.time,this.name,this.spa,this.spaType,this.duration);
        //Label variableLabel = new Label();
        Label variableLabel = (Label) r.lookup("#namLbl");
        variableLabel.setFont(new Font(30));
        variableLabel.setText(SpaReservation.displayReservation(rs));
        variableLabel.setLayoutX(x);
        variableLabel.setLayoutY(y);
    }

    @FXML
    private void handleButtonActionForPurchase (ActionEvent event)
    {
        System.out.println(this.spa);
        Reservation rs = SpaReservation.spaServices(this.time,this.name,this.spa,this.spaType,this.duration);
        //Add detail to the payment page and add to database

        try {
            String name = "confirmation-payment-page.fxml";
            String title = "Confirmation Payment";


            makePage(name, title,event);


        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }
    //make a new observable list that will return clomuns
    public ObservableList<Reservation> getResInfo(){
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        SpaType spa = SpaType.valueOf("MASSAGE");
        SpecialType sp = SpecialType.valueOf("SWEDISH");
        Banking b = new Banking(5,5,5,5);
        reservations.add(new Reservation(0.0,"Wanker",spa, b, sp, 0, 0.0 ));
        return reservations;
    }
}