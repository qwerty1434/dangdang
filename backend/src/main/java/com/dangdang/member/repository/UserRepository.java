package com.dangdang.member.repository;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value="select * from user where email=:email and is_delete=false", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    @Query(value="select * from user where nickname=:nickname and is_delete=false", nativeQuery = true)
    User findByNickname(@Param("nickname") String nickname);

}
