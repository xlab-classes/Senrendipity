import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class get_check {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            get_show(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get_show(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int room = Integer.parseInt(request.getParameter("room"));
        chat_im serv = new chat_im();
        PrintWriter check = response.getWriter();
        Connection conn = DButil.getConnection();
        String sql = "" + "select * from chat_table where ROOM = " + room + " and show_message = 0";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        if(rs.next()){
            String re = rs.getString("message");
            serv.updateShow(room,);
            check.write(re);
        }else check.write("0");

    }

}
