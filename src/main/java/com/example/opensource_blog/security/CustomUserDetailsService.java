package com.example.opensource_blog.security;

import com.example.opensource_blog.domain.users.User;
import com.example.opensource_blog.domain.users.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("{}: {}", getClass().getSimpleName(), "loadUserByUserId(String)");

        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
        return new CustomUserDetails(user);
    }
}
