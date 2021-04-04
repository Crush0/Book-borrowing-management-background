package cn.edu.just.bbmb.repository;

import cn.edu.just.bbmb.bean.user;

public interface IUserService {
    void Reg(user user);
    user login(String username,String password);
    void Check(user user);
}
