package com.dangdang.order.domain;

import com.dangdang.funding.domain.Funding;
import com.dangdang.member.domain.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

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
public class OrderHistory {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id", nullable = false)
    private Funding funding;

    @Column(name = "total_price" , nullable = false)
    private int totalPrice;

    @Column(name = "address" , nullable = false)
    private String address;

    @Column(name = "phone_number" , nullable = false)
    private String phoneNumber;

    @Column(name = "email" , nullable = false)
    private String email;

    public static OrderHistory OrderHistoryCreate(User user, Funding funding, int totalPrice, String address, String phoneNumber, String email ) {
        return OrderHistory.builder()
                .user(user)
                .funding(funding)
                .totalPrice(totalPrice)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }


}
