package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;


import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.getClient;


import javafx.scene.control.*;
import org.greenrobot.eventbus.Subscribe;

public class Manager  {

    @FXML
    private Button Approve;

    @FXML
    private Button EmergencyButton;

    @FXML
    private Button EmergencyCalls;

    @FXML
    private ListView<Task> ListOfTasks;

    @FXML
    private Button Members;

    @FXML
    private TextField Reason;

    @FXML
    private Button Reject;

    @FXML
    private TextArea Request;

    @FXML
    private Button Send;

    @FXML
    private Button Tasks;


    private int msgId;



    @FXML
    void ApproveRequest(ActionEvent event) {

    }

    @FXML
    void EmergencyCall(ActionEvent event) {

    }

    @FXML
    void RejectRequest(ActionEvent event) {

    }

    @FXML
    void SendReason(ActionEvent event) {

    }

    @FXML
    void ShowEmergency(ActionEvent event) {

    }

    @FXML
    void ShowList(ActionEvent event) {

    }

    @FXML
    void ShowMembers(ActionEvent event) {

    }

    @FXML
    void ShowTasks(ActionEvent event) {

    }

    @FXML
    void WriteReason(ActionEvent event) {

    }

    @FXML
    void AcceptRequest(ActionEvent event) {
    }


    @Subscribe
    public void ShowListView(TasksMessageEvent event) {

      /*  Platform.runLater(() -> {
            tasksContaine.getChildren().clear(); // Clear existing content

            if (event != null) {
                List<Task> tasks = event.getTasksE().getTasks();
                if (tasks != null && !tasks.isEmpty()) {
                    ObservableList<Task> observableTasks = FXCollections.observableArrayList(tasks);

                    // Create ListView to display tasks
                    ListOfTasks = new ListView<>(observableTasks);
                }
                else {
                    showAlert("Requests Information", "Requests Information", "There is no requests.", Alert.AlertType.INFORMATION);
                }
            }
            else {
                showAlert("Error", "Error", "Invalid event received.", Alert.AlertType.ERROR);
            }

            Scene scene = new Scene(ListOfTasks, 300, 250);

            // Set stage title and scene, then show the stage
            Manager.setTitle("Tasks Waiting for Approval");
            Manager.setScene(scene);
            Manager.show();
        });*/
    }

    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, content);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.show();
    }

    @FXML
    void initialize() {
      //  EventBus.getDefault().register(this);
        try {
            UserClient.getClient().sendToServer("list view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }





}
