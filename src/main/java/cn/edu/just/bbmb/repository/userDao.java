package cn.edu.just.bbmb.repository;

import cn.edu.just.bbmb.bean.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userDao extends JpaRepository<user,String> {
    user findByUsername(String username);
    user findByPhone(String phone);
    user findByEmail(String email);
}
