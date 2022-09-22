package com.dangdang.image.repository;

import com.dangdang.image.domain.BodyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BodyImageRepository extends JpaRepository<BodyImage, UUID> {

    @Query(value = "SELECT * FROM body_image WHERE `funding_id`=:fundingId  order by sequence ", nativeQuery = true)
    List<BodyImage> findByFundingId(@Param("fundingId") String fundingId);
}
