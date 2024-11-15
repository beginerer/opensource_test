package com.example.opensource_blog.jwt;

import com.example.opensource_blog.service.user.UserInfo;
import com.example.opensource_blog.service.user.UserInfoService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long tokenValidityInSeconds;

    private SecretKey key;

    @Autowired
    private UserInfoService userInfoService;

    public TokenProvider(@Value("${jwt.secret}")String secret,
                         @Value("${jwt.accessTokenValidityInSeconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInSeconds = tokenValidityInSeconds;

        // 시크릿 값을 복호화(decode) 하여 키 변수에 할당
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInSeconds * 1000);
        return Jwts.builder()
                .subject(authentication.getName())
                .claim(AUTHORITIES_KEY,authorities)
                .signWith(key)
                .expiration(validity)
                .compact();
    }
    /**
     * 토큰을 받아 클레임을 생성
     * 클레임에서 권한 정보를 가져와서 시큐리티 UserDetails 객체를 만들고
     * Authentication 객체 반환
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        UserInfo userInfo = (UserInfo) userInfoService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userInfo,token,authorities);
    }

    /**
     * 토큰 유효성 체크
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            e.printStackTrace();
        }
        return false;
    }



}