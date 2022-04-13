import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "done_btn", urlPatterns =  "/done_btn")
public class done_btn extends HttpServlet {
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

        app_im serv = new app_im();
        info_im info = new info_im();
        int user_id = serv.getId(username);

        if(info.getLabel(user_id) == null || info.getLabel(user_id).isEmpty()) {
            check.write("0");
        }
        else {
            check.write("1");
        }



    }


}
