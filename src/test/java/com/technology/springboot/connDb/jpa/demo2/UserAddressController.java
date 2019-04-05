package com.technology.springboot.connDb.jpa.demo2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("demo1")
public class UserAddressController {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @GetMapping("addAddress")
    @ResponseBody
    private String addAddress(@RequestParam String city, @RequestParam String town, @RequestParam String rural) {
        UserAddress userAddress = new UserAddress() ;  //匿名内部类 是一个新类 而不是原来的类,直接创建对象的语法,比用匿名内部类更好用
        userAddress.setCity(city);
        userAddress.setTown(town);
        userAddress.setRural(rural);
        userAddressRepository.save(userAddress);
        return "add success";
    }

}
