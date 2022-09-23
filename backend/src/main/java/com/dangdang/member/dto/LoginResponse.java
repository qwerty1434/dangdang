package com.dangdang.member.dto;

import lombok.*;

public class LoginResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UserInfo {
        private String nickname;
        private boolean isAdmin;
        private String email;
        private String accessToken;
        public static LoginResponse.UserInfo build(String nickname, boolean isAdmin, String email, String accessToken) {
            return UserInfo.builder()
                    .nickname(nickname)
                    .isAdmin(isAdmin)
                    .email(email)
                    .accessToken(accessToken)
                    .build();
        }
    }
}
