import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Objects;


@WebServlet(name = "register_verify", urlPatterns =  "/register_verify")

public class register_verify extends HttpServlet{
    private static final long  serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
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
        app_im serv = new app_im();
        String number = request.getParameter("num");
        String username = request.getParameter("username");
        String resend = request.getParameter("resend");

        PrintWriter check = response.getWriter();
        User user = serv.getUser_name(username);
        String vcode = user.getV_Code();

        //System.out.print("vode="+vcode);
        //System.out.print("myvode="+number);
        //System.out.print("resend="+resend);


        if (Objects.equals(resend, "0")) {  // is verified button
            if(Objects.equals(number, vcode)){
                serv.updateStatus(user,true);
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
