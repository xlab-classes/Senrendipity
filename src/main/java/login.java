import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;

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

    public void userLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        app_im serv = new app_im();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter check = response.getWriter();

        if(username.contains("@")){
            if(serv.UserExist_email(username)==true && (serv.getUser_email(username).getPassword().equals(password))){
            check.write("1");
            }else{
                check.write("0");
            }
        }else{
            if(serv.UserExist_name(username)==true && (serv.getUser_name(username).getPassword().equals(password))){
                check.write("1");
            }else{
                check.write("0");
        }
        }
        check.close();
    
    }

    
    
}
