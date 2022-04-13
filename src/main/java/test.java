import java.io.PrintWriter;
import java.sql.*;
import java.util.*;


public class test {
    public static void main(String[] args) throws Exception{
        User b = new User();
        info_im a = new info_im();
        String s = "()";
        String w = "{[]}([)]";
        chat_im serv = new chat_im();


    }

    public static int romanToInt(String a, String b) {
        int aL = a.length() - 1;
        int bL = b.length() - 1;

        while(aL >= 0  && bL >= 0){

            aL--;
            bL--;
        }
        return aL;
    }

    public static void c(int room) throws Exception {
        Connection conn = DButil.getConnection();
        String sql = "" + "select * from chat_table where ROOM = " + room + " and show_message = 0";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        if(rs.next()){
            String re = rs.getString("message");
            System.out.println(re);
        }else System.out.println("213123");
    }


}
