import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "users_interest", urlPatterns = "/users_interest")
public class users_interest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet(request,response);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            user_interest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void user_interest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = request.getParameter("username");
        PrintWriter check = response.getWriter();

        app_im serv = new app_im();
        info_im info = new info_im();
        int user_id = serv.getId(username);
        String ret = "";

        if(info.getLabel(user_id) != null && !info.getLabel(user_id).isEmpty()) {
            String[] buff = info.getLabel(user_id).split(",");
            for(int i=0;i<buff.length;i++){
                ret = ret + info.search_label(Integer.parseInt(buff[i])) + ", ";
            }
        }
        if(!ret.isEmpty()) {
            ret = ret.substring(0, ret.length()-2);
        }
        check.write(ret);
    }

}