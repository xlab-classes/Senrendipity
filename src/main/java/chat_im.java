import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class chat_im extends app_im{

    public int id_count() throws Exception {
        Connection conn = DButil.getConnection();
        String sql = ""+"SELECT * FROM chat_table where total = (select max(total) from chat_table)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery(sql);
        int id = 0;
        while (rs.next()){
            id = rs.getInt("total");
        }
        psmt.close();
        rs.close();
        return id+1;
    }

    public void addMsg(int room, int from, int to, String time, String msg) throws Exception {
        Connection conn = DButil.getConnection();
        String sql = ""
                +"Insert into chat_table" +
                "(ROOM,fid,tid,message,time,total,show_message)" +
                "values(?,?,?,?,?,?,?)";

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,room);
        psmt.setInt(2,from);
        psmt.setInt(3,to);
        psmt.setString(4,msg);
        psmt.setString(5,time);
        psmt.setInt(6,id_count());
        psmt.setInt(7,0);
        psmt.execute();
        psmt.close();
    }

    public void updateShow(int room, int from, int to) throws Exception {
        Connection conn = DButil.getConnection();
        String sql = ""
                + "update chat_table set show_message =" + 1 +" where ROOM = " + room
                +" and fid = " + from + " and tid = "+ to + " and show_message = " + 0;

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.executeUpdate();
        psmt.close();
    }

    public void delete(int room) throws SQLException{
        Connection conn = DButil.getConnection();
        String sql = "" +
                "delete from chat_table where ROOM=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, room);
        psmt.executeUpdate();
        psmt.close();
    }


}
