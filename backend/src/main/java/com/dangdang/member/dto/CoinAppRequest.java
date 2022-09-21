package com.dangdang.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoinAppRequest {

    private String fundingId;

    private int amountUsed;

    private String purpose;

}
