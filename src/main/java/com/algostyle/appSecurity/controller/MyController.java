package com.algostyle.appSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MyController {

    @GetMapping("welcome")
    public String welcome(){
        return "welcome to algostyle channel";
    }

    @GetMapping("creator")
    public String nameCreator(){
        return "Asmae Aouassar is the creator";
    }
}
