package com.dangdang.order.dto;

import lombok.*;

import java.util.List;

public class OrderResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Regist{

        private boolean result;

        public static OrderResponse.Regist build(boolean result){
            return OrderResponse.Regist.builder()
                    .result(result)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UserOrderList{

        private List<UserOrder> userOrderList;
        private int orderTotalPrice;

        public static OrderResponse.UserOrderList build(List<UserOrder> userOrderList, int orderTotalPrice){
            return OrderResponse.UserOrderList.builder()
                    .userOrderList(userOrderList)
                    .orderTotalPrice(orderTotalPrice)
                    .build();
        }
    }


}
