package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Task;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class registered_user_Controller {

    @FXML
    private TextField another_info;
    @FXML
    private TextField TF_deadline;

    @FXML
    private AnchorPane btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;
    private int msgId;

    @FXML
    void check_choose(ActionEvent event)
    {

        Task newTask;

        try {
            int deadline= Integer.parseInt(TF_deadline.getText());
            Button clickedButton = (Button) event.getSource();
           // newTask=new Task(clickedButton.getText(),getClient(),deadline);
            Message message = new Message(msgId++,clickedButton.getText());
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    @FXML
    void send_free_choose(ActionEvent event)
    {
        try {
            int deadline= Integer.parseInt(TF_deadline.getText());
            Message message = new Message(msgId++, another_info.getText());
            another_info.clear();
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

}
