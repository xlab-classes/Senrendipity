import java.util.Random;

public class test {
    public static void main(String[] args) throws Exception{
        User b = new User();
        app_Design serv = new app_im();
        info_im a = new info_im();
        String num = "1,2,3,4,5,6,7,8,9,10";
        int id = a.getId("1444");
        a.update(3,"1，3，3");
        System.out.println(a.interest_num(a.getLabel(3)));
        System.out.println(a.interest_num(a.getLabel(id)));

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
