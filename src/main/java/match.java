import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class match {
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
            getMatch(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getMatch(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = request.getParameter("username");
        PrintWriter check = response.getWriter();

        info_im serv = new info_im();
        int id = serv.getId(username);
        String str1 = serv.getLabel(id);

        Connection conn = DButil.getConnection();
        String sql = "" + "select * from user_info";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();

        /// int count =0;
        //List<String> list = new ArrayList<String>();
        List<Integer> user_id = new ArrayList<Integer>();
        List<Double> res = new ArrayList<Double>();
        while (rs.next()){
            if (rs.getInt("id") == id){
                continue;
            }else {
                user_id.add(rs.getInt("id"));
                String str2 =  rs.getString("label");
                //list.add(str2);
                res.add(serv.getSimilar(str1,str2));
                //System.out.println(user_id.get(count)+"="+res.get(count++));
            }
        }
        int index = res.indexOf(Collections.max(res));
        check.write(user_id.get(index));
    }

}
