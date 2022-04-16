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


@WebServlet(name = "get_friend_list", urlPatterns =  "/get_friend_list")
public class get_friend_list extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            get_show(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get_show(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = request.getParameter("username");
        int friend_show = Integer.parseInt(request.getParameter("friend_show"));

        PrintWriter check = response.getWriter();
        info_im serv = new info_im();
        User user = serv.getUser_name(username);

        JSONObject json = new JSONObject();
//        List<String> last_message_list= new ArrayList<String>();


        // get new friend:
        Connection conn = DButil.getConnection();
        String sql1 = "" + "select * from user_info where id = " + user.getId() ;
        PreparedStatement psmt1 = conn.prepareStatement(sql1);
        ResultSet rs1 = psmt1.executeQuery();

        List<String> new_friend= new ArrayList<String>();

        while (rs1.next()){
            String friend_list = rs1.getString("friend_list");
            List<String> friends = Arrays.asList(friend_list.split(" "));
            List<String> new_friend_ID = friends.subList(friend_show, friends.size());
            for (int id = 0; id < new_friend_ID.size(); id++) {
                User new_friend_user = serv.getUser_id(Integer.parseInt(new_friend_ID.get(id)));
                new_friend.add(new_friend_user.getUsername());
            }

        }

        //System.out.println(new_friend);
        json.put("friend",new_friend);
        psmt1.close();
        rs1.close();

        System.out.println(json.toJSONString());
        check.write(json.toJSONString());

        check.close();
    }

}