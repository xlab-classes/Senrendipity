import javax.servlet.ServletException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "add_friend_response", urlPatterns =  "/add_friend_response")
public class addF extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            getFriend(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getFriend(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String  username = request.getParameter("username");
        String target_user = request.getParameter("target_user");
        String button = request.getParameter("button");// 传输1或0
        PrintWriter check = response.getWriter();
        info_im serv = new info_im();
        chat_im s = new chat_im();
        //判断1或0
        //1: 从friend_request把target_user移除,并且两个user在friend_list当中都添加好友
        //0：直接从friend_request把target_user移除
        String friend_re= serv.getRequest(serv.getId(username));
        String temp = serv.getId(target_user) + " ";
        serv.addToRequest(serv.getId(username),friend_re.replace(temp,""));
        if (button.equals("1")){
            s.addfriend(username,target_user);
        }


    }
}