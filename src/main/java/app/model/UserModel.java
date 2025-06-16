package app.model;

/**
 * Model representing the user currently logged in.
 */
public class UserModel {
    private int ID = 0;
    private String email = null;
    private String password = null;
    private String panel = null;
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

    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

}
