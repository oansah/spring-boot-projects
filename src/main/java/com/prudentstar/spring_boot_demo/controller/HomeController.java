package com.prudentstar.spring_boot_demo.controller;


import com.prudentstar.spring_boot_demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @RequestMapping("/")
    public  String home(){
        return "Hello World!";
    }

   // @RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public User user(){
        User user = new User();
        user.setEmailid("clems@test.com");
        user.setName("Clement");
        user.setId("1");
        return  user;
    }

    @GetMapping("/{id}/{id2}")
    public String pathVariable(@PathVariable String id, @PathVariable("id2") String name){
        return  "The path variable id is "+ id + " and name is "+ name;
    }

    @GetMapping("/requestParam")
    public String requestParam(@RequestParam(name="name") String name,
                               @RequestParam(name="emailId",
                                       required = false, defaultValue = "") String emailId){
        return  "The name is "+ name + " and emailId is "+ emailId;
    }
}
