package com.dangdang.reward.dto;

import com.dangdang.image.dto.FundThumbnailResponse;
import lombok.*;

import javax.persistence.Column;
import java.util.UUID;

public class RewardResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Res{

        private UUID id;
        private int price;
        private String title;
        private String content;
        private int sequence;

        public static RewardResponse.Res build(UUID id, int price, String title, String content, int sequence){
            return Res.builder()
                    .id(id)
                    .price(price)
                    .title(title)
                    .content(content)
                    .sequence(sequence)
                    .build();
        }
    }
}
