package com.springboot.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/th")
    public  String thymeStart() {
        return "thymeStart";
    }
}
