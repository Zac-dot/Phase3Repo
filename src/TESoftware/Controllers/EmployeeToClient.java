package TESoftware.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

//The way that a employee will work with a client
public class EmployeeToClient {
    //For use with SQL Connector
    String databaseurl = "jdbc:mysql://localhost:3306/teaccounting";
    String username = "root";
    String password = "cs380";
    //Used for various aspects of the program
    @FXML
    public ComboBox comboboxID;
    public TextField costID;
    public TextField commentID;
    //Populates the box with variables to choose from
    @FXML
    public void initialize() {
        comboboxID.getItems().removeAll(comboboxID.getItems());
        comboboxID.getItems().addAll("Philip J Fry", "Micheal Bee Bee");
        comboboxID.getSelectionModel().select("Select a person");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                comboboxID.getValue();
            }
        };

        comboboxID.setOnAction(event);
    }

    @FXML
    public Button submitID;
    //Main part that will add the information collected and call on getters, setters, and submit to SQL
    public void Submitted(ActionEvent event) throws Exception {

        //SQL Code, getters, setters get called
        Random rand = new Random();
        int randint1 = rand.nextInt(1000);

        //Reconnects to the SQL Database and places what was made into individual table
        try{
            Connection conn = DriverManager.getConnection(databaseurl,username,password);
            Statement smt = (Statement) conn.createStatement();
            smt.execute("INSERT IGNORE INTO orderHistory(orderID,client,budget,comments) VALUES ('"+ randint1 + "','" + (String)comboboxID.getValue() +"','" + Double.parseDouble(costID.getText()) + "','" + (String)commentID.getText() +"');");
            Alert(randint1, event);
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something didn't go as planned");
            //Remake the previous stage
            goBack(event);
        }

    }

    //Makes an alert to show the orderID
    public void Alert(int randint1, ActionEvent event) throws Exception {
        //Alerts the user and will show the OrderID
        Alert alert = new Alert(Alert.AlertType.NONE);

        alert.setAlertType(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setContentText("Added! Your Order Number is: " + randint1);
        alert.show();

        //Remake the previous stage
        goBack(event);
    }


    //On press it will go back to the previous view
    @FXML
    public Button backID;
    public void goBack(ActionEvent event) throws Exception{
        Stage stage = (Stage) backID.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/TESoftware/Views/EmployeeProfile.fxml"));
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Welcome, Philip J Fry");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
