package com.dangdang.member.repository;

import com.dangdang.member.domain.Maker;
import com.dangdang.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MakerRepository extends JpaRepository<Maker, User> {

}
