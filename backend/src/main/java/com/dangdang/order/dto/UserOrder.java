package com.dangdang.order.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserOrder {
    private List<OrderRewardResponse> orderReward;
    private OrderHistoryResponse orderHistory;

    public static UserOrder UserOrderCreate(List<OrderRewardResponse> orderRewardResponseList, OrderHistoryResponse orderHistoryResponse){
        return UserOrder.builder()
                .orderReward(orderRewardResponseList)
                .orderHistory(orderHistoryResponse)
                .build();
    }


}
