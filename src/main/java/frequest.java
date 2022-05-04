import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "add_friend_request", urlPatterns =  "/add_friend_request")
public class frequest extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            getRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String user = request.getParameter("username");
        String input = request.getParameter("input");
        info_im serv = new info_im();
        PrintWriter check = response.getWriter();
        JSONObject re = new JSONObject();
        int user_id = serv.getId(user);
        int target_id = 0;

        //判断是邮箱还是username
        //判断input的用户是否存在数据库
        if(input.contains("@") && serv.UserExist_email(input)){
            target_id = serv.getUser_email(input).getId();
        }else if(serv.UserExist_name(input)){
            target_id = serv.getUser_name(input).getId();
        }else {
            //如果不存在就return fail, " this user doesn't exist."
            re.put("fail","This user doesn't exist");
            check.write(re.toJSONString());
            check.close();
            return;
        }
        //判断input用户是否是好友关系，是return fail，"this user is already ur friend"，
        if (serv.friendExist(user_id,target_id) && serv.friendExist(target_id,user_id)){
             re.put("fail","This user already is your friend");
             check.write(re.toJSONString());
        }else {
            // 不是好友就把input的id放到friend_request，不准有重复的id
            if (serv.friendRequest(user_id,target_id) && serv.friendRequest(target_id,user_id)){
                re.put("fail", "You have sent a request to this user before");
                check.write(re.toJSONString());
            }else {
                // "success: this user has been added."
                String temp = serv.getRequest(target_id);
                temp += user_id + " ";
                serv.addToRequest(target_id,temp);
                re.put("success", "Your request has been sent");
                check.write(re.toJSONString());
            }
        }
        check.close();;
        /*
        if(input.contains("@") && serv.UserExist_email(input)){
            int target_id = serv.getUser_email(input).getId();
            //判断input用户是否是好友关系，是return fail，"this user is already ur friend"，

            if (serv.friendExist(user_id,target_id) && serv.friendExist(target_id,user_id)){
                re.put("fail","This user is already your friend");
                check.write(re.toJSONString());

            }else {
                // 不是好友就把input的id放到friend_request，不准有重复的id
                if (serv.friendRequest(user_id,target_id) && serv.friendRequest(target_id,user_id)){
                    re.put("fail", "You have sent a request to this user before");
                    check.write(re.toJSONString());
                }else {
                    String temp = serv.getRequest(user_id);
                    temp += target_id + " ";
                    serv.addToRequest(user_id,temp);
                    re.put("success", "Your request has been sent");
                    check.write(re.toJSONString());
                }
            }
        }else if (serv.UserExist_name(input)){
            int target_id = serv.getUser_name(input).getId();
            if(serv.friendExist(user_id, target_id) && serv.friendExist(target_id,user_id)){
                re.put("fail","This user is already your friend");
                check.write(re.toJSONString());
            }else {
                if (serv.friendRequest(user_id,target_id) && serv.friendRequest(target_id,user_id)){
                    re.put("fail", "You have sent a request to this user before");
                    check.write(re.toJSONString());
                }else {
                    String temp = serv.getRequest(user_id);
                    temp += target_id + " ";
                    serv.addToRequest(user_id,temp);
                    re.put("success", "Your request has been sent");
                    check.write(re.toJSONString());
                }

            }
        }else {
            //如果不存在就return fail, " this user doesn't exist."
            re.put("fail","This user doesn't exist");
            check.write(re.toJSONString());
        }
        */



    }
}
