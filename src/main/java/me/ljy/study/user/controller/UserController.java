package me.ljy.study.user.controller;

import me.ljy.study.user.service.UserService;
import me.ljy.study.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("test/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserVo>> getAllUsers() {
        return new ResponseEntity<List<UserVo>>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserVo> getUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<UserVo>(userService.findByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserVo> save(@RequestBody UserVo userVo) {
        return new ResponseEntity<UserVo>(userService.save(userVo), HttpStatus.OK);
    }
}
