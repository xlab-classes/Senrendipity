import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class interest {
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

    private static boolean is_id(String label) {
        boolean ret = false;
        try{
            Integer.parseInt(label);
            ret = true;
        }catch(Exception e) {
        }
        return ret;
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
        String interest = request.getParameter("interest");

        PrintWriter check = response.getWriter();

        app_im serv = new app_im();
        info_im info = new info_im();
        int user_id = serv.getId(username);
        String label_id;


        if(is_id(interest) == true) {
            if(info.getLabel(user_id) == null) {
                info.addInfo(user_id, interest + "," , 0);
                check.write("1");  // 用户没有interest，并更新用户的interest
            }
            else if(info.getLabel(user_id).contains(interest)) {
                check.write("0");  // label已存在
            }
            else if(info.interest_num(info.getLabel(user_id)) == 10) {
                check.write("-1"); // label满了
            }
            else {
                info.update(user_id, info.getLabel(user_id) + interest + ",");
                check.write("2");  // 用户已有interest, 并更新用户的interest
            }
        }
        else {
            info.addLabel(interest);
            label_id = info.search_id(interest) + ",";
            if(info.getLabel(user_id) == null) {
                info.addInfo(user_id, interest + "," , 0);
                check.write("1");  // 用户没有interest，并更新用户的interest
            }
            if(info.interest_num(info.getLabel(user_id)) == 10) {
                check.write("-1"); // label已满
            }
            else{
                info.update(user_id, info.getLabel(user_id) + label_id);
                check.write("2"); // 用户已有interest, 并更新用户的interest
            }
        }


        }


//    public static void main(String[] args) throws Exception {
//        String username = "hx";
//        String interest = "Painting";
//
//
//        app_im serv = new app_im();
//        info_im info = new info_im();
//        int user_id = serv.getId(username);
//        String label_id;
//
//        if(is_id(interest) == true) {
//            if(info.getLabel(user_id) == null) {
//                info.addInfo(user_id, interest + "," , 0);
//                System.out.println("1");  // 用户没有interest，并更新用户的interest
//            }
//            else if(info.getLabel(user_id).contains(interest)) {
//                System.out.println("0");  // label已存在
//            }
//            else if(info.interest_num(info.getLabel(user_id)) == 10) {
//                System.out.println("-1"); // label满了
//            }
//            else {
//                info.update(user_id, info.getLabel(user_id) + interest + ",");
//                System.out.println("2");  // 用户已有interest, 并更新用户的interest
//            }
//        }
//        else {
//            info.addLabel(interest);
//            label_id = info.search_id(interest) + ",";
//            if(info.getLabel(user_id) == null) {
//                info.addInfo(user_id, interest + "," , 0);
//                System.out.println("1");  // 用户没有interest，并更新用户的interest
//            }
//            if(info.interest_num(info.getLabel(user_id)) == 10) {
//                System.out.println("-1"); // label已满
//            }
//            else{
//                info.update(user_id, info.getLabel(user_id) + label_id);
//                System.out.println("2"); // 用户已有interest, 并更新用户的interest
//            }
//        }
//
//
//    }


}
