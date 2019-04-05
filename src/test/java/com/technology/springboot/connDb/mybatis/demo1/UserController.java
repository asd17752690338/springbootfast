package com.technology.springboot.connDb.mybatis.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private  UserMapper userMapper;

    @RequestMapping("getUserById")
    public  String getUserById(@RequestParam Integer id){
          return userMapper.getById(id).toString();
    }

    @RequestMapping("getUserById2")
    public  String getUserById2(@RequestParam Integer id){
        return userMapper.getById2(id).toString();
    }

    @RequestMapping("insertUser")
    public  String insertUser(){
        User user = new User();
        user.setName("aaa");
        user.setEmail("sadasd");
        user.setUserAccount("qwerwq");
        userMapper.insert(user);
        return "success";
    }


    @RequestMapping("batchInsert")
    public  String batchInsert(){
        List<User> users = new ArrayList<>();
        for(int i = 0;i<10;i++){
            User user = new User();
            user.setName("aaa"+i);
            user.setEmail("sadasd"+i);
            user.setUserAccount("qwerwq"+i);
            users.add(user);
        }
        userMapper.batchInsert(users);
        return "batchInsert success";
    }



}
