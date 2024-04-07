package il.cshaifasweng.OCSFMediatorExample.client;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.geometry.Pos;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleChatClient.setRoot;

public class PostNotifications {

    private static PostNotifications instance;
    static boolean notified=false;
    static boolean unregeister=false;

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
            unregeister=false;
            // Create a media file for the notification sound
            Media errorsound = new Media(new File("sorry_sound.wav").toURI().toString());
            MediaPlayer mediaPlayer1 = new MediaPlayer(errorsound);
            Media confirmsound = new Media(new File("yay_sound.mp3").toURI().toString());
            MediaPlayer mediaPlayer2 = new MediaPlayer(confirmsound);
            Media victorysound = new Media(new File("victory_sound.wav").toURI().toString());
            MediaPlayer mediaPlayer3 = new MediaPlayer(victorysound);
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
                        .title("Volunteer Found!")
                        .text("Luck you! " + event.getNotification().getUserInvolved().getUsername() +
                                " has just volunteered for your task number "+ event.getNotification().getTaskInvolved().getId())
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_LEFT)
                        .showInformation();
                        mediaPlayer3.play();
            } else if (event.getNotification().getNotification().equals("Completed?") && !notified) {
                System.out.println("in notification completed?");
                ButtonType seeMore = new ButtonType("See Tasks", ButtonBar.ButtonData.YES);
                ButtonType off = new ButtonType("Don't Show Again");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "We noticed that you didn't update the volunteer status for some of your tasks ");
                alert.setTitle("Volunteer Status Check");
                alert.setHeaderText("Have you completed your tasks?");
                alert.getDialogPane().setPrefWidth(500); // Set the width as needed
                alert.getDialogPane().setPrefHeight(100); // Set the height as needed

                // Add the "Don't Show Again" button
                alert.getButtonTypes().addAll(seeMore,off); // Add the custom button
                Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                if (okButton != null) {
                    // Set the text for the OK button
                    okButton.setText("Keep Remind Me");
                }


                // Show the alert and wait for a button to be clicked
                Optional<ButtonType> result = alert.showAndWait();

                // Check if the custom button was clicked
                if (result.isPresent() && result.get() == seeMore) {
                    // Perform the action when the custom button is clicked
                    Platform.runLater(() -> {
                        try {
                            setRoot("showMyVolunteeredTasks");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    notified=true;
                    alert.close();
                    unregeister=true;
                } else if (result.isPresent() && result.get() == off) {
                    notified = true;
                }


            }

//            EventBus.getDefault().unregister(this);
        });
    }


            }







