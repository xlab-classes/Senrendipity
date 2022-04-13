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


@WebServlet(name = "add_interest", urlPatterns =  "/add_interest")
public class add_interest extends HttpServlet {
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
        /*
           1.拿到wjj传输过来的数据
           4.先去label_table里看wjj传过来的label是否存在
                两种情况：
                存在就直接调用id
                不存在就调用addLabel新的label名字加入到数据库，并调用它的id
           3.
           调用interest_num方法来查看用户是否已经有interest了
                也要看是否interest已经满了
                如果满的就printwrite一个什么东西给wjj

           5.如果用户没有interest，就一个一个把label num后面 + 逗号（调用addInfo方法）放进user_info table里面
           6.前提条件: 看用户的interest里是否已经存在了interest 如果存在返回一个啥给wjj
           如果用户已经存在interest,就取用户的interest 再用 + 连接新的东西
              如果成功的话就传啥
              失败传啥
         */

        String username = request.getParameter("username");
        String interest = request.getParameter("interest");  // 更改之前的interest

        PrintWriter check = response.getWriter();
        String condition = "1";
        app_im serv = new app_im();
        info_im info = new info_im();
        int user_id = serv.getId(username);
        int label_id;
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

        if(info.label_exited(interest)) {
            label_id = info.search_id(interest);
            if(info.interest_num(info.getLabel(user_id)) == 0) {
                info.addInfo(user_id, label_id + ",", 0); // 用户没有interest
            }
            else if(info.interest_num(info.getLabel(user_id)) >= 11) {
                condition = "-1";   // label满了
            }
            else if(interests.contains(Integer.toString(label_id))) {
                condition = "0";      // label已存在
            }
            else {
                info.update(user_id, info.getLabel(user_id) + label_id + ","); // 更新用户interest
            }

        }
        else {
            info.addLabel(interest);
            label_id = info.search_id(interest);
            if(info.interest_num(info.getLabel(user_id)) == 0) {
                info.addInfo(user_id, label_id + ",", 0); // 用户没有interest
            }
            else if(info.interest_num(info.getLabel(user_id)) >= 11) {
                condition = "-1";   // label满了
            }
            else if(interests.contains(Integer.toString(label_id))) {
                condition = "0";      // label已存在
            }
            else {
                info.update(user_id, info.getLabel(user_id) + label_id + ","); // 更新用户interest
            }
        }

        check.write(condition);

    }


}
