package com.dangdang.order.repository;

import com.dangdang.order.domain.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, UUID> {
    List<OrderHistory> findByUserIdAndFundingIdOrderByOrderDate(UUID userId, UUID fundingId);

    List<OrderHistory> findByFundingId(UUID fundingId);
}
