package com.dangdang.member.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Maker implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @Type(type="uuid-char")
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @MapsId //@MapsId 는 @id로 지정한 컬럼에 @OneToOne 이나 @ManyToOne 관계를 매핑시키는 역할
    private User user;

    @NotNull
    private String companyNumber;

    @NotNull
    private String companyName;

    private String img;

    @NotNull
    @Builder.Default
    private Long fundingSum=0L;

    public void addFundingSum(Long fundingMoney){
        this.fundingSum+=fundingMoney;
    }

}
