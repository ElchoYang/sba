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

    @GetMapping("/login")
    public String login(String name) throws ParserConfigurationException, IOException, SAXException {
        String s = restTemplate.getForObject("http://provider-user-api/user", String.class);


        String xml = StringEscapeUtils.escapeXml("<i>ABC'&</i>");
        log.info("---------------");
        log.info(StringEscapeUtils.escapeXml(xml));


     /*   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource((new StringReader(xml))));
*/


        //System.out.println(StringEscapeUtils.escapeXml("<i><pay>ABC&</pay><go><ddd' cc & ABC</go></i>"));
         return "login User: " +s;
    }

    @CrossOrigin
    @GetMapping("/all")
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
