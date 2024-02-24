package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;

// Entity:Abstract User


@Entity
abstract class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    private String UserName;
    private String Password;
    private String community;
    private String role;


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public User(String userName, String password, String community, String role) {
        UserName = userName;
        Password = password;
        this.community = community;
        this.role = role;
    }

    public User() {

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
