package cn.edu.just.bbmb.util;

import cn.edu.just.bbmb.Exception.MailException;
import cn.edu.just.bbmb.Exception.UsernameDuplicateException;
import cn.edu.just.bbmb.repository.userDao;
import cn.edu.just.bbmb.struct.Vcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.rmi.server.ServerCloneException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Component
public class SllMainSend {
    private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    @Value("${spring.mail.host}")
    private String smtpServer; // SMTP服务器地址
    private String port = "465"; // 端口
    @Value("${spring.mail.username}")
    private String username; // 登录SMTP服务器的用户名
    @Value("${spring.mail.password}")
    private String password; // 登录SMTP服务器的密码
    @Autowired
    public userDao user;
    public String changeEncode(String str) {
        try {
            str = MimeUtility.encodeText(new String(str.getBytes(), "UTF-8"),
                    "UTF-8", "B"); // "B"代表Base64
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
    public void sendMain(String email,String subject,String content) throws ServerCloneException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpServer);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", SSL_FACTORY);  //使用JSSE的SSL socketfactory来取代默认的socketfactory
        properties.put("mail.smtp.socketFactory.fallback", "false");  // 只处理SSL的连接,对于非SSL的连接不做处理

        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.socketFactory.port", port);

        Session se = Session.getInstance(properties);
        se.setDebug(true);
        MimeMessage message = new MimeMessage(se);
        try{
            Address address = new InternetAddress(username);
            message.setFrom(address);
            Address toAddress = new InternetAddress(email);
            message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
            message.setSubject(changeEncode(subject));
            message.setSentDate(new Date());
            Multipart multipart = new MimeMultipart();
            BodyPart text = new MimeBodyPart();
            text.setText(content);
            multipart.addBodyPart(text);
            message.setContent(multipart);
            message.saveChanges();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            Transport transport = se.getTransport("smtp");
            transport.connect(smtpServer, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void SendRegCode(String email, HttpSession session){
        Integer code = new Random().nextInt(900000) + 100000;
        if(user.findByEmail(email)!=null){
            throw new UsernameDuplicateException("邮箱已经存在");
        }
        else{
            Vcode vcCode = new Vcode(email,new Date().getTime(),code);
            session.setAttribute("registerVCode",vcCode);
        }
        try{
            sendMain(email,"注册验证码","您的验证码是:"+code+",两分钟内有效,如果非本本人操作，请忽视。");
        }
        catch (MailException | ServerCloneException ex){
            throw new MailException("邮件发送失败");
        }
    }
}
