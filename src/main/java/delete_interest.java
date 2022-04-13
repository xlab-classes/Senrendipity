import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "delete_interest", urlPatterns =  "/delete_interest")
public class delete_interest extends HttpServlet {
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
        String interest = request.getParameter("interest");  // 更改之前的interest

        PrintWriter check = response.getWriter();

        app_im serv = new app_im();
        info_im info = new info_im();
        int user_id = serv.getId(username);
        String label_id;
        String condition = "1";
        interest = interest.toLowerCase();

        List<String> interests = new ArrayList();
        if(info.interest_num(info.getLabel(user_id)) != 0) {
            interests = Arrays.asList(info.getLabel(user_id).split(","));

        }
        if(interest.endsWith("s")) {
            interest = interest.substring(0, interest.length()-1);
        }
        else if(interest.endsWith("ies")) {
            interest = interest.substring(0, interest.length()-3);
            interest = interest + "y";
        }

        if(!info.label_exited(interest)) {
            condition = "-1";
        }
        else if(!interests.contains(info.search_id(interest) + "")) {
            condition = "0";
        }
        else {
            label_id = info.search_id(interest)+"";
            if(interests.contains(label_id)) {
                info.update(user_id, info.getLabel(user_id).replaceFirst(label_id + ",", ""));
            }
        }
        check.write(condition);



    }

}
