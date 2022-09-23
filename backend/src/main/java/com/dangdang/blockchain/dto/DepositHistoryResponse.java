package com.dangdang.blockchain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepositHistoryResponse {
    String address;
    int won;
    Timestamp time;
    String nickname;
    boolean isAnonymous;
}
