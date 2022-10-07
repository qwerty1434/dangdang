package com.dangdang.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoinAppRequest {

    private String fundingId;

    private int amountUsed;

    private String purpose;

}
