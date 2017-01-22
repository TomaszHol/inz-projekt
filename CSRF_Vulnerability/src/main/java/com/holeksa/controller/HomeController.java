package com.holeksa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by bourbonkid on 11.01.17.
 */

@Controller
@RequestMapping("/")
public class HomeController {


    @GetMapping("/")
    public String myHome() {
        return "menu";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
