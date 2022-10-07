package com.dangdang.reward.repository;

import com.dangdang.funding.domain.Funding;
import com.dangdang.image.domain.BodyImage;
import com.dangdang.reward.domain.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RewardRepository extends JpaRepository<Reward, UUID> {

    @Query(value = "SELECT * FROM reward WHERE `funding_id`=:fundingId  order by sequence ", nativeQuery = true)
    List<Reward> findByFundingId(@Param("fundingId") String fundingId);

}
