package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class Notification implements Serializable {
    private String notification;
    private Registered_user addressee;

    private Registered_user userInvolved;
    private  Task taskInvolved;

    public Task getTaskInvolved() {
        return taskInvolved;
    }

    public void setTaskInvolved(Task taskInvolved) {
        this.taskInvolved = taskInvolved;
    }

    public Registered_user getUserInvolved() {
        return userInvolved;
    }

    public void setUserInvolved(Registered_user userInvolved) {
        this.userInvolved = userInvolved;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Registered_user getAddressee() {
        return addressee;
    }

    public void setAddressee(Registered_user addressee) {
        this.addressee = addressee;
    }

    public Notification(String notification, Registered_user addressee) {
        this.notification = notification;
        this.addressee = addressee;
    }

    public Notification(String notification, Registered_user addressee,Registered_user userInvolved, Task task) {
        this.notification = notification;
        this.addressee = addressee;
        this.taskInvolved=task;
        this.userInvolved=userInvolved;
    }

    public Notification(String notification, Registered_user addressee, Registered_user userInvolved) {
        this.notification = notification;
        this.addressee = addressee;
        this.userInvolved = userInvolved;
    }
}
