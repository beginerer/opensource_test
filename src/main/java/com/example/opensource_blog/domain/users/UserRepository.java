package com.example.opensource_blog.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {


    Optional<User> findByUserId(String user_id);

    boolean existsByUserId(String user_id);
}
