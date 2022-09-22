package com.dangdang.reward.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RewardRequest {

        private UUID rewardId;
        private int count;

}
