package com.technology.springboot.connDb.mybatis.demo1;

import com.technology.util.print.Prints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("getUserById")
    public String getUserById(@RequestParam Integer id) {
        return userMapper.getById(id).toString();
    }

    @RequestMapping("getUserById2")
    public String getUserById2(@RequestParam Integer id) {
        return userMapper.getById2(id).toString();
    }

    @RequestMapping("insertUser")
    public String insertUser() {
        User user = new User();
        user.setName("aaa");
        user.setEmail("sadasd");
        user.setUserAccount("qwerwq");
        userMapper.insert(user);
        return "success";
    }


    @RequestMapping("batchInsert")
    public String batchInsert() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("aaa" + i);
            user.setEmail("sadasd" + i);
            user.setUserAccount("qwerwq" + i);
            users.add(user);
        }
        userMapper.batchInsert(users);
        return "batchInsert success";
    }

    @RequestMapping("callProducer")
    public String callProducer() {
        List<User> users = userMapper.callProducer();
        users.forEach(item -> {
            Prints.printObj(item);
        });
        return "callProducer success";
    }

    @RequestMapping("callCount")
    public String callCount() {  //这个是失败的,证明输出值 不能通过java自带的包装类型返回,可能是没有get set方法
        Long count = 0L;
        Long ret = userMapper.callCount(count);
        System.out.println(count);
        System.out.println(ret);
        return "callCount success";
    }

    @RequestMapping("callCount2")
    public String callCount2() {  //此时返回成功,说明mybatis支持的是对象的返回设置
        MyData myData = new MyData();
        Long ret = userMapper.callCount2(myData);
        System.out.println(ret);
        return Prints.getObj(myData);
    }



    @RequestMapping("callUserById")
    public String callUserById(@RequestParam Long id) {
        User user = userMapper.callUserById(id);
        return  Prints.getObj(user);
    }


}
