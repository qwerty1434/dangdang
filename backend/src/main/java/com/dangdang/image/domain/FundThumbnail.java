package com.dangdang.image.domain;


import com.dangdang.funding.domain.Funding;
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
public class FundThumbnail {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id", nullable = false)
    private Funding funding;

    @Column(name = "img" , nullable = false)
    private String img;

    @Column(name = "sequence" , nullable = false)
    private int sequence;

    public static FundThumbnail FundThumbnailCreate(Funding funding, FundThumbnail fundThumbnail){
        return FundThumbnail.builder()
                .funding(funding)
                .img(fundThumbnail.getImg())
                .sequence(fundThumbnail.getSequence())
                .build();
    }


}
