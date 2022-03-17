import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class googleMail {
    private static void gmailtls(Properties p){
        p.put("mail.smtp.auth","true");
        p.put("mail.smtp.starttls.enable","true");
        p.put("mail.smtp.host","smtp.gmail.com");
        p.put("mail.smtp.port","587");
    }

    public boolean getMimeMessage(String email, String v_code) throws Exception{

        Properties p = new Properties();
        gmailtls(p);
        final String username = "serendipity.cse442@gmail.com";
        final String password = "bebnyqmbkkxkzbex";
        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(email));
        msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(email));

        //主题
        msg.setSubject("Verification code", "UTF-8");
        msg.setContent(v_code,"text/html;charset=UTF-8");
        Transport.send(msg);
        return true;
    }

}
