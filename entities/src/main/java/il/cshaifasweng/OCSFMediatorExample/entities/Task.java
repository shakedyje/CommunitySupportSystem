package il.cshaifasweng.OCSFMediatorExample.entities;
import java.time.LocalDateTime;


public class Task {
    private static int nextId = 1; // Static variable to keep track of next available ID
    private int id;
    private String Type_of_task;
    private LocalDateTime Creation_time;
    private User user;
    private LocalDateTime Deadline;
    private String Situation;
     private User Volunteer;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructor

    public Task( String type_of_task, LocalDateTime creation_time, User user, LocalDateTime deadline, String situation, User volunteer) {
        this.id = nextId++; // Assign the next available ID and then increment it
        Type_of_task = type_of_task;
        Creation_time = creation_time;
        this.user = user;
        Deadline = deadline;
        Situation = situation;
        Volunteer = volunteer;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters and setters
    public int getId() {
        return id;
    }

    public String getType_of_task() {
        return Type_of_task;
    }

    public void setType_of_task(String type_of_task) {
        Type_of_task = type_of_task;
    }

    public LocalDateTime getCreation_time() {
        return Creation_time;
    }

    public void setCreation_time(LocalDateTime creation_time) {
        Creation_time = creation_time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDeadline() {
        return Deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        Deadline = deadline;
    }

    public String getSituation() {
        return Situation;
    }

    public void setSituation(String situation) {
        Situation = situation;
    }

    public User getVolunteer() {
        return Volunteer;
    }

    public void setVolunteer(User volunteer) {
        Volunteer = volunteer;
    }


}
