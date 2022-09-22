package com.dangdang.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FundingSimpleInfo {

    private String id;

    private String title;

    private int targetPrice;

    private int nowPrice;

    private Timestamp endDate;

    private String detailState;

}
