

public class User {

    //variables
    private int id;
    private boolean check = false;
    private String username;
    private String email;
    private String password;
    private String v_Code;

    //Constructor 
    public User(){

    }

    public User(int id, boolean check, String username, String email, String password,  String v_Code){
        this.id = id;
        this.check = check;
        this.username = username;
        this.email = email;
        this.v_Code = v_Code;
    }

    //getter and setter
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public boolean getCheck(){
        return check;
    }

    public void setCheck(boolean check){
        this.check = check;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getV_Code(){
        return v_Code;
    }

    public void setV_Code(String v_Code){
        this.v_Code = v_Code;
    }
    
}
