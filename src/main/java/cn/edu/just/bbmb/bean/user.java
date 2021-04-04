package cn.edu.just.bbmb.bean;

import javax.persistence.*;

@Entity
public class user {
    @Id
    @GeneratedValue
    private Integer userid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String identify;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }
    @Override
    public String toString(){
        return username;
    }
}
