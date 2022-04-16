import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "send_response", urlPatterns =  "/send_response")


public class send_response extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            sendR(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendR(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String from = request.getParameter("from");
        //System.out.println(request.getParameter("room"));
        String to = request.getParameter("to");
        int roomId = Integer.parseInt(request.getParameter("room"));
        String msg = request.getParameter("message");
        String time = request.getParameter("time");



        chat_im serv = new chat_im();
        int user1 = serv.getUser_name(from).getId();
        int user2 = serv.getUser_name(to).getId();

        serv.addMsg(roomId,user1,user2,msg,time);
    }
}