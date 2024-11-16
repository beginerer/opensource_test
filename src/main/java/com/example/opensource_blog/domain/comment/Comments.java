package com.example.opensource_blog.domain.comment;

import com.example.opensource_blog.domain.post.Post;
import com.example.opensource_blog.domain.users.UserAccount;
import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "postId", referencedColumnName = "postId", nullable = false)
    private Post post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserAccount user;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    public Comments(Post post, UserAccount user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
    }

    public static Comments of(Post post, UserAccount user, String content) {
        return new Comments(post, user, content);
    }
}
