package com.dangdang.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessResponse {
    private String companyNo;
    private String companyName;
    private String openDay;
}
