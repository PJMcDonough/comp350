package com.example.comp350;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Scanner;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    public Button makeReservation;
    public void onMakeReservationClick()
    {
        /*
        System.out.println("When and what kind?");
        Scanner testing = new Scanner(System.in);
        String when = testing.next();
        String whatKind = testing.next();

        System.out.println("The Customer wants a " + whatKind + " at " + when);
         */
        SpaReservation.makeSpaReservation();
    }

    @FXML
    public void onViewReservationClick()
    {
        SpaReservation.displayReservation();
    }

    public void onRemoveReservationClick() {SpaReservation.removeReservation();}

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    public Button Bookingbtn;
    public void bookingAction(){
        Booking book = new Booking();
        book.book();


    }
    public Button roomservicebtn;
    public void roomServiceAction() {
        RoomService service1 = new RoomService();
        service1.whichservice();
    }
    private void handleButtonAction(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondWindow.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Make Reservation");
            stage.setScene(new Scene(root1));
            stage.show();

        }catch (Exception e)
        {
            System.out.println("Can't load a new window");
        }
    }
}