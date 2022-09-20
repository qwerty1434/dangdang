package com.dangdang.withdraw.dto;

import lombok.*;

public class WithdrawFormRequest {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    public static class Create {

        private int amountUsed;
        private String purpose;

    }
}
