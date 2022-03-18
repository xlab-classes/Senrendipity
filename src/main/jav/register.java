import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        boolean email_check = serv.UserExist_email(email);
        boolean username_check = serv.UserExist_name(username);
        System.out.print("email:"+ email_check + "username:"+username_check);

        String output = "fail";
        //流程
        boolean e  = false;
        boolean u = false;
        List<User> users = new ArrayList<User>();

        if (email_check || username_check){
            if (serv.UserExist_email(email)){ // email exist
                User user2 = serv.getUser_email(email);
                if (user2.getCheck()){
                    e = true;
                    output= "exist email";
                }
                //serv.deleteUser(user2.getId());
            }

            if(serv.UserExist_name(username)) { // user exist
                User user2 = serv.getUser_name(username);
                //System.out.print(user2.getCheck());
                if (user2.getCheck()){
                    u = true;
                    output= "exist username";
                }
            }
            if(u && e){
                output= "exist email and username";
            }
        }

        if(!e && !u){
            serv.sendEmail(email,user);
            serv.addUser(user);
            output= "0";
        }
        check.write(output);
        check.close();
    }
}
