package me.ljy.study.user.repository;

import me.ljy.study.user.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserVo, Integer> {
    public UserVo findByUserId(String userId);
}
