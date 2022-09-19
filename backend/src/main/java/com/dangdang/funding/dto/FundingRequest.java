package com.dangdang.funding.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.sql.Timestamp;

public class FundingRequest {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    public static class Create{

        private String title;
        private int targetPrice;
        private String projectIntroduction;
        private String company;
        private int businessLicenseNum;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Seoul" )
        private Timestamp endDate;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Seoul")
        private Timestamp startDate;

    }
}
