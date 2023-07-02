package cn.edu.hzcu.ky.model;

public class BeanAdministrator {
    private String AdministratorID;
    private String Name;
    private String LoginPassword;

    public String getAdministratorID() {
        return AdministratorID;
    }
    public void setAdministratorID(String administratorID) {
        AdministratorID = administratorID;
    }
    public String getName() {
        return Name;
    }
    public void setLoginPassword(String loginPassword) {
        LoginPassword = loginPassword;
    }
    public String getLoginPassword() {
        return LoginPassword;
    }
    public void setName(String name) {
        Name = name;
    }
}
