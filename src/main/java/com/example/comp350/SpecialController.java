package com.example.comp350;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class SpecialController {
    public String spa;
    private String spaType;
    private double time;
    private String name;
    public String pgname;
    private double duration;
    @FXML
    TextField SpecialNameTextField;
    @FXML
    TextField startTimeSpecialTextField;

    public void setName(String name) {
        this.name = name;
    }

    public void makePage(String fxml, String title, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent root1 = fxmlLoader.load();
        //Replaces existing window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        int HEIGHT = 1920, WIDTH = 1080;
        stage.setScene(new Scene(root1, HEIGHT, WIDTH));
        stage.show();
    }

    @FXML
    private void handleButtonActionHome(ActionEvent event) {
        try {
            String name = "hello-view.fxml";
            String title = "MiYe";
            makePage(name, title, event);
        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void handleButtonActionForMake(ActionEvent event) {
        try {
            String name = "make-reservation-page.fxml";
            String title = "Make Reservation";
            makePage(name, title, event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void buttonForTime1() {
        this.duration = .5;
    }

    @FXML
    private void buttonForTime2() {
        this.duration = 1.0;
    }

    @FXML
    private void buttonForTime3() {
        this.duration = 1.5;
    }









    @FXML
    private void buttonForST1() {
        this.spaType = "HOT_STONE";
    }

    @FXML
    private void buttonForST2() {
        this.spaType = "=SUGAR_SCRUB";
    }

    @FXML
    private void buttonForST3() {
        this.spaType = "HERBAL_BODY_WRAP";
    }

    @FXML
    private void buttonForST4() {
        this.spaType = "BOTANICAL_MUD_WRAP";
    }


    @FXML
    private void buttonForBath(ActionEvent event) {
        String title = "Mineral Bath";
        this.spa = title.toUpperCase();
        this.spaType = "";

        try {
            String name = "mineral-bath-page.fxml";
            makePage(name, title, event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }

    }

    // Options With Reservation

    @FXML
    private void handleButtonActionForView(ActionEvent event) {
        try {
            String name = "view-reservation-page.fxml";
            String title = "View Reservation";
            makePage(name, title, event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void handleButtonActionForRemove(ActionEvent event) {
        try {
            String name = "remove-reservation-page.fxml";
            String title = "Remove Reservation";
            makePage(name, title, event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void handleButtonActionForSubmit(ActionEvent event) {
        System.out.println(this.spa == null ? "DID NOT SHOW" : "DID SHOW");
        //Reservation rs = SpaReservation.spaServices(this.time,this.name,this.spa,this.spaType,this.duration);
        try {
            String pname = "payment-page.fxml";
            String title = "Payment";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pname));
            Parent root1 = fxmlLoader.load();
            //Replaces existing window
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);

            variable(290, 370, root1);
            //show user detail

            int HEIGHT = 1920, WIDTH = 1080;
            stage.setScene(new Scene(root1, HEIGHT, WIDTH));
            stage.show();

        } catch (Exception e) {
            System.out.println("Can't load a new window");
            System.out.println(e);
        }
    }
    private void variable(int x, int y,Parent r)
    {
        // Goes to text and reads the name
        this.name=SpecialNameTextField.getText();
        this.spa = "SPECIAL_TREATMENT";
        this.time = Integer.parseInt(startTimeSpecialTextField.getText());

        Reservation rs = SpaReservation.spaServices(this.time,this.name,this.spa,this.spaType,this.duration);
        //Label variableLabel = new Label();
        Label variableLabel = (Label) r.lookup("#namLbl");
        variableLabel.setFont(new Font(30));
        variableLabel.setText(SpaReservation.displayReservation(rs));
        variableLabel.setLayoutX(x);
        variableLabel.setLayoutY(y);
    }
}