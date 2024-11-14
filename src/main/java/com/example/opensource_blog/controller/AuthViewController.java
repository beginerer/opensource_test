package com.example.opensource_blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AuthViewController {

    @GetMapping("/error/denied")
    public String accessDeniedPage() {
        return "error/denied-page";
    }
}
