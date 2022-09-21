package com.dangdang.funding.dto;

import com.dangdang.image.domain.BodyImage;
import com.dangdang.image.domain.FundThumbnail;
import com.dangdang.reward.domain.Reward;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

public class FundingRequest {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    public static class Create{
        private String fundingId;
        private String category;
        private String title;
        private int targetPrice;
        private String projectIntroduction;
        private String company;
        private int businessLicenseNum;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Seoul" )
        private Timestamp endDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Seoul")
        private Timestamp startDate;
        private List<Reward> rewards;
        private List<FundThumbnail> thumbnails;
        private List<BodyImage> bodyImgs;
        private List<String> tags;
    }
}
