package com.dangdang.image.dto;

import lombok.*;

public class BodyImgResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Res{

        private String img;
        private int sequence;

        public static BodyImgResponse.Res build(String img, int sequence){
            return BodyImgResponse.Res.builder()
                    .img(img)
                    .sequence(sequence)
                    .build();
        }
    }
}
