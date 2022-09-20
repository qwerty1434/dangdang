package com.dangdang.reward.domain;

import com.dangdang.category.domain.Category;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.dto.FundingRequest;
import com.dangdang.member.domain.Maker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class Reward {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id", nullable = false)
    private Funding funding;

    @Column(name = "price" , nullable = false)
    private int price;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "content" , nullable = false)
    private String content;

    public static Reward RewardCreate(Funding funding, int price, String title, String content){
        return Reward.builder()
                .funding(funding)
                .price(price)
                .title(title)
                .content(content)
                .build();
    }
}
