package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Registered_user;
import il.cshaifasweng.OCSFMediatorExample.entities.Task;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class SimpleChatServer {

    private static SimpleServer server;
    private static Session session;
    private static ArrayList<Registered_user> registered_users = new ArrayList<>();
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        server = new SimpleServer(3000);
        System.out.println("Server is listening");
        server.listen();
    }

    public static int getRandomNumber(int min, int max){
            return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Initialize application's users
     */
    private static void initializeUsers() {
        registered_users.add(new Registered_user("Rom","Levi","rom_levi1","123",0,"0507773121","Haifa"));
        registered_users.add(new Registered_user("Yarin","Rabinobi","yarin_rabinobi2","1234",0,"0524373191","Tel-Aviv"));
        registered_users.add(new Registered_user("Dan","Shimoni","dan_shimoni1","1235",0,"0547373199","Haifa"));
        registered_users.add(new Registered_user("Linoy","Ohaion","linoyOhaion2","1232",1,"0502213188","Jerusalem"));
        registered_users.add(new Registered_user("Roman","Shapira","romanroman","1231",0,"0521153111","Jerusalem"));
        registered_users.add(new Registered_user("Shira","Omer","ShiraOmer22","1220",0,"0502479900","Haifa"));
        registered_users.add(new Registered_user("Yarden","Mesgav","yarden_yarden3","1230",0,"Tel-Aviv"));
        registered_users.forEach(user -> session.save(user));
    }

    /**
     * Initialize application's tasks
     */
    private static void initializeTask() {
        LocalTime currentTime = LocalTime.now();
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime futureDeadline1 = now.plusDays(7);
        Task task1 = new Task("Help with supermarket shopping", currentTime, futureDeadline1, "request");
        task1.setTask(registered_users.get(0));

        LocalDateTime futureDeadline2 = now.plusDays(14);
        Task task2 = new Task("Ordering medication", currentTime, futureDeadline2, "request");
        task2.setTask(registered_users.get(1));

        LocalDateTime futureDeadline3 = now.plusDays(10);
        Task task3 = new Task("A ride somewhere", currentTime, futureDeadline3, "request");
        task3.setTask(registered_users.get(2));

        LocalDateTime futureDeadline4 = now.plusDays(3);
        Task task4 = new Task("Ordering medication", currentTime, futureDeadline4, "request");
        task4.setTask(registered_users.get(3));

        LocalDateTime futureDeadline5 = now.plusDays(1);
        Task task5 = new Task("Help with supermarket shopping", currentTime, futureDeadline5, "request");
        task5.setTask(registered_users.get(4));

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.add(task5);
        tasks.forEach(task -> session.save(task));
    }
}
