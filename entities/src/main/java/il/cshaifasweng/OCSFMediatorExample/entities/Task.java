package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Type_of_task;
    private LocalDateTime Creation_time;
    @ManyToOne
    @JoinColumn(name = "registered_user_id")
    private Registered_user registered_user;
    private LocalDateTime Deadline;
    private String Status;
     @OneToOne
     @JoinColumn(name = "volunteer_id")
     private User Volunteer;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Constructor

    public Task( String type_of_task, Registered_user user, LocalDateTime deadline) {
        Type_of_task = type_of_task;
        Creation_time = LocalDateTime.now();
        this.registered_user = user;
        Deadline = deadline;
        Status = "waiting for approval";
    }
    public Task( String type_of_task)
    {
        Type_of_task = type_of_task;
        Creation_time = LocalDateTime.now();
        Status = "waiting for approval";
    }

    public Task()
    {
       this.Type_of_task="help";
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
        return registered_user;
    }

    public void setUser(Registered_user user) {
        this.registered_user = user;
    }

    public LocalDateTime getDeadline() {
        return Deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        Deadline = deadline;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String situation) {
        Status = situation;
    }

    public User getVolunteer() {
        return Volunteer;
    }

    public void setVolunteer(User volunteer) {
        Volunteer = volunteer;
    }


}
