package cn.edu.just.bbmb.impl;

import cn.edu.just.bbmb.Exception.InfoNotEmpty;
import cn.edu.just.bbmb.Exception.PasswordNotException;
import cn.edu.just.bbmb.Exception.UsernameDuplicateException;
import cn.edu.just.bbmb.Exception.UsernameNotFoundException;
import cn.edu.just.bbmb.repository.IUserService;
import cn.edu.just.bbmb.bean.user;
import cn.edu.just.bbmb.repository.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServieImpl implements IUserService {
    @Autowired
    private userDao userdao;
    @Override
    public void Reg(user user) {
        String username = user.getUsername();
        String phone = user.getPhone();
        String email = user.getEmail();
        user.setIdentify("0");
        if(username==""){
            throw new InfoNotEmpty("用户名不得为空");
        }
        if(user.getPassword()==""){
            throw new InfoNotEmpty("密码不得为空");
        }
        if(phone==""){
            throw new InfoNotEmpty("手机号不得为空");
        }
        if(email==""){
            throw new InfoNotEmpty("邮箱不得为空");
        }
        if(userdao.findByUsername(username)!=null){
            throw new UsernameDuplicateException("用户名已被占用");
        }
        if(userdao.findByPhone(phone)!=null){
            throw new UsernameDuplicateException("手机号已经存在");
        }
        if(userdao.findByEmail(email)!=null){
            throw new UsernameDuplicateException("邮箱已经存在");
        }
        userdao.saveAndFlush(user);
    }
    @Override
    public void Check(user user){
        if(userdao.findByUsername(user.getUsername())!=null){
            throw new UsernameDuplicateException("用户名已被占用");
        }
        if(userdao.findByPhone(user.getPhone())!=null){
            throw new UsernameDuplicateException("手机号已经存在");
        }
        if(userdao.findByEmail(user.getEmail())!=null){
            throw new UsernameDuplicateException("邮箱已经存在");
        }
    }
    @Override
    public user login(String username, String password) {
        user User = userdao.findByUsername(username);
        if(User==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        if(!User.getPassword().equals(password)){
            throw new PasswordNotException("密码错误");
        }
        return User;
    }
}
