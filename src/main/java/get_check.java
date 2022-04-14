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
import java.util.*;


@WebServlet(name = "get_check", urlPatterns =  "/get_check")
public class get_check extends HttpServlet {
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
        int room = Integer.parseInt(request.getParameter("room"));
        String from = request.getParameter("username");
        String last = request.getParameter("last");

        PrintWriter check = response.getWriter();
        chat_im serv = new chat_im();

        JSONObject json = new JSONObject();
        List<String> sender_list= new ArrayList<String>();
        List<String> receiver_list= new ArrayList<String>();
        List<String> message_list= new ArrayList<String>();

//        re.put("fail", 0);
//        check.write(re.toJSONString());

        Connection conn = DButil.getConnection();
        String sql = "" + "select * from chat_table where ROOM = " + room + " and mesId > "+ last;
        PreparedStatement psmt2 = conn.prepareStatement(sql);
        ResultSet rs = psmt2.executeQuery();

//        //第一条消息
//        sender_list.add(rs.getString("fid"));
//        receiver_list.add(rs.getString("tid"));
//        message_list.add(rs.getString("message"));
//        if(identify=="user1"){
//            serv.updateShow1(rs.getInt("room"),rs.getInt("fid"),rs.getInt("tid"));
//        }
//        if(identify=="user2"){
//            serv.updateShow2(rs.getInt("room"),rs.getInt("fid"),rs.getInt("tid"));
//        }

        //如果出现很多消息的情况：
        app_im s = new app_im();
        while (rs.next()){
            User user = s.getUser_id(rs.getInt("fid"));
            User user2 = s.getUser_id(rs.getInt("tid"));
            sender_list.add(user.getUsername());
            receiver_list.add(user2.getUsername());
            message_list.add(rs.getString("message"));
        }

        json.put("sender",sender_list);
        json.put("receiver",receiver_list);
        json.put("message",message_list);

        System.out.println("show:"+ json.toJSONString());
        check.write(json.toJSONString());

        psmt2.close();
        rs.close();
        check.close();
    }

}