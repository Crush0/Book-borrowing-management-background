package cn.edu.just.bbmb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class nameValid {

    public static boolean isEmail(String email) {
        if (email == null)
            return false;
        Pattern p = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isMobileNum(String telNum){
        if (telNum == null)
            return false;
        Pattern p = Pattern.compile("^[1]\\d{10}$");
        Matcher m = p.matcher(telNum);
        return m.matches();
    }
    //六位-三十位密码，不能包含汉字，空格
    public static boolean isValidPwd(String password) {
        if(password.length()<6)
            return false;
        if(password.length()>=30)
            return false;
        if (password.length() > 0) {
            //判断是否有空格字符串
            for (int t = 0; t < password.length(); t++) {
                String b = password.substring(t, t + 1);
                if (b.equals(" ")) {
                    return false;
                }
            }
            //判断是否有汉字
            int count = 0;
            String regEx = "[\\u4e00-\\u9fa5]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(password);
            while (m.find()) {
                for (int i = 0; i <= m.groupCount(); i++) {
                    count = count + 1;
                }
            }
            if(count>0){
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
