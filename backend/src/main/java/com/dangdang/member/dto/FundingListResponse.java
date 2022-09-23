package com.dangdang.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FundingListResponse {

    private String id;
    private String title;
    private String companyName;
    private String img;
    private int nowPrice;
    private Double achieveRate;
    private Timestamp endDate;
    private String detailState;
    private int remainDays;
    private String category;

}
