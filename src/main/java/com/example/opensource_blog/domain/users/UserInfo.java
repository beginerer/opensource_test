package com.example.opensource_blog.domain.users;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class UserInfo {
    private String user_id;
    private String username;
    private String password;

    public UserInfo(String user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }
}