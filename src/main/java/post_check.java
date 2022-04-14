import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet(name = "post_check", urlPatterns =  "/post_check")
public class post_check {

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
        int room = Integer.parseInt(request.getParameter("room"));
        PrintWriter check = response.getWriter();
        Connection conn = DButil.getConnection();
        String sql = "" + "select * from chat_table where ROOM = " + room + " and show_message = 0";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs;
        try{
            rs = psmt.executeQuery();
            boolean temp = rs.next();
            String re = "0";
            if(temp == true) re = "1";
            psmt.close();
            rs.close();
            check.write(re);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}