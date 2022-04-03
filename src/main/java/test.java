import java.util.Random;

public class test {
    public static void main(String[] args) throws Exception{
        User b = new User();
        app_Design serv = new app_im();
        info_im a = new info_im();
        String num = "1,2,3,4,5,6,7,8,9,10";

        a.addInfo(2,num,1);

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
