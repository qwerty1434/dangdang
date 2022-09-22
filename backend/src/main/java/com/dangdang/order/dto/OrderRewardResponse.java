package com.dangdang.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRewardResponse {
    private String title;
    private int count;
    private int totalPrice;
    private int sequence;

    public static OrderRewardResponse orderRewardResponseCreate(String title, int count, int totalPrice, int sequence){
        return OrderRewardResponse.builder()
                .title(title)
                .count(count)
                .totalPrice(totalPrice)
                .sequence(sequence)
                .build();
    }
}
