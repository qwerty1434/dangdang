package com.dangdang.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MakerCompanyInfo { // 추후 사업자 정보 api 추가하며 정보 추가 예정

    private String companyName;

    private String companyNumber;

}
