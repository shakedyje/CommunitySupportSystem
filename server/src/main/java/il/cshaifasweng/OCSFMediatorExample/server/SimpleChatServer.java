
package il.cshaifasweng.OCSFMediatorExample.server;



import il.cshaifasweng.OCSFMediatorExample.entities.Registered_user;
import il.cshaifasweng.OCSFMediatorExample.entities.Task;

import org.hibernate.SessionFactory;

import java.io.IOException;


import org.hibernate.Session;

import java.time.LocalDateTime;

public class SimpleChatServer
{


    private static Session session;
    private static SimpleServer server;

 /*   private static SessionFactory getSessionFactory() throws HibernateException
    {
        Configuration configuration = new Configuration();

        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Registered_user.class);
        configuration.addAnnotatedClass(Task.class);


        ServiceRegistry serviceRegistry = new
                StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }*/


    public static void main( String[] args ) throws IOException
    {
        server = new SimpleServer(3000);

        DatabaseChecker DB = new DatabaseChecker();
        SessionFactory sessionFactory = FactoryUtil.getSessionFactory();

        if (!DB.isDatabaseNotEmpty()) {
            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
                System.out.println("1");

                Registered_user user1 = new Registered_user("Rom","Levi","rom_levi1","123",false,"0507773121","Haifa");
                Registered_user user2 = new Registered_user("Yarin","Rabinobi","yarin_rabinobi2","1234",false,"0524373191","Tel-Aviv");
                Registered_user user3 = new Registered_user("Dan","Shimoni","dan_shimoni1","1235",false,"0547373199","Haifa");
                Registered_user user4 = new Registered_user("Linoy","Ohaion","linoyOhaion2","1232",true,"0502213188","Jerusalem");
                Registered_user user5 = new Registered_user("Roman","Shapira","romanroman","1231",false,"0521153111","Jerusalem");
                Registered_user user6 = new Registered_user("Shira","Omer","ShiraOmer22","1220",false,"0502479900","Haifa");
                Registered_user user7 = new Registered_user("Yarden","Mesgav","yarden_yarden3","1230",false,"0532251580","Tel-Aviv");


                session.save(user1);
                session.save(user2);
                session.save(user3);
                session.save(user4);
                session.save(user5);
                session.save(user6);
                session.save(user7);
                //  session.getTransaction().commit(); // Save everything.

                System.out.println("2");


                session.flush();

                LocalDateTime now = LocalDateTime.now();
                LocalDateTime futureDeadline1 = now.plusDays(7);
                Task t1 = new Task("Help with supermarket shopping",user1 , futureDeadline1);
                LocalDateTime futureDeadline2 = now.plusDays(4);
                Task t2 = new Task("Ordering medication",user2 , futureDeadline2);
                LocalDateTime futureDeadline3 = now.plusDays(12);
                Task t3 = new Task("A ride somewhere",user3 , futureDeadline3);
                LocalDateTime futureDeadline4 = now.plusDays(1);
                Task t4 = new Task("Babysitter",user4 , futureDeadline4);
                LocalDateTime futureDeadline5 = now.plusDays(10);
                Task t5 = new Task("Help with supermarket shopping",user5 , futureDeadline5);





                System.out.println("3");


                session.save(t1);
                System.out.println("4");


                session.save(t2);
                session.save(t3);
                session.save(t4);
                session.save(t5);

                session.flush();
                System.out.println("5");

                session.getTransaction().commit(); // Save everything.
            } catch (Exception exception) {
                if (session != null) {
                    session.getTransaction().rollback();
                }
                System.err.println("An error occured, changes have been rolled back.");
                exception.printStackTrace();
            } finally {
                session.close();
            }
        }
        System.out.println("server is listening");
        server.listen();
    }
}
