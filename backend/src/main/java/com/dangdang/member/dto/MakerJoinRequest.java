package com.dangdang.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MakerJoinRequest {

    private String companyNumber;

    private String companyName;

    private String openDay;

    private String managerName;

    private String img;

}
