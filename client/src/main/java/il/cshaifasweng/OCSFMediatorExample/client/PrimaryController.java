package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.MessageOfStatus;
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

public class PrimaryController {

	@FXML
	private TextField submitterID1;

	@FXML
	private TextField submitterID2;

	@FXML
	private TextField timeTF;

	@FXML
	private TextField MessageTF;

	@FXML
	private Button SendBtn;

	@FXML
	private TextField DataFromServerTF;

	private int msgId;
    ////////////////////////////////////
	/////////////////////////////////////\
	//////////////////////////////////////////////
	///////////////////////////////////////////////////////BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBb
	@FXML
	void sendMessage(ActionEvent event) {
		try {
			Message message = new Message(msgId++, MessageTF.getText());
			MessageTF.clear();
			SimpleClient.getClient().sendToServer(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	////////////////////////////////////
	/////////////////////////////////////\
	//////////////////////////////////////////////
	///////////////////////////////////////////////////////

	@FXML
	void changeRequest(ActionEvent event) {
		try {
			MessageOfStatus message = new Message(task, "change status");
			SimpleClient.getClient().sendToServer(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Subscribe
	public void setDataFromServerTF(MessageEvent event) {
		DataFromServerTF.setText(event.getMessage().getMessage());
	}//////////////////////////////////////////////////////////////////////////////////

	@Subscribe
	public void showNewRequest(NewDetailsEvent event) {

		DateTimeFormatter creationTime = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter deadlineTime = DateTimeFormatter.ofPattern("HH:mm:ss");

		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION,
					String.format("Task ID: %d\nType of task: %s\nCreation time: %s\nUser name: %s" +
									"\nDeadline time: %s\nStatus: %s\nVolunteer: %s",
							event.getMessage().getTask().getId(),
							event.getMessage().getTask().getType_of_task(),
							event.getMessage().getTask().getCreation_time().format(dtf),
							event.getMessage().getTask().getUser(),
							event.getMessage().getTask().getDeadline().format(dtf),
							event.getMessage().getTask().getSituation(),
							event.getMessage().getTask().getVolunteer())
			);
			alert.setTitle("Task Information");
			alert.setHeaderText("Task Information:");
			alert.show();
		});
	}

	@Subscribe
	public void setSubmittersTF(UpdateMessageEvent event) {
		submitterID1.setText(event.getMessage().getData().substring(0,9));
		submitterID2.setText(event.getMessage().getData().substring(11,20));
	}

	@Subscribe
	public void getStarterData(NewSubscriberEvent event) {
		try {
			Message message = new Message(msgId, "send Submitters IDs");
			SimpleClient.getClient().sendToServer(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Subscribe
	public void errorEvent(ErrorEvent event){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.ERROR,
					String.format("Message:\nId: %d\nData: %s\nTimestamp: %s\n",
							event.getMessage().getId(),
							event.getMessage().getMessage(),
							event.getMessage().getTimeStamp().format(dtf))
			);
			alert.setTitle("Error!");
			alert.setHeaderText("Error:");
			alert.show();
		});
	}

	@FXML
	void initialize() {
		EventBus.getDefault().register(this);
		MessageTF.clear();
		DataFromServerTF.clear();
		msgId=0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			LocalTime currentTime = LocalTime.now();
			timeTF.setText(currentTime.format(dtf));
		}),
				new KeyFrame(Duration.seconds(1))
		);
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
		try {
			Message message = new Message(msgId, "add client");
			SimpleClient.getClient().sendToServer(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
