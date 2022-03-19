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


        String username = request.getParameter("username");
        String resend = request.getParameter("resend");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PrintWriter check = response.getWriter();

        final app_im serv = new app_im();
        User user = new User();
        user.setId(serv.id_Count());
        user.setUsername(username);

        String key = serv.generateKey();//生成key
        user.setPassKey(key);
        String en_password = serv.encrypt(key,password);//加密
        user.setPassword(en_password);
        user.setCheck(true);
        user.setEmail(email);


        if (Objects.equals(resend, "0")) {  // is verified button
                serv.addUser(user);
                check.write("1");
        }

        else if (Objects.equals(resend, "1")){ // is resent code button
            //serv.sendEmail(user.getEmail(),user);
            //serv.updateVCode(user,user.getV_Code());
            //System.out.print("code="+user.getV_Code());
            check.write(serv.sendEmailR(email));
        }

        check.close();
    }
}
