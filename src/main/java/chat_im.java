import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class chat_im extends app_im{

    public int room_Count() throws SQLException {
        Connection conn = DButil.getConnection();
        String sql = ""+"SELECT * FROM room_table where ROOM = (select max(ROOM) from room_table)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery(sql);
        int room = 0;
        while (rs.next()){
            room = rs.getInt("ROOM");
        }
        psmt.close();
        rs.close();
        return room+1;
    }

    public int createR(String user1, String user2) throws Exception{
        Connection conn = DButil.getConnection();
        String sql = "" +
                "insert into room_table"+
                "(ROOM,user1,user2)"+
                "values(?,?,?)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        int room = room_Count();
        psmt.setInt(1,room);
        psmt.setString(2,user1);
        psmt.setString(3,user2);
        psmt.execute();
        psmt.close();
        return room;
    }


    public void addMsg(int room, int from, int to, String time, String msg) throws Exception {
        Connection conn = DButil.getConnection();
        String sql = ""
                +"Insert into chat_table" +
                "(ROOM,fid,tid,message,time,user1,user2)" +
                "values(?,?,?,?,?,?,?)";

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,room);
        psmt.setInt(2,from);
        psmt.setInt(3,to);
        psmt.setString(4,msg);
        psmt.setString(5,time);
        psmt.setInt(6,0);
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