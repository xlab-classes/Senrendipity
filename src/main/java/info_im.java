import java.sql.*;

public class info_im extends app_im implements app_Design {

    @Override
    public int id_Count() throws SQLException {
        Connection conn = DButil.getConnection();
        String sql = ""+"SELECT * FROM label_table where id = (select max(id) from label_table)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery(sql);
        int id = 0;
        while (rs.next()){
            id = rs.getInt("id");
        }
        psmt.close();
        rs.close();
        return id+1;
    }

    public void addLabel(String name) throws Exception {
        Connection conn = DButil.getConnection();
        String sql = ""+
                "INSERT INTO label_table" +
                "(id,name)"+
                "values(?,?)";

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,id_Count());
        psmt.setString(2,name);
        psmt.execute();
        psmt.close();

    }

    public void addInfo(int user_id,String label, int creates) throws Exception {
        Connection conn = DButil.getConnection();
        String sql = ""+
                "INSERT INTO user_info" +
                "(id,label,creates)"+
                "values(?,?,?)";

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,user_id);
        psmt.setString(2,label);
        psmt.setInt(3,creates);
        psmt.execute();
        psmt.close();

    }

    @Override
    public void delete(int id) throws SQLException{
        Connection conn = DButil.getConnection();
        String sql = "" +
                "delete from label_table where id=?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, id);
        psmt.executeUpdate();
        psmt.close();
    }

    //更新info
    public void update(int id,String label) throws SQLException {
        Connection conn = DButil.getConnection();
        String sql = "" +
                "update user_info set label =" + "'"+label+"'"
                +" where id ="+id;
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.executeUpdate(sql);
        psmt.close();
    }

    public String getLabel(int id) throws SQLException{
        String label = null;
        Connection conn = DButil.getConnection();
        String sql = "select * from user_info where id =" + id;
        PreparedStatement psmt = conn.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()){
            label = rs.getString("label");
        }
        return label;
    }

    public int interest_num(String label) throws SQLException{
        int count = 1 ;
        if(label.trim().isEmpty()){
            return 0;
        }
        for(int i = 0; i<label.length(); i++ ){
            if(label.charAt(i) == ','){
                count++;
            }
        }

        return count;
    }

    public int search_id(String label) throws SQLException{
        int id = 0;
        Connection conn= DButil.getConnection();
        String sql = "" +
                "select * from label_table where label = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1,label);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()){
            id = rs.getInt("id");
        }
        return id;
    }


}