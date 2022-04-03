import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    public void getInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

    }
}
