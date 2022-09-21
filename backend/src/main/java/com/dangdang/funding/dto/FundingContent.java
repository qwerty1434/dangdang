package com.dangdang.funding.dto;

import com.dangdang.funding.domain.Funding;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class FundingContent {
    private String id;
    private String title;
    private String companyName;
    private int nowPrice;
    private String detailState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
    private Timestamp startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
    private Timestamp endDate;
    private int achieveRate;
    private String img;
    private int remainDays;
    private String category;

    public static FundingContent Create(Funding funding, int achieveRate, String img, int remainDays, String category){
        return FundingContent.builder()
                .id(funding.getId().toString())
                .title(funding.getTitle())
                .companyName(funding.getCompany())
                .nowPrice(funding.getNowPrice())
                .achieveRate(achieveRate)
                .img(img)
                .startDate(funding.getStartDate())
                .endDate(funding.getEndDate())
                .remainDays(remainDays)
                .category(category)
                .build();
    }
}
