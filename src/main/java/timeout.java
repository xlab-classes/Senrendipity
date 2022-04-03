import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


@WebServlet(name = "timeout", urlPatterns =  "/timeout")

public class timeout extends HttpServlet{
    private static final long  serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            outs(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void outs(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
        final app_im serv = new app_im();
        String username = request.getParameter("username");
        String output = "0";
        PrintWriter check = response.getWriter();
        User user = serv.getUser_name(username);

        if (!user.getCheck()){
            serv.delete(user.getId());
            output="1";
        }
        check.write(output);
        check.close();
    }
}
