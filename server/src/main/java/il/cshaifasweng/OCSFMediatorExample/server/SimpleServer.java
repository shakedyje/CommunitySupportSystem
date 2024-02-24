package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.MessageOfStatus;
import il.cshaifasweng.OCSFMediatorExample.entities.Task;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.SubscribedClient;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SimpleServer extends AbstractServer {
	private static ArrayList<SubscribedClient> SubscribersList = new ArrayList<>();

	public SimpleServer(int port) {
		super(port);
		
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		MessageOfStatus message1 = (MessageOfStatus) msg;
		Message message = (Message) msg;
		String request = message.getMessage();
		try {
			//we got an empty message, so we will send back an error message with the error details.
//			if (request.isBlank()){
//				message.setMessage("Error! we got an empty message");
//				client.sendToClient(message);
//			}
//			//we got a request to change submitters IDs with the updated IDs at the end of the string, so we save
//			// the IDs at data field in Message entity and send back to all subscribed clients a request to update
//			//their IDs text fields. An example of use of observer design pattern.
//			//message format: "change submitters IDs: 123456789, 987654321"
//			else if(request.startsWith("change submitters IDs:")){
//				message.setData(request.substring(23));
//				message.setMessage("update submitters IDs");
//				sendToAllClients(message);
//			}
//			//we got a request to add a new client as a subscriber.
//			else if (request.equals("add client")){
//				SubscribedClient connection = new SubscribedClient(client);
//				SubscribersList.add(connection);
//				message.setMessage("client added successfully");
//				client.sendToClient(message);
//			}
//			//we got a message from client requesting to echo Hello, so we will send back to client Hello world!
//			else if(request.startsWith("echo Hello")){
//				message.setMessage("Hello World!");
//				client.sendToClient(message);
//			}
//			else if(request.startsWith("send Submitters IDs")){
//				//add code here to send submitters IDs to client
//				message.setMessage("209402031, 323033035");
//				client.sendToClient(message);
//			}
//			else if (request.startsWith("send Submitters")){
//				//add code here to send submitters names to client
//				message.setMessage("Yair, Janan");
//				client.sendToClient(message);
//			}
//			else if (request.equals("what’s the time?")) {
//				//add code here to send the time to client
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//				LocalTime currentTime = LocalTime.now();
//				String formattedTime= currentTime.format(formatter);
//				message.setMessage(formattedTime);
//				client.sendToClient(message);
//
//
//
//			}
//			else if (request.startsWith("multiply")) {
//				//add code here to multiply 2 numbers received in the message and send result back to client
//				//(use substring method as shown above)
//				//message format: "multiply n*m"
//				String twoParameters = request.substring(9);
//				twoParameters = twoParameters.replaceAll("\\*", " * ");
//				String[] parts = twoParameters.split("\\s+");
//				int n = Integer.parseInt(parts[0]);
//				int m = Integer.parseInt(parts[2]);
//				int result = n * m;
//				String stringResult = Integer.toString(result);
//				message.setMessage(stringResult);
//				client.sendToClient(message);
//				System.out.println(stringResult);
//
                if (message1.getChangeStatus().startsWith("change status")) {
					int entityId = message1.getTask().getId();

					Configuration configuration = new Configuration().configure();
					try (SessionFactory sessionFactory = configuration.buildSessionFactory();
						 Session session = sessionFactory.openSession()) {

						// Begin a transaction
						Transaction transaction = session.beginTransaction();

						// Load the entity from the database
						Task task = session.get(Task.class, entityId);

						// Check if the entity exists
						if (task != null) {
							// Modify the properties of the entity
							task.setStatus("not performed yet");

							// Save the changes by committing the transaction
							transaction.commit();

							MessageOfStatus message2 = new MessageOfStatus(task, "the change completed");
							// Echo back the received message to the client

							client.sendToClient(message2);
						} else {
							System.out.println("Entity not found with id: " + entityId);
						}
					}
				}
				else if (request.equals("display tasks")) {
					Configuration configuration = new Configuration().configure();
					try (SessionFactory sessionFactory = configuration.buildSessionFactory();
						 Session session = sessionFactory.openSession()) {

						// Begin a transaction
						Transaction transaction = session.beginTransaction();

						// Create a CriteriaBuilder and CriteriaQuery
						CriteriaBuilder builder = session.getCriteriaBuilder();
						CriteriaQuery<Task> query = builder.createQuery(Task.class);
						Root<Task> root = query.from(Task.class);

						// Set the query to select all tasks
						query.select(root);

						// Execute query and retrieve results
						List<Task> results = session.createQuery(query).getResultList();

						// Commit the transaction
						transaction.commit();
						if (results.isEmpty())
							// Send a message to the client indicating no tasks
							client.sendToClient(results);

						}
					}
					else{}


//					} else {
//					//add code here to send received message to all clients.
//					//The string we received in the message is the message we will send back to all clients subscribed.
//					//Example:
//					// message received: "Good morning"
//					// message sent: "Good morning"
//					//see code for changing submitters IDs for help
//					message.setMessage(request);
//					sendToAllClients(message);
//				}
//
//			}
		} catch (IOException e1) {
			e1.printStackTrace();//
		}
	}

	/*public void sendToAllClients(Message message) {
		try {
			for (SubscribedClient SubscribedClient : SubscribersList) {
				SubscribedClient.getClient().sendToClient(message);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}*/

}
