package com.dangdang.image.domain;

import com.dangdang.funding.domain.Funding;
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
public class BodyImage {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id", nullable = false)
    private Funding funding;

    @Column(name = "img" , nullable = false)
    private String img;

    @Column(name = "sequence" , nullable = false)
    private int sequence;

    public static BodyImage BodyImageCreate(Funding funding, String img, int sequence){
        return BodyImage.builder()
                .funding(funding)
                .img(img)
                .sequence(sequence)
                .build();
    }


}
