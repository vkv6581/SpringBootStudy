package me.ljy.study.user.repository;

import me.ljy.study.user.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserVo, Integer> {
    public UserVo findByUserId(String userId);
}
