import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", urlPatterns =  "/login")
public class login extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    public login(){
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("cse442-spring2022-team-heart/front-end/script.js;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            userLogin(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        app_Design serv = new app_im();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter check = response.getWriter();
        String ret = "0";


        if (username.contains("@")) {
            if (serv.UserExist_email(username)) {
                User user = serv.getUser_email(username);
                //System.out.print("key="+user.getPassKey());

                String de_password = serv.decode(user.getPassKey(), user.getPassword());//解密
                //System.out.print("decode="+de_password);

                if (Objects.equals(de_password, password)) {
                    ret = user.getUsername();
                }
            }
            check.write(ret);
        }

        else {
            if (serv.UserExist_name(username)) {
                if (serv.UserExist_name(username)) {
                    User user = serv.getUser_name(username);
                    String de_password = serv.decode(user.getPassKey(), user.getPassword());//解密
                    //System.out.print(de_password);
                    if (Objects.equals(de_password, password)) {
                        ret = username;
                    }
                }
            }
            check.write(ret);

        }
        check.close();
    }
}
