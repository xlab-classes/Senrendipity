import java.sql.*;

public class DButil {

    //128.205.36.4
    private static final String URL = "jdbc:mysql://oceanus.cse.buffalo.edu:3306/cse442_2022_spring_team_m_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimeZone=UTC";
    private static final String NAME = "jweng6";
    private static final String PASSWORD = "50311675";

    private static Connection conn = null;
    static{
        try{
            //动态加载Mysql驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        System.out.print("connect");
        return conn;
    }

}
