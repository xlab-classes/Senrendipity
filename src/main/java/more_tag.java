import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "more_tag", urlPatterns = "/more_tag")
public class more_tag extends HttpServlet {
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
            more(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void more(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter check = response.getWriter();

        info_im info = new info_im();

        String ret = "";
        for(int i = 0; i < info.all_labels().size(); i++) {
            ret = ret + info.all_labels().get(i) + ", ";
        }
        if(!ret.isEmpty()) {
            ret = ret.substring(0, ret.length()-2);
        }

        check.write(ret);

    }

}