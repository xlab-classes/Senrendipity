import com.alibaba.fastjson.JSONObject;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class test {

    public static void delete(int room) throws SQLException {

        Connection conn = DButil.getConnection();
        String sql = "" +
                "delete from room_table where ROOM=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, room);
        psmt.executeUpdate();
        psmt.close();
    }

    public static void match(String username) throws Exception{
        info_im serv = new info_im();
        int id = serv.getId(username);
        String str1 = serv.getLabel(id);

        Connection conn = DButil.getConnection();
        String sql = "" + "select * from user_info";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        String sql2 ="" + "select * from user_info where id = "+ serv.getId(username);
        PreparedStatement psmt1 = conn.prepareStatement(sql2);
        ResultSet rs2 = psmt1.executeQuery();
        String friend = "";
        while(rs2.next()){
            friend = rs2.getString("friend_list");
        }
        /// int count =0;
        //List<String> list = new ArrayList<String>();
        List<Integer> user_id = new ArrayList<Integer>();
        List<Double> res = new ArrayList<Double>();
        List<Double> sec = new ArrayList<>();
        while (rs.next()){
            if (rs.getInt("id") == id || friend.contains(Integer.toString(rs.getInt("id")))){
                System.out.println(rs.getInt("id"));
                continue;
            }else {
                user_id.add(rs.getInt("id"));
                String str2 =  rs.getString("label");
                //list.add(str2);
                double temp = serv.getSimilar(str1,str2);
                res.add(temp);
                sec.add(temp);
                //System.out.println(user_id.get(count)+"="+res.get(count++));
            }
        }
        Collections.sort(sec);
        int top = res.indexOf(Collections.max(res));
        int second = res.indexOf(sec.get(sec.size()-2));
        int best1 = user_id.get(top); // best match
        int best2 = user_id.get(second);
        //System.out.println(best1);
        //System.out.println(best2);


        //System.out.print(top);
        //System.out.print(second);
        if (top!=-1 && second!=-1 && top!=second){
            User matchPerson1 = serv.getUser_id(best1);
            User matchPerson2 = serv.getUser_id(best2);

            JSONObject re = new JSONObject();
            re.put("user1", matchPerson1.getUsername());
            re.put("user2", matchPerson2.getUsername());

            //String info = matchPerson1.getUsername() + "###"+ matchPerson2.getUsername();
            //System.out.println(info);
            System.out.println(re.toJSONString());

        }
        else {
            JSONObject re = new JSONObject();
            re.put("fail", 0);

            System.out.println(re.toJSONString());
        }
    }

    public static void main(String[] args) throws Exception{
        //delete(1);

        chat_im serv = new chat_im();
        match("weng");
//        User b = new User();
//        app_Design serv = new app_im();
//        info_im a = new info_im();
//
//        String s1 = "SwiMMinG";
//        String s2 = "Video Games";
//        String s3 = "11,2,3,4,15";
//        System.out.println(a.getLabel(11));
//        String[] c = s3.split(",");
//
//        List<String> interests = new ArrayList();
//        String asd = "a";
//
//        interests = Arrays.asList(asd.split(","));
//        System.out.println(interests);

//        if(a.getLabel(1) != null) {
//            String[] buff = a.getLabel(1).split(",");
//            for(int i=0;i<buff.length;i++){
//                ret = ret + a.search_label(Integer.parseInt(buff[i])) + ", ";
//            }
//        }
//
//        ret = ret.substring(0, ret.length()-2);
//        System.out.println(ret);
//        for(int i = 0; i < c.length;i++) {
//            System.out.println(c[i]);
//        }
//        if(a.getLabel(5) != null) {
//            String[] buff = a.getLabel(5).split(",");
//            for(int i=0;i<buff.length;i++){
//                System.out.println(a.search_label(Integer.parseInt(buff[i])));
//            }
//        }
//        String ret = "";
//        for(int i = 0; i < a.all_labels().size(); i++) {
//            ret = ret + a.all_labels().get(i) + ",";
//        }
//        ret = ret.substring(0, ret.length()-1);
//        System.out.println(ret);



        /*int id = a.getId("1444");
        System.out.println(a.interest_num(a.getLabel(3)));
        System.out.println(a.interest_num(a.getLabel(id)));
        System.out.println(a.search_id("Piano"));
        */

        /*
        Connection conn = DButil.getConnection();
        String sql = "" + "select * from user_info";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        int i = 0;
        List<String> list = new ArrayList<String>();
        while (rs.next()){
            if (rs.getInt("id") == 5){
                continue;
            }else {
              String label =  rs.getString("label");
              list.add(label);
            }
            i++;
        }
        for (int j = 0; j < list.size(); j++){
            System.out.println(list.get(j));
        }
         */


        /*
        if(username.contains("@")){
            if(serv.UserExist_email(username)==true && (serv.getUser_email(username).getPassword() == password)){
                System.out.println("登入成功");
            }else{
                System.out.println("email错误");
            }
        }else{
            if(serv.UserExist_name(username)==true && (serv.getUser_name(username).getPassword().equals(password))){
                System.out.println("登入成功");
            }else{
                System.out.println(password);
                System.out.println("账号错误");
            }
        }
        Random random = new Random();
        String rs = "";
        for(int i = 0; i < 5; i++){
            rs += random.nextInt(10);
        }
        System.out.println(rs);
         */

    }
    /*
    public static int w(String username) throws Exception{

        info_im serv = new info_im();
        int id = serv.getId(username);
        String str1 = serv.getLabel(id);

        Connection conn = DButil.getConnection();
        String sql = "" + "select * from user_info";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();

        int count =0;
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
                System.out.println(user_id.get(count)+"="+res.get(count++));
            }
        }
        int index = res.indexOf(Collections.max(res));

        return user_id.get(index);
        //list.get(index);
        //return user_id[index];

    }

     */
}
