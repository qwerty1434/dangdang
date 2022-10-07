package com.dangdang.member.dto;

import com.dangdang.funding.dto.FundingSimpleInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MakerInfoResponse {

    private String id;

    private String companyName;

    private String img;

    // 일단은 0 >> 블록체인 연결 후 추가하기
    private int supporterNum=0;

    private Long fundingSum;

    private List<FundingSimpleInfo> fundingList;

}
