package com.example.opensource_blog.security;

import com.example.opensource_blog.domain.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String userId;
    private String username;
    private String password;

    public CustomUserDetails(User entity) {
        userId = entity.getUserId();
        username = entity.getUsername();
        password = entity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    public String getUserId(){
        return userId;
    }
}
