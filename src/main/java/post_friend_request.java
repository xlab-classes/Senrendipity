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
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "post_friend_request", urlPatterns =  "/post_friend_request")
public class post_friend_request extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            postC(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postC(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String username = request.getParameter("username");
        PrintWriter check = response.getWriter();
        Connection conn = DButil.getConnection();
        info_im serv = new info_im();
        User user = serv.getUser_name(username);

        String sql = "" + "select * from user_info where id = " + user.getId() ;
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs ;
        rs = psmt.executeQuery();
        String ret = "0";

        while (rs.next()){
            String friend_request = rs.getString("friend_request");
            if(!friend_request.isEmpty()){
                ret = "1";
            }
        }
        check.write(ret);
        psmt.close();
        rs.close();
        //System.out.println("check:"+ret);

        check.close();
    }
}