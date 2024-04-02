package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.greenrobot.eventbus.EventBus;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class UserClient extends AbstractClient {

    /*just for the running check*/
    private static Registered_user loggedInUser=null;
    private static UserClient client = null;

    //    public static void login(String username, String password) { //rina and malek
//        // Perform login logic and set the loggedInUser
//        // ...
//
//        // Example: Setting the loggedInUser
//        // here it need to return from server with the user instance
//        //loggedInUser = new Registered_user(username, /* other user details */);
//        /*just for the running*/
////        loggedInUser = new Registered_user("Rom", "Levi", "rom_levi1", "123", false, "0507773121", "Haifa");
//    }
    private UserClient(String host, int port) {
        super(host, port);
    }

    public static Registered_user getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Registered_user user) {
        if (loggedInUser == null) {
            System.out.println("client created");
            loggedInUser = user;
        }
    }

    @Override
    protected void handleMessageFromServer(Object msg) throws IOException {
        System.out.println("got into handleMessageFromServer ");
        if (msg instanceof NewTaskMessage) {
            NewTaskMessage ntm = (NewTaskMessage) msg;
            EventBus.getDefault().post(new NewTaskEvent(ntm));

        }
//        else if (msg instanceof DisplayDataMessage) {
//            DisplayDataMessage dis = (DisplayDataMessage) msg;
//            if (dis.getDataType().equals("all tasks")) {
//                ObservableList<Task> ALLTask_ = FXCollections.observableArrayList(dis.getTasks());
//                TasksOb.getInstance().setObservableTasks(ALLTask_);
//            }
//        }
//        else if (msg instanceof AddToObMessage) {
//            AddToObMessage NT = (AddToObMessage) msg;
//            TasksOb.getInstance().observableTasks.add(NT.getNewtask());
//        }
        else if (msg instanceof Message) {
            System.out.println("in client/handlefrom serverr /in message inst");
            Message message=(Message)msg;
            System.out.println(message.getMessage());
            EventBus.getDefault().post(new NewVerifiedInformationEvent(message));
        }

    }

    public static UserClient getClient() {
        if (client == null) {
            System.out.println("client created");
            client = new UserClient("localhost", 3000);
        }
        return client;
    }
}