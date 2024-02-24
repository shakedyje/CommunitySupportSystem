package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.MessageOfStatus;
import il.cshaifasweng.OCSFMediatorExample.entities.Task;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.util.List;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		Message message = (Message) msg;
		MessageOfStatus message1 =(MessageOfStatus)  msg;
//		if(message1.getChangeStatus().equals("update submitters IDs")){
//			EventBus.getDefault().post(new UpdateMessageEvent(message));
//		}else if(message.getMessage().equals("client added successfully")){
//			EventBus.getDefault().post(new NewSubscriberEvent(message));
//		}else if(message.getMessage().equals("Error! we got an empty message")){
//			EventBus.getDefault().post(new ErrorEvent(message));
		if (msg instanceof List<?>) {
			List<Task> tasks = (List<Task>) msg;
			EventBus.getDefault().post(new TasksMessageEvent(tasks));
		}
		else if(message1.getChangeStatus().equals("the change completed")){

			EventBus.getDefault().post(new NewDetailsEvent(message1));
		}

		else {
			EventBus.getDefault().post(new MessageEvent(message));
		}
	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
