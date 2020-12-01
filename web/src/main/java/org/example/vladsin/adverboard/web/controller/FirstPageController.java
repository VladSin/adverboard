package org.example.vladsin.adverboard.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FirstPageController {

    @GetMapping("/")
    public  String doGet(){
        return "index";
    }
}
