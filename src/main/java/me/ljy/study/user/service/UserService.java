package me.ljy.study.user.service;

import me.ljy.study.user.repository.UserRepository;
import me.ljy.study.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserVo save(UserVo userVo) {
        return userRepository.save(userVo);
    }

    public List<UserVo> findAll() {
        return userRepository.findAll().stream().toList();
    }

    public UserVo findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
