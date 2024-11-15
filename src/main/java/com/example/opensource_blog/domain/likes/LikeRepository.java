package com.example.opensource_blog.domain.likes;

import com.example.opensource_blog.domain.articles.Article;
import com.example.opensource_blog.domain.users.UserAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LikeRepository extends JpaRepository<Like,Long> {


}
