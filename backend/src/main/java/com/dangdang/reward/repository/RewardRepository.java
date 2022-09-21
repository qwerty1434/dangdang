package com.dangdang.reward.repository;

import com.dangdang.funding.domain.Funding;
import com.dangdang.reward.domain.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RewardRepository extends JpaRepository<Reward, UUID> {



}
