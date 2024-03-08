package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import org.greenrobot.eventbus.EventBus;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class UserClient extends AbstractClient {

    /*just for the running check*/
    private static Registered_user loggedInUser;
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
////		Message message = (Message) msg;
//        if(msg instanceof MessageOfStatus) {
//            MessageOfStatus message1 = (MessageOfStatus) msg;
//            if (message1.getChangeStatus().equals("the change completed")) {
//                EventBus.getDefault().post(new NewDetailsEvent(message1));
//                getClient().sendToServer("display tasks");
//            }
//        }
//		if(message1.getChangeStatus().equals("update submitters IDs")){
//			EventBus.getDefault().post(new UpdateMessageEvent(message));
//		}else if(message.getMessage().equals("client added successfully")){
//			EventBus.getDefault().post(new NewSubscriberEvent(message));
//		}else if(message.getMessage().equals("Error! we got an empty message")){
//			EventBus.getDefault().post(new ErrorEvent(message));
//        else if (msg instanceof DisplayTasksMassage) {
//            DisplayTasksMassage dis = (DisplayTasksMassage) msg;
//            EventBus.getDefault().post(new TasksMessageEvent(dis));
//            System.out.println("recognized massage as a list of tasks");
//        }
//        if (msg instanceof NewTaskMessage) {

//            DisplayTasksMassage dis = (DisplayTasksMassage) msg;
//            EventBus.getDefault().post(new TasksMessageEvent(dis));
//            System.out.println("recognized massage as a list of tasks");


//		else {
//			EventBus.getDefault().post(new MessageEvent(message));
//		}
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



