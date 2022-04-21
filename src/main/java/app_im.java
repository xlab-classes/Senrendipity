import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import org.apache.commons.codec.binary.Hex;
import java.util.Random;


public class app_im implements app_Design {

    //没有问题
    public void addUser(User user) throws Exception{
        //连接数据库
        Connection conn = DButil.getConnection();
        String sql = ""+
                "INSERT INTO user_table" +
                "(id,checks,username,email,password,verification,passkey)"+
                "values(?,?,?,?,?,?,?)";

        //编译sql
        PreparedStatement psmt = conn.prepareStatement(sql);

        //传参
        psmt.setInt(1, user.getId());
        psmt.setBoolean(2, user.getCheck());
        psmt.setString(3, user.getUsername());
        psmt.setString(4, user.getEmail());
        psmt.setString(5, user.getPassword());
        psmt.setString(6, user.getV_Code());
        psmt.setString(7,user.getPassKey());

        psmt.execute();
        psmt.close();
    }

    //没有问题
    //更新密码？验证码？(需要吗?)
    public void updatePassword(User user, String password) throws SQLException{
        //连接数据库
        Connection conn = DButil.getConnection();
        String sql = "" +
                "update user_table set password ='" + password+"'" +" where id=" + user.getId();
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.executeUpdate(sql);
        psmt.close();
    }

    //没有问题
    public void updateStatus(User user, boolean check) throws SQLException{
        //连接数据库
        Connection conn = DButil.getConnection();
        String sql = "" +
                "update user_table set checks="+check+" where id ="+user.getId();
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.executeUpdate(sql);
        psmt.close();

    }

    public void updateVCode(User user, String code) throws SQLException{
        //连接数据库
        Connection conn = DButil.getConnection();
        String sql = "" +
                "update user_table set verification ='" + code +"'" +" where id=" + user.getId();

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.executeUpdate(sql);
        psmt.close();
        //System.out.print("codeSQL="+user.getV_Code());
    }

    //没有问题
    //通过id查找用户
    public User getUser_id(int id) throws SQLException{
        User user = null;
        //连接数据库
        Connection conn = DButil.getConnection();
        String sql = "" +
                "SELECT * FROM user_table WHERE id=?";

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, id);
        ResultSet rs = psmt.executeQuery();
        while(rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setCheck(rs.getBoolean("checks"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setV_Code(rs.getString("verification"));
        }
        psmt.close();
        rs.close();
        return user;
    }

    @Override
    public int getId(String name) throws SQLException {
        int id = 0;
        Connection conn = DButil.getConnection();
        String sql = "" +
                "SELECT * FROM user_table WHERE username=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1,name);
        ResultSet rs = psmt.executeQuery();
        while(rs.next()){
            id = rs.getInt("id");
        }
        psmt.close();
        rs.close();
        return id;
    }

    //没有问题
    //通过邮箱查找用户
    public User getUser_email(String email) throws SQLException{
        User user =null;
        Connection conn = DButil.getConnection();
        String sql = "" +
                "SELECT * FROM user_table WHERE email=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1,  email);
        ResultSet rs = psmt.executeQuery();

        while(rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setCheck(rs.getBoolean("checks"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setV_Code(rs.getString("verification"));
            user.setPassKey(rs.getString("passkey"));
        }
        psmt.close();
        rs.close();
        return user;
    }

    //没有问题
    public User getUser_name(String name) throws SQLException{
        User user =null;
        Connection conn = DButil.getConnection();
        /*
        StringBuffer sb = new StringBuffer();
        sb.append("select * from user_table");
        sb.append("where email like ?");
        PreparedStatement psmt = conn.prepareStatement(sb.toString());
        */
        String sql = "" +
                "SELECT * FROM user_table WHERE username=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1,  name);
        ResultSet rs = psmt.executeQuery();

        while(rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setCheck(rs.getBoolean("checks"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setV_Code(rs.getString("verification"));
            user.setPassKey(rs.getString("passkey"));
        }
        psmt.close();
        rs.close();
        return user;
    }

    //没有问题
    //判断用户名是否已经存在
    //user name 搜查
    public boolean UserExist_name(String username) throws SQLException{
        Connection conn = DButil.getConnection();
        String sql = ""+"SELECT * FROM user_table WHERE username=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = null;
        try{
            psmt.setString(1, username);
            rs = psmt.executeQuery();
            boolean temp = rs.next();
            psmt.close();
            rs.close();
            return temp;
        }catch(SQLException e){
            psmt.close();
            rs.close();
            return false;
        }
    }

    //没有问题
    public boolean UserExist_email(String email) throws SQLException{
        Connection conn = DButil.getConnection();
        String sql = ""+"SELECT * FROM user_table WHERE email=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = null;
        try{
            psmt.setString(1, email);
            rs = psmt.executeQuery();
            boolean temp = rs.next();
            psmt.close();
            rs.close();
            return temp;
        }catch(SQLException e){
            psmt.close();
            rs.close();
            return false;
        }
    }

    //没有问题
    //从数据库中删除用户
    public void delete(int id) throws SQLException{
        Connection conn = DButil.getConnection();
        String sql = "" +
                "delete from user_table where id=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, id);
        psmt.executeUpdate();
        psmt.close();
    }

    //没有问题
    //获取ID
    public int id_Count() throws SQLException{
        Connection conn = DButil.getConnection();
        String sql = ""+"SELECT * FROM user_table where id = (select max(id) from user_table)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery(sql);
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        psmt.close();
        rs.close();
        return id+1;
    }


    //获取随机数
    public String getRandom() {
        Random random = new Random();
        String rs = "";
        for(int i = 0; i < 5; i++){
            rs += random.nextInt(10);
        }
        return rs;
    }

    //发送email
    @Override
    public void sendEmail(String email, User user) throws Exception {
        googleMail mail = new googleMail();
        app_im serv = new app_im();
        String temp = serv.getRandom();
        user.setV_Code(temp);
        mail.getMimeMessage(email,temp);
    }

    public String sendEmailR(String email) throws Exception {
        googleMail mail = new googleMail();
        app_im serv = new app_im();
        String temp = serv.getRandom();
        mail.getMimeMessage(email,temp);
        return temp;
    }

    public String generateKey() throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(new SecureRandom());
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] byteKey = secretKey.getEncoded();
        return Hex.encodeHexString(byteKey);

    }

    @Override
    public String encrypt(String ukey, String password) throws Exception {
        try {
            Key key = new SecretKeySpec(Hex.decodeHex(ukey.toCharArray()),"AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] res = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(res);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decode(String ukey, String code) throws Exception {
        try {
            Key key = new SecretKeySpec(Hex.decodeHex(ukey),"AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] res = cipher.doFinal(Hex.decodeHex(code));
            return  new String(res);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void truncateTable(String table_name) throws Exception {  // chat_table, label_table, room_table, user_info, user_table
        Connection conn = DButil.getConnection();
        String sql = ""+"truncate table_name=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, table_name);
        psmt.close();
        conn.close();
    }



}