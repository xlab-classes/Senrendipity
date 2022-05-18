import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@WebServlet(name = "unfriend", urlPatterns =  "/unfriend")
public class unfriend extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            unfriend(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unfriend(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String target_user =request.getParameter("target_user");
        String current_user =request.getParameter("current_user");
        int room = Integer.parseInt(request.getParameter("room"));

        //delete_room_table
        Connection conn = DButil.getConnection();
        String sql = "" +
                "delete from room_table where ROOM=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, room);
        psmt.executeUpdate();
        psmt.close();

        //delete chat table
        chat_im serv = new chat_im();
        serv.delete(room);

        //delete user_info -> friend_list
        User c_user= serv.getUser_name(current_user);
        User t_user= serv.getUser_name(target_user);
        int id1 = c_user.getId();
        int id2 = t_user.getId();
        Connection conn2 = DButil.getConnection();
        String sql2 = "" + "select*from user_info  where id in (" + id1 +","+id2 +")";
        PreparedStatement psmt2 = conn2.prepareStatement(sql2);
        ResultSet rs1= psmt2.executeQuery();
        while(rs1.next()) {
            if(rs1.getInt("id")== id1) {
                String friend_list1 = rs1.getString("friend_list").trim();
                List<String> friends = Arrays.asList(friend_list1.split(" "));
                String result = "";
                for (int i = 0 ; i < friends.size(); i++){
                    if(!Objects.equals(friends.get(i), Integer.toString(id2)) ){
                        result += friends.get(i)+' ';
                    }
                }
                sql2 = "" + "update user_info set friend_list = " + "'" + result + "'" + "where id =" + id1;
                psmt2 = conn2.prepareStatement(sql2);
                psmt2.executeUpdate(sql2);
            }

            if(rs1.getInt("id")== id2) {
                String friend_list2 = rs1.getString("friend_list").trim();
                List<String> friends2 = Arrays.asList(friend_list2.split(" "));
                String result2 = "";

                for (int i = 0 ; i < friends2.size(); i++){
                    if(!Objects.equals(friends2.get(i), Integer.toString(id1))){
                        result2 += friends2.get(i)+' ';
                    }
                }
                sql2 = "" + "update user_info set friend_list = " + "'" + result2 + "'" + "where id =" + id2;
                psmt2 = conn2.prepareStatement(sql2);
                psmt2.executeUpdate(sql2);
            }
        }
        psmt2.close();
    }
}