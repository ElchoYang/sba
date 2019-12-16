package com.elcho.stockexchange.controller;

import com.elcho.stockexchange.model.User;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.io.StringReader;


@RestController
@RequestMapping( value = "/stockexchange/user")
public class UserController {
    private static final Logger log = Logger.getLogger(String.valueOf(UserController.class));

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ResponseEntity<User> login(@RequestBody User user, UriComponentsBuilder ucBuilder) throws ParserConfigurationException, IOException, SAXException {
        log.info("客户端传输过来的用户信息是："+user);
        ResponseEntity<User> entity =  restTemplate.postForEntity("http://provider-user-api/login",user, User.class);
        HttpStatus statusCode  = entity.getStatusCode();
        log.info("Status Code: " + statusCode);

        User _user = entity.getBody();
        log.info("_user: " + _user);

        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = {RequestMethod.POST})
    public ResponseEntity<Integer> signup(@RequestBody User user, UriComponentsBuilder ucBuilder){
        log.info("客户端传输过来的用户信息是："+user);
        ResponseEntity<Integer> entity =  restTemplate.postForEntity("http://provider-user-api/singup",user, Integer.class);
        HttpStatus statusCode  = entity.getStatusCode();
        log.info("Status Code: " + statusCode);

        Integer status = entity.getBody();

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public ResponseEntity<Integer> logout(){
        log.info("logout Successful");
        return new ResponseEntity<>(0, HttpStatus.OK);
    }

   // @GetMapping("/all")
    @RequestMapping(value = "/all", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<User>> checkAllUser(String name, UriComponentsBuilder ucBuilder){
        ResponseEntity<List> entity =  restTemplate.getForEntity("http://provider-user-api/users", List.class);
        HttpStatus statusCode  = entity.getStatusCode();
        log.info("Status Code: " + statusCode);
        System.out.println(entity.getBody());
        List<User> userList = entity.getBody();

        System.out.println(userList);


        //构建一个响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setConnection("keep-alive");
        //

        return new ResponseEntity<>(userList,headers, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long Id){
        ResponseEntity<User> entity = restTemplate.getForEntity("http://provider-user-api/user/{id}", User.class, Id);
        HttpStatus statusCode  = entity.getStatusCode();
        log.info("Status Code: " + statusCode);
        System.out.println(entity.getBody());

        User user =  entity.getBody();
        System.out.print("UserName: " +user.getUserName());

        return user;

    }

}
