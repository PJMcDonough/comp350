package com.example.comp350;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloController {
    public String spa;
    private String spaType;
    private double time;
    private String name;
    private double duration;

    private static TableView<Object> tableView = new TableView<>();


    //TODO: Fix problem with spaType == null
    //TODO: List everything about the purchase
    //TODO: Confirmation page needs work

    //TODO: Functionality to the one or two rooms (2nd-ary)


    //Solved problem:  we want to use id to save it to a variable and use that to get the text
    //to get the string of the name or input
    @FXML
    private TextField textFieldForName;
    @FXML
    private TextField selectTime;
    @FXML
    private TextField textFieldForRemove;

    @FXML
    private Button button;

     @FXML
     private Text textForNameOutput;
     @FXML
     private Text textForSpaOutput;
     @FXML
     private Text textForSpaTypeOutput;
     @FXML
     private Text textForTimeOutput;
     @FXML
     private Text textForDurationOutput;

    public void makePage(String fxml, String title, ActionEvent event)
    {
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root1 = fxmlLoader.load();
            //Replaces existing window
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            int HEIGHT = 1920, WIDTH = 1080;
            stage.setScene(new Scene(root1, HEIGHT, WIDTH));
            stage.show();

            if (fxml.equalsIgnoreCase("massage-page.fxml")){
                //pass the name to another scene controller
                MassageController massageController = fxmlLoader.getController();
                massageController.setName(this.name);

            }

        }catch (Exception e){
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void handleButtonActionHome(ActionEvent event)
    {
        String name = "hello-view.fxml";
        String title = "MiYe";
        makePage(name, title,event);
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

        String name = "make-reservation-page.fxml";
        String title = "Make Reservation";
        makePage(name, title,event);

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
        String name = "massage-page.fxml";
        String title = "Massage";
        this.spa = title.toUpperCase();
        makePage(name, title,event);
    }

    @FXML
    //added new stuff here
    //sets the varible names for the new pane
    private void buttonForM1()
    {
        this.spa = "MASSAGE";
        this.spaType = "Swedish".toUpperCase();
    }

    @FXML
    //added new stuff here
    //sets the varible names for the new pane
    private void buttonForM2()
    {
        this.spa = "MASSAGE";
        this.spaType = "Shiatsu".toUpperCase();
    }

    @FXML
    //addded new stuff here
    private void buttonForM3()
    {
        this.spa = "MASSAGE";
        this.spaType = "Deep Tissue".toUpperCase();
    }


    @FXML
    private void buttonForFacial(ActionEvent event)
    {
        String name = "facial-page.fxml";
        String title = "Facial";
        makePage(name, title,event);
        this.spa = title.toUpperCase();
    }

    @FXML
    private void buttonForF1()
    {
        this.spa = "FACIAL";
        this.spaType = "Normal";
    }

    @FXML
    private void buttonForF2()
    {
        this.spa = "FACIAL";
        this.spaType = "Collagen";
    }

    @FXML
    private void buttonForSpecialTreat(ActionEvent event)
    {
        String name = "special-treatment-page.fxml";
        String title = "Special Treatment";
        makePage(name, title,event);
    }

    @FXML
    private void buttonForST1()
    {
        this.spa = "SPECIAL TREATMENT";
        this.spaType = "Hot Stone";
    }
    @FXML
    private void buttonForST2()
    {
        this.spa = "SPECIAL TREATMENT";
        this.spaType = "Sugar Scrub";
    }
    @FXML
    private void buttonForST3()
    {
        this.spa = "SPECIAL TREATMENT";
        this.spaType = "Herbal Body Wrap";
    }
    @FXML
    private void buttonForST4()
    {
        this.spa = "SPECIAL TREATMENT";
        this.spaType = "Botanical Mud Wrap";
    }


    @FXML
    private void buttonForBath(ActionEvent event)
    {
        String title = "Mineral Bath";
        this.spa = title.toUpperCase();
        this.spaType = "";

        String name = "mineral-bath-page.fxml";
        makePage(name, title,event);

    }

    // Options With Reservation

    @FXML
    private void handleButtonActionForView (ActionEvent event)
    {
        String name = "view-reservation-page.fxml";
        String title = "View Reservation";
        TableUIpage(name,title,event);
    }
    @FXML
    private void handleButtonActionForRemove (ActionEvent event)
    {
        String name = "remove-reservation-page.fxml";
        String title = "Remove Reservation";
        makePage(name, title,event);

        String input = this.textFieldForRemove.getText();
        Reservation res = SpaReservation.removeReservation(input);
    }
    @FXML
    private void handleButtonForSearch(ActionEvent event)
    {
        String name = "search-remove-reservation-page.fxml";
        String title = "Search Remove Reservation";
        makePage(name, title,event);
    }


    private void printInputs()
    {
        textForNameOutput.setText(this.name);
        textForSpaOutput.setText(this.spa);
        textForSpaTypeOutput.setText(this.spaType);
        textForDurationOutput.setText(String.valueOf(this.duration));
    }

    private void copyTextFieldInput()
    {
        this.name = this.textFieldForName.getText();
        this.time = Double.parseDouble(this.selectTime.getText());
    }

    @FXML
    private void handleButtonActionForSubmit (ActionEvent event)
    {
        try {
            String name = "payment-page.fxml";
            String title = "Payment";
            makePage(name,title,event);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name));
            Parent root1 = fxmlLoader.load();
            //Replaces existing window
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(title);

            variable(290,370,root1);

            int HEIGHT = 1920, WIDTH = 1080;
            stage.setScene(new Scene(root1, HEIGHT, WIDTH));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }

        copyTextFieldInput();
    }

    //Try to display variables that were passed in
    private void variable(int x, int y, Parent r)
    {
        Reservation rs = SpaReservation.spaServices(this.time,this.name,this.spa,this.spaType,this.duration);
        Label variableLabel = (Label) r.lookup("#namLbl");

        variableLabel.setFont(new Font(30));
        assert rs != null;
        variableLabel.setText(SpaReservation.displayReservation(rs));
        variableLabel.setLayoutX(x);
        variableLabel.setLayoutY(y);
    }

    @FXML
    private void handleButtonActionForPurchase (ActionEvent event)
    {
        Reservation rs = SpaReservation.spaServices(this.time,this.name,this.spa,this.spaType,this.duration);

        //Add detail to the payment page and add to database
        addToTable(rs);

        String name = "confirmation-payment-page.fxml";
        String title = "Confirmation Payment";
        makePage(name, title,event);
    }

    //Inserted new method for table
    public void TableUIpage(String fxml, String title, ActionEvent event)  {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root1 = fxmlLoader.load();

            //Only to
            tableView.setEditable(false);

            // Creating each Column
            TableColumn<Object, Object> column1 = new TableColumn<>("Start Time");
            TableColumn column2 = new TableColumn<>("Name");
            TableColumn column3 = new TableColumn<>("Spa");
            TableColumn column4 = new TableColumn<>("SpaType");
            TableColumn column5 = new TableColumn<>("Duration");

            //Add them to column
            tableView.getColumns().addAll(column1, column2, column3, column4, column5);

            // Need to get the reservation part to insert into table
//        for(Reservation rs : reservation)
//            tableView.getItems().add(rs);
            //tableView.getItems().add(new Person("Jane", "Deer"));

            //Replaces existing window
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            int HEIGHT = 1920, WIDTH = 1080;
            Scene scene = new Scene(root1, HEIGHT, WIDTH);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            System.out.println("Can't load a new window");
        }
    }
    public void addToTable(Reservation rs) {
        tableView.getItems().add(rs);
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