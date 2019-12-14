package com.elcho.stockexchange.controller;

import com.elcho.stockexchange.model.User;
import com.elcho.stockexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger log = Logger.getLogger(String.valueOf(UserController.class));

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

    //---------- Find login user -------------
    @RequestMapping(value = "/users/{username}",method = RequestMethod.POST)
    public ResponseEntity<User> getUser(@PathVariable("username") String username,@RequestBody User user, UriComponentsBuilder ucBuilder) {
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

    // sign up
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("客户端传输过来的用户信息是："+user);
        //判断用户名是否已经存在
        if(userService.isUserExists(user.getUserName())) {
            //
            System.out.printf("用户名：%s已经存在了\n", user.getUserName());
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        //调用业务方法
        userService.save(user);

        //构建一个响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/api/users/{id}").buildAndExpand(user.getId()).toUri());
        //
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
