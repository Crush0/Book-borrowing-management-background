package cn.edu.just.bbmb.struct;

import java.util.Date;

public class Vcode {
    long ETime;
    Integer Code;
    String email;

    public Vcode(String setEmail, long setTime, Integer setCode){
        this.Code = setCode;
        this.ETime = setTime;
        this.email=setEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getETime() {
        return ETime;
    }

    public void setETime(long ETime) {
        this.ETime = ETime;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }
}
