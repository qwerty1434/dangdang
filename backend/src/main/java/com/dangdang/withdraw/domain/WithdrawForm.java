package com.dangdang.withdraw.domain;

import com.dangdang.funding.domain.Funding;
import com.dangdang.withdraw.dto.WithdrawFormRequest;
import com.sun.istack.NotNull;
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
@Setter
public class WithdrawForm {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
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

    @NotNull
    @Builder.Default
    @ColumnDefault("false")
    private boolean isDelete = false;

    public static WithdrawForm withdrawFormCreate(Funding funding, WithdrawFormRequest.Create request){
        return WithdrawForm.builder()
                .funding(funding)
                .amountUsed(request.getAmountUsed())
                .purpose(request.getPurpose())
                .build();
    }
}
