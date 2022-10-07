package com.dangdang.funding.dto;

import com.dangdang.blockchain.dto.UseHistoryResponse;
import com.dangdang.image.domain.BodyImage;
import com.dangdang.image.domain.FundThumbnail;
import com.dangdang.image.dto.BodyImgResponse;
import com.dangdang.image.dto.FundThumbnailResponse;
import com.dangdang.member.dto.MakerResponse;
import com.dangdang.reward.domain.Reward;
import com.dangdang.reward.dto.RewardResponse;
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

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DetailFunding{

        private List<RewardResponse.Res> rewards;
        private List<BodyImgResponse.Res> bodyImages;
        private List<FundThumbnailResponse.Res> fundThumbnails;
        private FundingContent fundingContent;
        private MakerResponse.Res maker;


        public static FundingResponse.DetailFunding build(List<RewardResponse.Res> rewards,List<BodyImgResponse.Res> bodyImages, List<FundThumbnailResponse.Res> fundThumbnails, FundingContent fundingContent , MakerResponse.Res maker){
            return DetailFunding.builder()
                    .rewards(rewards)
                    .bodyImages(bodyImages)
                    .fundThumbnails(fundThumbnails)
                    .fundingContent(fundingContent)
                    .maker(maker)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RaisedListAndRemained{

        private List<UseHistoryResponse> useHistory;
        private int totalRemain;

        public static FundingResponse.RaisedListAndRemained build(List<UseHistoryResponse> useHistory, int totalRemain){
            return FundingResponse.RaisedListAndRemained.builder()
                    .useHistory(useHistory)
                    .totalRemain(totalRemain)
                    .build();
        }
    }







}
