package com.dangdang.member.dto;

import com.dangdang.reward.dto.RewardResponse;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.UUID;

public class MakerResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Res{

        private UUID id;
        private String companyNumber;
        private String companyName;
        private String img;
        private Long fundingSum;
        private int supporter;

        public static MakerResponse.Res build(UUID id, String companyNumber, String companyName, String img, Long fundingSum , int supporter){
            return MakerResponse.Res.builder()
                    .id(id)
                    .companyNumber(companyNumber)
                    .companyName(companyName)
                    .img(img)
                    .fundingSum(fundingSum)
                    .supporter(supporter)
                    .build();
        }
    }

}
