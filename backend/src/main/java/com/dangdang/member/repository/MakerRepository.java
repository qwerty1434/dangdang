package com.dangdang.member.repository;

import com.dangdang.member.domain.Maker;
import com.dangdang.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MakerRepository extends JpaRepository<Maker, User> {

    @Query(value="select * from maker where id=:userId", nativeQuery = true)
    Maker findByUserId(@Param("userId") String userId);

}
