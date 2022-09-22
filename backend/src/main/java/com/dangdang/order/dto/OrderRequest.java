package com.dangdang.order.dto;

import com.dangdang.reward.domain.Reward;
import com.dangdang.reward.dto.RewardRequest;
import lombok.*;

import java.util.List;
import java.util.UUID;

public class OrderRequest {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    public static class Create{
        private UUID fundingId;
        private int totalPrice;
        private String address;
        private String phoneNumber;
        private List<RewardRequest> rewards;
        private boolean anonymous;
    }
}
