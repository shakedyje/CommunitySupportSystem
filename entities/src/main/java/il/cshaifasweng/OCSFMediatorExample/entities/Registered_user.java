package il.cshaifasweng.OCSFMediatorExample.entities;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Registered_user extends User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "registered_user")
    private List <Task> task_list;
    private String SerialNumber;
    private int phone_numbe;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Registered_user(String userName, int password, String community, Role role, int phone_numbe) {
        super(userName,password,community,role);
        this.phone_numbe = phone_numbe;
        task_list=new ArrayList<>();
    }

    public Registered_user() {

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public int getId() {
        return id;
    }


    public List<Task> getTask_list() {
        return task_list;
    }

    public void setTask_list(List<Task> task_list) {
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
