package com.example.opensource_blog.controller;

import com.example.opensource_blog.dto.users.UserDto;
import com.example.opensource_blog.security.CustomUserDetails;
import com.example.opensource_blog.security.CustomUserDetailsService;
import com.example.opensource_blog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Controller
public class UserCommandController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/sign-up")
    public String registerUser(@Valid @ModelAttribute("registerForm")UserDto.RegisterForm registerForm,
                               BindingResult bindingResult) {

        //중복 아이디 검증
        if(userService.isAlreadySameId(registerForm.getUserId())) {
            log.debug("중복된 아이디입니다! userId={}",registerForm.getUserId());
            bindingResult.reject("777");
        }

        if(bindingResult.hasErrors()) {
            return "users/sign-up";
        }
        userService.registerUser(registerForm.getUserId(),registerForm.getUsername(),registerForm.getPassword());
        return "redirect:/accounts/login";
    }


}
