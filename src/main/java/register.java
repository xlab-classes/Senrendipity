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

    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws  Exception {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        PrintWriter check = response.getWriter();
        app_im serv = new app_im();
        /*
        User user = new User();
        user.setId(serv.id_Count());
        user.setUsername(username);

        String key = serv.generateKey();//生成key
        user.setPassKey(key);
        String en_password = serv.encrypt(key,password);//加密
        user.setPassword(en_password);
        user.setCheck(false);
        user.setEmail(email);
        //check.println(user.getUsername())；

         */

        boolean email_check = serv.UserExist_email(email);
        boolean username_check = serv.UserExist_name(username);
        //System.out.print("email:"+ email_check + "username:"+username_check);
        String output = "fail";
        //流程
        if(email_check && username_check){
            output = "exit email and exit username";
        }
        else if (email_check){ // email exist
            output= "exist email";
            //serv.deleteUser(user2.getId());
        }
        else if(username_check) { // user exist
            //System.out.print(user2.getCheck());
            output= "exist username";

        }else{
            //serv.addUser(user);
            output = serv.sendEmailR(email);
        }
        check.write(output);
        check.close();
    }
}