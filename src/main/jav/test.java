import java.sql.SQLException;
import java.util.Random;

public class test {
    public static void main(String[] args) throws SQLException, Exception{
        User a = new User();
        app_im serv = new app_im();
        String key = serv.generateKey();
        String key2 = serv.generateKey();
        String en = serv.encrypt(key ,"s");
        System.out.println(key);
        String en2 = serv.encrypt(key2 ,"1232312313");
        System.out.println(key2);

        serv.deleteUser(4);
        System.out.println(serv.id_Count());
        System.out.println(en);
        System.out.println(en2);
        System.out.println(serv.decode(key,en));
        System.out.println(serv.decode(key2,en2));

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
}
