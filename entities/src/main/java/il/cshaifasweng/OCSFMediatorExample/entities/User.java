package il.cshaifasweng.OCSFMediatorExample.entities;

// Entity:Abstract User
enum Role {User,Manager}

abstract class User
{
    private String UserName;
    private int Password;
    private String community;
    private Role role;
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public User(String userName, int password, String community, Role role) {
        UserName = userName;
        Password = password;
        this.community = community;
        this.role = role;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getPassword() {
        return Password;
    }

    public void setPassword(int password) {
        Password = password;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
