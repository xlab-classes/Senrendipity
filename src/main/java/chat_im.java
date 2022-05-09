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

    public int mesID_Count(int room) throws SQLException {
        Connection conn = DButil.getConnection();
        String sql = ""+"SELECT * FROM chat_table where room = " +room +  "  and mesId = (select max(mesId) from chat_table where room= "+ room+ ")";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery(sql);
        int mesId = 0;
        while (rs.next()){
            mesId = rs.getInt("mesId");
        }
        psmt.close();
        rs.close();
        return mesId+1;
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


    public void addMsg(int room, int from, int to, String msg ,String time) throws Exception {
        Connection conn = DButil.getConnection();
        String sql = ""
                +"Insert into chat_table" +
                "(ROOM,fid,tid,message,time,mesId)" +
                "values(?,?,?,?,?,?)";

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,room);
        psmt.setInt(2,from);
        psmt.setInt(3,to);
        psmt.setString(4,msg);
        psmt.setString(5,time);
        psmt.setInt(6,mesID_Count(room));


        psmt.execute();
        psmt.close();
    }

    public void addfriend(String user1, String user2) throws Exception{
        System.out.println("add test");
        Connection conn = DButil.getConnection();
        chat_im serv = new chat_im();
        int id1 = serv.getId(user1);
        int id2 = serv.getId(user2);
        String sql = "" + "select*from user_info  where id in (" + id1 +","+id2 +")";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs1= psmt.executeQuery();
        while(rs1.next()) {
            System.out.println(rs1.getString("id"));
            String friend_list1 = rs1.getString("friend_list");
            String newFriend1 = Integer.toString(id1);
            String newFriend2 = Integer.toString(id2);

            if(rs1.getInt("id")== id1) {
                if(!rs1.getString("friend_list").contains(newFriend2)) {
                    friend_list1 += newFriend2 + " ";
                    sql = "" + "update user_info set friend_list = " + "'" + friend_list1 + "'" + "where id =" + id1;
                    psmt = conn.prepareStatement(sql);
                    psmt.executeUpdate(sql);
                }
            }
            if(rs1.getInt("id")== id2){
                if(!rs1.getString("friend_list").contains(newFriend1)) {
                    friend_list1 += newFriend1 + " ";
                    sql = "" + "update user_info set friend_list = " + "'" + friend_list1 + "'" + "where id =" + id2;
                    psmt = conn.prepareStatement(sql);
                    psmt.executeUpdate(sql);
                }
            }

        }

    }

//    public void updateShow1(int room, int from, int to) throws Exception {
//        Connection conn = DButil.getConnection();
//        String sql = ""
//                + "update chat_table set show1 =" + 1 +" where ROOM = " + room
//                +" and fid = " + from + " and tid = "+ to + " and show1 = " + 0;
//
//        PreparedStatement psmt = conn.prepareStatement(sql);
//        psmt.executeUpdate();
//        psmt.close();
//    }
//
//    public void updateShow2(int room, int from, int to) throws Exception {
//        Connection conn = DButil.getConnection();
//        String sql = ""
//                + "update chat_table set show2 =" + 1 +" where ROOM = " + room
//                +" and fid = " + from + " and tid = "+ to + " and show2 = " + 0;
//
//        PreparedStatement psmt = conn.prepareStatement(sql);
//        psmt.executeUpdate();
//        psmt.close();
//    }

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