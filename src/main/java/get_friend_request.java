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


@WebServlet(name = "get_friend_request", urlPatterns =  "/get_friend_request")
public class get_friend_request extends HttpServlet {
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

        PrintWriter check = response.getWriter();
        info_im serv = new info_im();
        User user = serv.getUser_name(username);

        JSONObject json = new JSONObject();

        Connection conn = DButil.getConnection();
        String sql1 = "" + "select * from user_info where id = " + user.getId() ;
        PreparedStatement psmt1 = conn.prepareStatement(sql1);
        ResultSet rs1 = psmt1.executeQuery();

        List<String> new_friend= new ArrayList<String>();

        while (rs1.next()){
            String friend_request = rs1.getString("friend_request");
            List<String> friends = Arrays.asList(friend_request.split(" "));
            for (int id = 0; id < friends.size(); id++) {
                User new_friend_user = serv.getUser_id(Integer.parseInt(friends.get(id)));
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