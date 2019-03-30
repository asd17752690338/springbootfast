package com.fast.start.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public  String  hello(){
        return  "hello22121223322";
    }
}
