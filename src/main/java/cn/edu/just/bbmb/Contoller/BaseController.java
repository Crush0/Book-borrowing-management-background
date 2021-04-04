package cn.edu.just.bbmb.Contoller;

import cn.edu.just.bbmb.Exception.*;
import cn.edu.just.bbmb.util.JsonResult;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {
    public static final int SUCCESS=1000;
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable ex) {

        JsonResult<Void> jsonResult=new JsonResult<>(ex);

        if(ex instanceof UsernameDuplicateException){
            //用户名被占用  -  1998
            if(ex.getMessage()=="用户名已被占用")
                jsonResult.setState(1998);
            //手机号存在 - 1999
            else if(ex.getMessage()=="手机号已经存在")
                jsonResult.setState(1999);
            //邮箱存在 - 2000
            else if(ex.getMessage()=="邮箱已经存在")
                jsonResult.setState(2000);
        }else if(ex instanceof UsernameNotFoundException){
            //用户名找不到  -  2003
            jsonResult.setState(2003);
        }else if(ex instanceof PasswordNotException){
            //密码错误 - 2004
            jsonResult.setState(2004);
        }else if(ex instanceof InfoNotEmpty){
            //空用户名 - 2005
            if(ex.getMessage()=="用户名不得为空")
                jsonResult.setState(2005);
            //空密码 - 2006
            else if(ex.getMessage()=="密码不得为空")
                jsonResult.setState(2006);
            //空手机号 - 2007
            else if(ex.getMessage()=="手机号不得为空")
                jsonResult.setState(2007);
            //空邮箱 - 2008
            else if(ex.getMessage()=="邮箱不得为空")
                jsonResult.setState(2008);
        }
        if(ex instanceof MailException){
            if(ex.getMessage()=="邮箱验证码错误")
                jsonResult.setState(2009);
            else if(ex.getMessage()=="验证码过期")
                jsonResult.setState(2010);
            else if(ex.getMessage()=="邮箱不匹配")
                jsonResult.setState(2011);
            else if(ex.getMessage()=="验证码错误")
                jsonResult.setState(2012);
            //邮件发送失败
            else if(ex.getMessage()=="邮件发送失败")
                jsonResult.setState(2013);
        }
        if(ex instanceof ValidnameException){
            if(ex.getMessage()=="邮箱不合法")
                jsonResult.setState(4000);
            else if(ex.getMessage()=="手机号不合法")
                jsonResult.setState(4001);
            else if(ex.getMessage()=="密码不合法")
                jsonResult.setState(4002);
            else if(ex.getMessage()=="用户名不合法")
                jsonResult.setState(4003);
        }
        //返回响应值
        return jsonResult;
    }
}
