package com.dangdang.member.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @NotNull
    private String companyNumber;

    @NotNull
    private String companyName;

    private String img;

    @NotNull
    @Builder.Default
    private Long fundingSum=0L;

    public Maker(User user, String companyNumber, String companyName, Long fundingSum) {
        this.user = user;
        this.companyNumber = companyNumber;
        this.companyName = companyName;
        this.fundingSum = fundingSum;
    }
}
