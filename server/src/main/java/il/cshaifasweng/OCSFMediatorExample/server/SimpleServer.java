package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Task;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.SubscribedClient;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;

public class SimpleServer extends AbstractServer {

    private static Session session;
    private static ArrayList<SubscribedClient> SubscribersList = new ArrayList<>();

    public SimpleServer(int port) {
        super(port);

    }

    public List<Task> getAllTasks(Session session) {
        // Use HQL to retrieve all tasks
        Query<Task> query = session.createQuery("FROM Task", Task.class);
        return query.getResultList();
    }

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();

        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Registered_user.class);
        configuration.addAnnotatedClass(Task.class);


        ServiceRegistry serviceRegistry = new
                StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        System.out.println("get into handle from client in server class");
        Task myTask = null;
        String request = null;
        if (msg instanceof String) {
            System.out.println("in string inst");

            request = (String) msg;
        } else if (msg instanceof MessageOfStatus) {
            System.out.println("hello00");
            request = ((MessageOfStatus) msg).getChangeStatus();
            myTask = ((MessageOfStatus) msg).getTask();
        } else if (msg instanceof Message) {
            System.out.println("in Message inst");
            request = ((Message) msg).getMessage();
        }
        else System.out.println("is nothing");

        System.out.println(request);

        try {
            if (request.isBlank()) {
                System.out.println("isblank");

            } else if (request.equals("change status")) {
                System.out.println("in change status");
                int id = myTask.getId();

                SessionFactory sessionFactory = FactoryUtil.getSessionFactory();
                session = sessionFactory.openSession();

                Transaction tx2 = null; /////////////////////////////////////////////////////////////////////////////////
                try {
                    tx2 = session.beginTransaction();

                    // Perform operations with the second session
                    Task task = session.get(Task.class, id);

                    // Check if the entity exists
                    if (task != null) {
                        // Modify the properties of the entity
                        task.setStatus("not performed yet");
                        System.out.println("not null");

                        // Save the changes by committing the transaction
                        tx2.commit();

                        MessageOfStatus message2 = new MessageOfStatus(task, "the change completed");
                        // Echo back the received message to the client

                        client.sendToClient(message2);
                        tx2.commit();
                    }
                } catch (RuntimeException e) {
                    if (tx2 != null) tx2.rollback();
                    throw e;
                } finally {
                    session.close(); // Close the second session
                }
            } else if (request.equals("display tasks")) {
                System.out.println("in desplayyyyyyyy");


                SessionFactory sessionFactory = FactoryUtil.getSessionFactory();
                session = sessionFactory.openSession();

                Transaction tx2 = null;
                try {
                    tx2 = session.beginTransaction();
                    List<Task> tasks = getAllTasks(session);
                    DisplayTasksMassage dis = new DisplayTasksMassage(tasks);
                    client.sendToClient(dis);
                    tx2.commit();
                } catch (RuntimeException e) {
                    if (tx2 != null) tx2.rollback();
                    throw e;
                } finally {
                    session.close(); // Close the second session
                }
            } else if (request.equals("Confirm information")) {
                System.out.println("in sever /Confirm information ");


                String username = ((Message) msg).getUserName();
                String password = ((Message) msg).getPassword();
                System.out.println(username +"    "+password);


                SessionFactory sessionFactory = FactoryUtil.getSessionFactory();
                session = sessionFactory.openSession();
                Transaction tx2 = null;

                try {
                    tx2 = session.beginTransaction();

                    // Perform operations with the second session
                    System.out.println("Confirm");
                    // Use a query to get all users
                    List<Registered_user> users = session.createQuery("FROM Registered_user", Registered_user.class).getResultList();
                    // Check if the entity exists
                    if (users != null) {

                        Message message2=null;
                        for (Registered_user user : users) {

                            if (user.getUsername().equals(username)) {
                                if (user.getPassword().equals(password)) {
                                    message2 = new Message("correct");
                                    message2.setUser(user);
                                    System.out.println("correct");

                                } else {
                                    message2 = new Message("wrongPassword");
                                    System.out.println("wrongPassword");

                                }
                            }
                        }
                        if(message2 == null)
                            message2 = new Message("user is not exist");
                        client.sendToClient(message2);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("in else");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}