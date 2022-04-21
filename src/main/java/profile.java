import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "profile", urlPatterns =  "/profile")
public class profile extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        //doGet(request,response);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            getInfo(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void getInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = request.getParameter("username");

        PrintWriter check = response.getWriter();
        System.out.println(username);
        app_im serv = new app_im();
        info_im info = new info_im();
        int user_id = serv.getId(username);

        String label_ids = info.getLabel(user_id); // label id
        String retInterests = "";

        String[] interests =  label_ids.split(",");

        for(String interest : interests) {
            retInterests = retInterests + info.search_label(Integer.parseInt(interest)) + " ";
        }

        check.write(retInterests);
    }


    public static void main(String[] args) throws SQLException {
        app_im serv = new app_im();
        info_im info = new info_im();
        int user_id = serv.getId("ada");

        String label_ids = info.getLabel(user_id); // label id
        String retInterests = "";

        String[] interests =  label_ids.split(",");

        for(String interest : interests) {
            retInterests = retInterests + info.search_label(Integer.parseInt(interest)) + " ";
        }

        System.out.println(retInterests);
    }

}


