package com.dangdang.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckCoinResponse {
    int won;

    public static CheckCoinResponse build(int won){
        return CheckCoinResponse.builder()
                .won(won)
                .build();
    }


}
