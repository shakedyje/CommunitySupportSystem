package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    private int id;
    private String Type_of_task;
    private LocalDateTime Creation_time;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Registered_user user;
    private LocalDateTime Deadline;
    private String Status;
    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Registered_user volunteer;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructor
    public Task( String type_of_task, LocalDateTime creation_time, Registered_user user, LocalDateTime deadline, String situation, Registered_user volunteer) {
        Type_of_task = type_of_task;
        Creation_time = creation_time;
        this.user = user;
        Deadline = deadline;
        Status = Status;
        Volunteer = volunteer;
    }

    public Task() {

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

    public Registered_user getUser() {
        return user;
    }

    public void setUser(ser user) {
        this.ruser = user;
    }

    public LocalDateTime getDeadline() {
        return Deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        Deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }

    public User getVolunteer() {
        return Volunteer;
    }

    public void setVolunteer(User volunteer) {
        Volunteer = volunteer;
    }


}