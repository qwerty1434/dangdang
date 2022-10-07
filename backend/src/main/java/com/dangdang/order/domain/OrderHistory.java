package com.dangdang.order.domain;

import com.dangdang.funding.domain.Funding;
import com.dangdang.member.domain.User;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderHistory {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id", nullable = false)
    private Funding funding;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "address" , nullable = false)
    private String address;

    @Column(name = "phone_number" , nullable = false)
    private String phoneNumber;

    @Column(name = "order_date", nullable = false)
    @CreationTimestamp
    private Timestamp orderDate;


    public static OrderHistory OrderHistoryCreate(User user, Funding funding,  String address, String phoneNumber) {
        return OrderHistory.builder()
                .user(user)
                .funding(funding)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();
    }


}
