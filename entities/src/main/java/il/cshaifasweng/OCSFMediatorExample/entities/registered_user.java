package il.cshaifasweng.OCSFMediatorExample.entities;
import java.util.ArrayList;
import java.util.List;

public class registered_user extends User
{
    private static int nextId = 1; // Static variable to keep track of next available ID
    private int id;
    private Task task_list;
    private String SerialNumber;
    private int phone_numbe;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public registered_user(String userName, int password, String community, Role role, int phone_numbe) {
        super(userName,password,community,role);
        this.phone_numbe = phone_numbe;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public int getId() {
        return id;
    }


    public Task getTask_list() {
        return task_list;
    }

    public void setTask_list(Task task_list) {
        this.task_list = task_list;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public int getPhone_numbe() {
        return phone_numbe;
    }

    public void setPhone_numbe(int phone_numbe) {
        this.phone_numbe = phone_numbe;
    }
}
