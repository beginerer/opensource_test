package com.example.opensource_blog.controller;

import com.example.opensource_blog.domain.users.UserInfo;
import com.example.opensource_blog.dto.users.UserDto;
import com.example.opensource_blog.security.CustomUserDetails;
import com.example.opensource_blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Controller
public class UserAccountViewController {

    private final UserService userService;

    //회원가입 페이지
    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        var registerForm = UserDto.RegisterForm.builder().build();
        model.addAttribute("registerForm",registerForm);
        return "users/sign-up";
    }
    
    //회원가입 성공 페이지
    @GetMapping("/sign-up/success")
    public String signUpSuccessPage() {
        return "users/sign-up-success";
    }
    
    //로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "users/login";
    }

    // myAccount 페이지
    @GetMapping("/me")
    public String myAccountPage(@AuthenticationPrincipal CustomUserDetails userAccount,
                                Model model) {
        var userInfo = userService.findByUserId(userAccount.getUserId()).orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        UserInfo userInfoDto = new UserInfo(userInfo.getUserId(),userInfo.getUsername(),userInfo.getPassword());

        model.addAttribute("userInfo",userInfoDto);

        return "users/user-info";
    }
}
