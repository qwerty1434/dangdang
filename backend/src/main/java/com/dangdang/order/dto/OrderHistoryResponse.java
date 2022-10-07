package com.dangdang.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderHistoryResponse {
    private String address;
    private int totalPrice;

    public static OrderHistoryResponse OrderHistoryResponseCreate(String address, int totalPrice){
        return OrderHistoryResponse.builder()
                .address(address)
                .totalPrice(totalPrice)
                .build();
    }
}
