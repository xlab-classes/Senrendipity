import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "register", urlPatterns =  "/register")

public class register extends HttpServlet{
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
            registerUser(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PrintWriter check = response.getWriter();

        app_im serv = new app_im();
        User user = new User();
        user.setId(serv.id_Count());
        user.setUsername(username);
        user.setPassword(password);
        user.setCheck(false);
        user.setEmail(email);
        //check.println(user.getUsername())；

        //流程

        if(serv.UserExist_email(email) == true){
            User user2 = serv.getUser_email(email);
            //System.out.print(user2.getCheck());
            if (user2.getCheck()){
                check.write("0");
            }
            serv.deleteUser(user2.getId());
        }

        if(serv.UserExist_name(username) == true){
            User user2 = serv.getUser_name(username);
            System.out.print(user2.getCheck());
            if (user2.getCheck()){
                check.write("1");
            }
            serv.deleteUser(user2.getId());
        }

        else{
            serv.sendEmail(email,user);
            serv.addUser(user);
            check.write("2");
        }
        check.close();
    }
}
