package com.dangdang.member.dto;

import lombok.*;

public class TokenResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class NewToken {
        private String accessToken;
        private String email;

        public static NewToken build(String accessToken, String email) {
            return NewToken.builder()
                    .accessToken(accessToken)
                    .email(email)
                    .build();
        }
    }
}
