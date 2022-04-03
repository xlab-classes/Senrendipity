import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "forgot_password", urlPatterns =  "/forgot_password")

public class forgot_password extends HttpServlet{
    private static final long  serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

        doPost(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        //doGet(request,response);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            forgot(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void forgot(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {

        String email = request.getParameter("email");
        PrintWriter check = response.getWriter();

        app_im serv = new app_im();

        // has account
        if (serv.UserExist_email(email)){
            User user= serv.getUser_email(email);
            serv.sendEmail(email,user);
            serv.updateVCode(user,user.getV_Code());
            check.write("1");  // send email, success

        }
        else {
            check.write("0");
        }

    }
}
