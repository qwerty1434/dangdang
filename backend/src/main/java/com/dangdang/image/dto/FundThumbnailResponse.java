package com.dangdang.image.dto;

import lombok.*;

public class FundThumbnailResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Res{

        private String img;
        private int sequence;

        public static FundThumbnailResponse.Res build(String img, int sequence){
            return FundThumbnailResponse.Res.builder()
                    .img(img)
                    .sequence(sequence)
                    .build();
        }
    }
}
