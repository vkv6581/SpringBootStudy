package me.ljy.study.user.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="user")
public class UserVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    private String userId;

    private String password;

    public UserVo(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UserVo() {

    }
}
