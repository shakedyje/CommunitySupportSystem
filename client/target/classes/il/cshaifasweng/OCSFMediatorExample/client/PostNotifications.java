package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.MessageOfStatus;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.geometry.Pos;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setRoot;

public class PostNotifications {

    private static PostNotifications instance;

    private PostNotifications() {
        // Private constructor to prevent instantiation
    }

    public static synchronized PostNotifications getInstance() {
        if (instance == null) {
            instance = new PostNotifications();
        }
        return instance;
    }
    public void TaskNotification(UsersNotificationEvent event)
    {
        Platform.runLater(() -> {
            // Create a media file for the notification sound
            Media errorsound = new Media(new File("sorry_sound.wav").toURI().toString());
            MediaPlayer mediaPlayer1 = new MediaPlayer(errorsound);
            Media confirmsound = new Media(new File("yay_sound.mp3").toURI().toString());
            MediaPlayer mediaPlayer2 = new MediaPlayer(confirmsound);
            System.out.println("in notification");
            if (event.getNotification().getNotification().equals("Task Accepted")) {
                Image img= new Image("small_tick.png");
                ImageView small_tick =new ImageView(img);
                small_tick.setFitWidth(50); // Set the width to 50 pixels
                small_tick.setFitHeight(50);
                Notifications.create()
                        .title("Task Accepted!")
                        .graphic(small_tick)
                        .text("Hooray " + event.getNotification().getAddressee().getGivenName() + "!\n" +
                                "The community manager has approved your task")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_LEFT)
                        .show();
                mediaPlayer2.play();
            } else if(event.getNotification().getNotification().equals("Task Rejected")) {
                Notifications.create()
                        .title("Task Rejected")
                        .text("Sorry " + event.getNotification().getAddressee().getGivenName() + "...\n" +
                                "The community manager has rejected your task")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_LEFT)
                        .showError();
                mediaPlayer1.play();
            } else if (event.getNotification().getNotification().equals("Volunteer Found")) {
                Notifications.create()
                        .title("Volunteer found")
                        .text("Lucky you! " + event.getNotification().getUserInvolved().getUsername() + " volunteered for your task at this moment ")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_LEFT)
                        .showInformation();
                mediaPlayer2.play();
            } else if (event.getNotification().getNotification().equals("Completed?")) {
                ButtonType customButton = new ButtonType("See More");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        String.format("We noticed that you didn't update the volunteer status for some of your tasks "));
                alert.setTitle("Have you completed your tasks?");
                alert.getButtonTypes().add(customButton);
                // Show the alert and wait for a button to be clicked
                Optional<ButtonType> result = alert.showAndWait();

                // Check if the custom button was clicked
                if (result.isPresent() && result.get() == customButton) {
                    // Perform the action when the custom button is clicked
                    Platform.runLater(() -> {
                        try {
//                cleanup();
                            setRoot("showMyVolunteeredTasks");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    // Add your action code here
                }
            }
//            EventBus.getDefault().unregister(this);
        });
    }


            }







