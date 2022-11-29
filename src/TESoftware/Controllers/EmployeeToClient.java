package TESoftware.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

//The way that a employee will work with a client
public class EmployeeToClient {

    @FXML
    public ComboBox comboboxID;

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
