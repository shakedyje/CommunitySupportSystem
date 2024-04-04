
package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.LogInOutMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.MessageOfStatus;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import static il.cshaifasweng.OCSFMediatorExample.client.ManagerClient.getManagerClient;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setRoot;
import static il.cshaifasweng.OCSFMediatorExample.client.UserClient.getClient;
import static il.cshaifasweng.OCSFMediatorExample.client.UserClient.getLoggedInUser;

public class UserMainController {

    @FXML
    private Button volunteering;

    @FXML
    private AnchorPane btn1;
    @FXML
    private Label welcome_label; //after rina and malek do login we'll change it to welcome + user name

    @FXML
    private Button MY_REQUSTED_TASKS;
    String SaveUserName;

    private static Scene scene;
    private static Stage appStage;
    private Button LogOutBtn;


    @FXML
    void LOG_OUT(ActionEvent event) throws IOException {
//        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        currentStage.close();
//        ManagerClient.getClient().closeConnection();
        Message message = new Message("log out user", UserClient.getLoggedInUser().getUsername());
        UserClient userClient = UserClient.getClient();
        System.out.println("i will enter");
        userClient.sendToServer(message);
        System.out.println("Logout message sent to server");
        UserClient.setLoggedInUser(null);
//        UnknownUserClient.getClient().openConnection();
        Platform.runLater(() -> {
            try {
                setRoot("log_in");
//                UnknownUserClient.getClient().openConnection();
//                UserClient.getClient().closeConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("back from platfrom");
//        cleanup();

//        UserClient.getClient().sendToServer(new LogInOutMessage(getLoggedInUser(), "log out")); //add to logged-in users list
//
//        Platform.runLater(() -> {
//            try {
//                setRoot("log_in");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }

    //    @Subscribe
////    public void TaskNotification(UsersNotificationEvent event)
////    {
////        PostNotifications.getInstance().TaskNotification(event);
////    }
    public void cleanup() {
        // Unregister from the event bus during cleanup
        System.out.println("cleaned");
        EventBus.getDefault().unregister(this);
        System.out.println("999999999999999999999999999");
    }

    public void setAppStage(Stage appStage) {
        this.appStage = appStage;
    }

    @FXML
    private void initialize() {
//        try {
//            UserClient.getClient().openConnection();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        // Assuming you have the username stored in a variable named 'username'
        String username = UserClient.getLoggedInUser().getGivenName();
        SaveUserName = UserClient.getLoggedInUser().getUsername();
        // Set the text of the welcome_label to the username
        welcome_label.setText("Welcome " + username);
        welcome_label.setAlignment(Pos.CENTER);

        try {
          //  MessageOfStatus message = new MessageOfStatus("add user client", SaveUserName);
            Message message3 = new Message("add user client", UserClient.getLoggedInUser().getUsername());
            UserClient.getClient().sendToServer(message3);
//           UserClient.getClient().sendToServer(new LogInOutMessage(getLoggedInUser(), "log in"));
            //getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    @FXML
    void switchToemergency(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                setRoot("Emergency");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    void switchToNewTask(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            try {
                setRoot("new_task");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Subscribe
    void TaskAcceptedNotifiction(UsersNotificationEvent event) {

    }

    @FXML
    void showRequstedTasks(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                setRoot("requestedTasksPage");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @FXML
    void switchToVolunteer(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                setRoot("showMyVolunteeredTasks");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    void turnToVolunteeringPage(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                setRoot("VolunteeringPage");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }
}
