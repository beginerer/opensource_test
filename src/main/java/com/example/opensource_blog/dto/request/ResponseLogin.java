package com.example.opensource_blog.dto.request;

import lombok.Builder;

@Builder
public record ResponseLogin(
        String accessToken
) {
}
