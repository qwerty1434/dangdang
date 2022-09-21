package com.dangdang.funding.dto;

import lombok.*;

import java.util.List;

public class FundingResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Regist{

        private boolean result;

        public static FundingResponse.Regist build(boolean result){
            return FundingResponse.Regist.builder()
                    .result(result)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class fundingList{

        private List<FundingContent> fundingList;

        public static FundingResponse.fundingList build(List<FundingContent> fundingList){
            return FundingResponse.fundingList.builder()
                    .fundingList(fundingList)
                    .build();
        }
    }







}
