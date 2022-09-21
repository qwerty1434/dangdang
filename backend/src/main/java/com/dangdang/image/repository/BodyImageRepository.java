package com.dangdang.image.repository;

import com.dangdang.image.domain.BodyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BodyImageRepository extends JpaRepository<BodyImage, UUID> {



}
