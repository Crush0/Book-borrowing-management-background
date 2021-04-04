package cn.edu.just.bbmb.Contoller;

import cn.edu.just.bbmb.Exception.MailException;
import cn.edu.just.bbmb.Exception.ValidnameException;
import cn.edu.just.bbmb.repository.IUserService;
import cn.edu.just.bbmb.bean.user;
import cn.edu.just.bbmb.struct.Vcode;
import cn.edu.just.bbmb.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class UserController extends BaseController {
    @Autowired
    public IUserService UserService;
    @Autowired
    public SllMainSend sender;
    //密码解密解密秘钥
    private char pwdSec = 'T';
    //注册函数，校验验证码
    @PostMapping("user/register")
    private JsonResult<Void> Register(
            @RequestParam("inputCode")Integer inputCode,
            HttpSession session
    ){
        user User = (user)session.getAttribute("RegUser");
        Vcode code = (Vcode) session.getAttribute("registerVCode");
        if(code.getCode().equals(inputCode)){
            if(code.getEmail().equals(User.getEmail())) {
                if ((code.getETime() - new Date().getTime()) <= 12000) {
                    session.removeAttribute("registerVCode");
                    UserService.Reg(User);
                }
                else
                    throw new MailException("验证码过期");
            }
            else{
                throw new MailException("邮箱不匹配");
            }
        }
        else{
            throw new MailException("邮箱验证码错误");
        }
        return new JsonResult<>(SUCCESS,User.toString());
    }
    //发送验证码，验证账号手机号邮箱合法性
    @PostMapping("/user/Reg/SendVCode")
    private JsonResult<Void> SendVCode(
            @RequestParam("username")String username,
            @RequestParam("password")String password,
            @RequestParam("phone")String phone,
            @RequestParam("email")String email,
            HttpSession session
    ){
        if(!nameValid.isEmail(email)){
            throw new ValidnameException("邮箱不合法");
        }
        else if(!nameValid.isValidPwd(password)){
            throw new ValidnameException("密码不合法");
        }
        else if(!nameValid.isMobileNum(phone)){
            throw new ValidnameException("手机号不合法");
        }
        user User = new user();
        User.setUsername(username);
        User.setPhone(phone);
        User.setEmail(email);
        UserService.Check(User);
        User.setPassword(Encryption.encryptAndDencrypt(password,pwdSec));
        sender.SendRegCode(email,session);
        session.setAttribute("RegUser",User);
        return new JsonResult<>(SUCCESS,User.toString());
    }
    @PostMapping("/user/login")
    private JsonResult<Void> login(@RequestParam("username")String username,@RequestParam("password")String password, HttpSession session){
        user User = UserService.login(username, Encryption.encryptAndDencrypt(password,pwdSec));
        session.setAttribute("username",User.getUsername());
        session.setAttribute("uid",User.getUserid());
        return new JsonResult<>(SUCCESS,User.toString());
    }
}
