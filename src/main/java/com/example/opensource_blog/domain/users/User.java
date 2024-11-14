package com.example.opensource_blog.domain.users;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id@Column(name = "userid")
    private String userId;

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public static User of(String user_id,
                          String username,
                          String password){
        return new User(user_id,username,password);
    }
    public void updatePassword(String encodedPwd){this.password =encodedPwd;}
}
