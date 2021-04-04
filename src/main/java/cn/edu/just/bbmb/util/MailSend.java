package cn.edu.just.bbmb.util;

import cn.edu.just.bbmb.Exception.MailException;
import cn.edu.just.bbmb.Exception.UsernameDuplicateException;
import cn.edu.just.bbmb.repository.userDao;
import cn.edu.just.bbmb.struct.Vcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;
@Component
public class MailSend {
    @Autowired
    public userDao user;
    @Autowired
    public JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    public String from;
    public void sendSimpleMail(String mailTo, String mailHead, String mailContent) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mailTo);
        message.setSubject(mailHead);
        message.setText(mailContent);
        mailSender.send(message);
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
            sendSimpleMail(email,"注册验证码","您的验证码是:"+code+",两分钟内有效,如果非本本人操作，请忽视。");
        }
        catch (MailException ex){
            throw new MailException("邮件发送失败");
        }
    }
}
