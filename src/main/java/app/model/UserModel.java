package app.model;

public class UserModel {
    private int ID;
    private String email;
    private String password;
    private String panel;
    private String nameBranch = null;

    public UserModel(int id, String email, String password, String panel, String nameBranch){
        this.ID = id;
        this.email = email;
        this.password = password;
        this.panel = panel;
        this.nameBranch = nameBranch;
    }

    public int getID(){ return ID;}
    public String getEmail(){return email;}
    public String getPassword(){ return password;}
    public String getPanel(){return panel;}
    public String getNameBranch(){return nameBranch;}
}
