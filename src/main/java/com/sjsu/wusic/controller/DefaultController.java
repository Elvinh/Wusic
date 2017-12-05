package com.sjsu.wusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
	@GetMapping("/")
    public String home1() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String admin() {
        return "login";
    }

    @GetMapping("/hello")
    public String user() {
        return "hello";
    }
    
    @GetMapping("/advSearch")
    public String advSearch() {
        return "advancedSearch";
    }
}
