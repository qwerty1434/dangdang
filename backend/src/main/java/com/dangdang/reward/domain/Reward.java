package com.dangdang.reward.domain;

import com.dangdang.category.domain.Category;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.dto.FundingRequest;
import com.dangdang.member.domain.Maker;
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
@ToString
public class Reward {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id", nullable = false)
    private Funding funding;

    @Column(name = "price")
    private int price;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "sequence" , nullable = false)
    private int sequence;

    public static Reward RewardCreate(Funding funding, Reward reward){
        return Reward.builder()
                .funding(funding)
                .price(reward.getPrice())
                .title(reward.getTitle())
                .content(reward.getContent())
                .sequence(reward.getSequence())
                .build();
    }
}
