import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Objects;


@WebServlet(name = "forgot_verify", urlPatterns =  "/forgot_verify")

public class forgot_verify extends HttpServlet{

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
            verify(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void verify(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {

        String email = request.getParameter("email");
        String number = request.getParameter("num");
        String resend = request.getParameter("resend");
        String password = request.getParameter("password");
        PrintWriter check = response.getWriter();

        app_im serv = new app_im();
        User user = serv.getUser_email(email);
        // has account

        if (Objects.equals(resend, "0")) {  // is complete button
            if(Objects.equals(number, user.getV_Code())){
                serv.updatePassword(user,password);
                check.write("1");
            }
            else {
                check.write("0");
            }
        }


        if (Objects.equals(resend, "1")){ // is resent code button
            serv.sendEmail(user.getEmail(),user);
            serv.updateVCode(user,user.getV_Code());
            //System.out.print("code="+user.getV_Code());

            check.write("1");
        }
        check.close();



    }
}
