
package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.DisplayTasksMassage;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Task;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setRoot;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.getClient;

public class MainController {

    @FXML
    private AnchorPane btn1;

    @FXML
    private Button see_all_task_btn;


    @FXML
    private TextField TF_forErrro;

    @FXML
    private TextField Username_TF;

    @FXML
    private Label user_name_label;


    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_TF;
    private int msgId;


    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, content);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.show();
    }


    @FXML
    void LogIN_check_info(ActionEvent event) throws IOException {
        String password = password_TF.getText();
        String username = Username_TF.getText();
        System.out.println(password + "   " + username);
        password_TF.clear();
        Username_TF.clear();
        Message message = new Message("Confirm information", username, password);
        SimpleClient.getClient().sendToServer(message);

    }


        @Subscribe
        public void result_user_input(NewVerifiedInformationEvent event)
        {
            System.out.println("in client/after event/result_user_input");
            if(event.getMessage().getMessage().equals("correct"))switchToMainOfUser(event.getMessage().getUser().getGivenName());
            else if(event.getMessage().getMessage().equals("wrongPassword"))
            {

             TF_forErrro.setText("you try to write a wrong password,please try againe");

               // showAlert("Error","wrong password","you try to write a wrong password,please try againe",Alert.AlertType.ERROR);
            }
            else if(event.getMessage().getMessage().equals("user is not exist"))
            {
                TF_forErrro.setText("you try to write a wrong username,please try againe");

                showAlert("Error","wrong UserName","you try to write a wrong username,please try againe",Alert.AlertType.ERROR);
            }
        }



    void switchToMainOfUser(String name) {
        Platform.runLater(() -> {
            try {
                setRoot("main");
                //  String currentText = user_name_label.getText(); // Get the current text
                //  String newText = currentText + " " + name; // Concatenate with the new name
                user_name_label.setText(name); // Set the new text
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }


    @FXML
    void switchToAllTask(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            try {
                setRoot("All_tasks_fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        msgId = 0;
        try {
            Message message = new Message(msgId, "add client");
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
