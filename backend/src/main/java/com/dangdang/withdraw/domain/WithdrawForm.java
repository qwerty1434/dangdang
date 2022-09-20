package com.dangdang.withdraw.domain;

import com.dangdang.funding.domain.Funding;
import com.dangdang.withdraw.dto.WithdrawFormRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawForm {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id", nullable = false)
    private Funding funding;

    @Column(name = "amount_used" , nullable = false)
    private int amountUsed;

    @Column(name = "purpose" , nullable = false)
    private String purpose;

    @CreationTimestamp
    @Column(name = "date" , nullable = false)
    private Timestamp date;

    @Builder.Default
    @Column(name = "process" , nullable = false)
    private String process = "대기중";

    @Column(name = "refer_reason" )
    private String referReason;

    public static WithdrawForm withdrawFormCreate(Funding funding, WithdrawFormRequest.Create request){
        return WithdrawForm.builder()
                .funding(funding)
                .amountUsed(request.getAmountUsed())
                .purpose(request.getPurpose())
                .build();
    }
}
