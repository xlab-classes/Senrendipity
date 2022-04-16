import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "create_rooms", urlPatterns =  "/create_rooms")
public class creat_room extends HttpServlet {
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
            getRoom(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRoom(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String user1 = request.getParameter("user1");
        String user2 = request.getParameter("user2");
        PrintWriter check = response.getWriter();

        chat_im serv = new chat_im();
        Connection conn = DButil.getConnection();

        String sql = "" + "select * from room_table where user1 =" + "'" + user1+"'"
                +" and user2 = " + "'" + user2+"'";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs1,rs2,rs3;
        rs1 = psmt.executeQuery();


        String sql2 = "" + "select * from room_table where user1 =" + "'" + user2+"'"
                +" and user2 = " + "'" + user1+"'" ;
        PreparedStatement psmt2 = conn.prepareStatement(sql2);
        rs2 = psmt2.executeQuery();
        String re ="0";
        while (rs1.next()){
            re =rs1.getString("room");
            check.write(rs1.getString("room"));
        }

        while (rs2.next()){
            re =rs2.getString("room");
            check.write(rs2.getString("room"));
        }

        if(re == "0"){
            check.write(Integer.toString(serv.createR(user1,user2)));
            serv.addfriend(user1,user2);
        }


        //System.out.println("re"+re);
        //check.write(re);
        psmt.close();
        psmt2.close();

        rs1.close();
        rs2.close();

        check.close();
    }
}