import java.sql.SQLException;



public interface app_Design{

    public void addUser(User user) throws Exception;

    public void updatePassword(User user, String password) throws SQLException;

    public void updateStatus(User user, boolean check) throws SQLException;

    public User getUser_id(int id) throws SQLException;

    public int getId(String name) throws SQLException;

    public User getUser_email(String email) throws SQLException;

    public User getUser_name(String name) throws SQLException;

    public boolean UserExist_email(String email) throws SQLException; 

    public boolean UserExist_name(String name) throws SQLException;

    public String getRandom();

    public void delete(int id) throws SQLException;

    public int id_Count() throws SQLException;

    public void sendEmail(String email, User user) throws Exception;

    public String encrypt(String ukey,String password) throws Exception;

    public String decode(String ukey, String code) throws Exception;

}
