package com.jwt.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping("/welcome")
    public String welcome() {
        String text = "This is private page You are Accessing!. ";
        text += "This page is not allow for unauthorized users";
        return text;
    }
}
