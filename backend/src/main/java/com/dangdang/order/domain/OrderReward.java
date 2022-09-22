package com.dangdang.order.domain;

import com.dangdang.reward.domain.Reward;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderReward {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id", nullable = false)
    private Reward reward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_history_id", nullable = false)
    private OrderHistory orderHistory;

    @Column(name = "count" , nullable = false)
    private int count;

    @Column(name = "item_total_price" , nullable = false)
    private int itemTotalPrice;

    public static OrderReward OrderRewardCreate(Reward reward, OrderHistory orderHistory, int count , int itemTotalPrice){
        return OrderReward.builder()
                .reward(reward)
                .orderHistory(orderHistory)
                .count(count)
                .itemTotalPrice(itemTotalPrice)
                .build();
    }

}
