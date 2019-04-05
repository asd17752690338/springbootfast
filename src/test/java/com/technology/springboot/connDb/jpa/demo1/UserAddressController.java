package com.technology.springboot.connDb.jpa.demo1;

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
        UserAddress userAddress = new UserAddress() {{
            setCity(city);
            setTown(town);
            setRural(rural);
        }};
        userAddressRepository.save(userAddress);
        return "add success";
    }

}
