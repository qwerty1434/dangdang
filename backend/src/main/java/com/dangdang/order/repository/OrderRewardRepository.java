package com.dangdang.order.repository;

import com.dangdang.order.domain.OrderReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRewardRepository extends JpaRepository<OrderReward, UUID> {
    List<OrderReward> findByOrderHistoryId(UUID id);
}
