package l17_atm;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Outlook {
String email;
int otp;
Outlook(String mail,int pin){
    email=mail;otp=pin;
    final String username = "dclatmsystem@gmail.com";  // like yourname@outlook.com
    final String password = "DCLSYSTEM";   // password here

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
      });
    session.setDebug(true);

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(email));  
        message.setSubject("OTP FOR PASSWORD RESET");
        message.setText("AN ATTEMPT TO RESET PASSWORD HAS DONE \n\nPlease Enter the below otp to reset password \n\n\n"+otp);

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
    public static void main(String[] args) {
        String mail="";
        int pin=0;
        new Outlook(mail,pin);
    }
}
