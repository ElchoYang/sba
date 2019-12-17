package com.elcho.provideuserapi.controller;

import com.elcho.provideuserapi.model.User;
import com.elcho.provideuserapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {
    private static final Logger log = Logger.getLogger(String.valueOf(UserController.class));
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String getUser(){
        return userService.getUser();
    }

    //---------- find All user -------------
    @RequestMapping(value = "/users",method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userService.findAll();
        //判断
        if(userList == null || userList.isEmpty()) {
            //告诉客户端，没有相关的数据
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    //---------- find user -------------
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable("id") Long Id) {
        log.info(String.valueOf(Id));
        User user = userService.findById(Id);
        //判断
        if(user == null) {
            //告诉客户端，没有相关的数据
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //---------- Find login user -------------
    @RequestMapping(value = "/users/{username}",method = RequestMethod.POST)
    public ResponseEntity<User> getUser(@PathVariable("username") String username, @RequestBody User user, UriComponentsBuilder ucBuilder) {
        log.info("user: " + user);
        User _user = new User();
        //判断
        if(!userService.isUserExists(username)) {
            //告诉客户端，没有相关的数据
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        _user = userService.queryByUserName(username);
        log.info("_user: " + _user.getPassword());

        if(!_user.getPassword().equals(user.getPassword())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(_user, HttpStatus.OK);
    }


    // Login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        User _user = new User();

        log.info("consumer端传输过来的用户信息是："+user);
        //判断用户名是否已经存在
        if(!userService.isLoginUserExists(user.getUserName(), user.getPassword())) {
            log.info("用户名：%s Do not Exist\n" + user.getUserName());
            return new ResponseEntity<>(_user, HttpStatus.CONFLICT);
        }
        //
        _user = userService.queryByUserName(user.getUserName());
        log.info("Login successful!");
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    // sign up
    @RequestMapping(value = "/singup", method = RequestMethod.POST)
    public ResponseEntity<Integer> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        Integer status = -1;

        log.info("consumer端传输过来的用户信息是："+user);
        //判断用户名是否已经存在
        if(userService.isUserExists(user.getUserName())) {
            //
            log.info("用户名：%s已经存在了\n" + user.getUserName());
            return new ResponseEntity<>(status, HttpStatus.CONFLICT);
        }
        //调用业务方法
        userService.save(user);
        status = 0;

        log.info("Sign up successful!");
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

}
