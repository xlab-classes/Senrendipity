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
import java.sql.SQLException;
@WebServlet(name = "post_check", urlPatterns =  "/post_check")
public class post_check extends HttpServlet {

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
        int last = Integer.parseInt(request.getParameter("last")); // mesID

        PrintWriter check = response.getWriter();
        Connection conn = DButil.getConnection();
        String sql = "" + "select * from chat_table where ROOM = " + room + " and mesId > "+ last;
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs ;

        String ret = "0";

        rs = psmt.executeQuery();
        boolean tmp = rs.next();

        if(tmp){
            ret = "1";
        }
        psmt.close();
        rs.close();
        //System.out.println("check:"+ret);
        check.write(ret);
        check.close();
    }
}