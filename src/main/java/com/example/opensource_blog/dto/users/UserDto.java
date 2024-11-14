package com.example.opensource_blog.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class UserDto {

    @ToString
    @Getter
    @Builder
    public static class RegisterReq {

        @NotBlank @Pattern(regexp = "^[a-zA-Z0-9]{4,8}$")
        private String userId;

        @NotBlank @Pattern(regexp = "^[a-zA-Z0-9]{4,8}$")
        private String password;

        @NotBlank
        private String username;
    }

    @ToString
    @Getter
    @Builder
    public static class RegisterForm {

        @NotBlank @Pattern(regexp = "^[a-zA-Z0-9]{4,8}$")
        private String userId;

        @NotBlank @Pattern(regexp = "^[a-zA-Z0-9]{4,8}$")
        private String password;

        @NotBlank
        private String username;
    }

    @ToString
    @Getter
    @Builder
    public static class MainInfoResponse {
        private String userId;
        private String username;
        private String name;
    }

}
