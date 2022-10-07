package com.dangdang.withdraw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawFormResponse {

    private String id;

    private int amountUsed;

    private String purpose;

    private Timestamp date;

    private String process;

    private String referReason;

}
