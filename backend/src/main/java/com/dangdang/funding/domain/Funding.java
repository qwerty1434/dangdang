package com.dangdang.funding.domain;

import com.dangdang.category.domain.Category;
import com.dangdang.funding.dto.FundingRequest;
import com.dangdang.member.domain.Maker;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
public class Funding {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id", nullable = false)
    private Maker maker;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "target_price" , nullable = false)
    private int targetPrice;

    @Builder.Default
    @Column(name = "now_price" , nullable = false)
    private int nowPrice = 0;

    @Column(name = "project_introduction" , nullable = false)
    private String projectIntroduction;

    @Column(name = "company" , nullable = false)
    private String company;

    @Column(name = "business_license_num" , nullable = false)
    private int businessLicenseNum;

    @Column(name = "end_date" , nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp endDate;

    @Column(name = "start_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp startDate;


    @Builder.Default
    @ColumnDefault("0")
    @Column(name = "state", nullable = false)
    private int state = 0;

    @Builder.Default
//    @ColumnDefault("작성 중")
    @Column(name = "detail_state", nullable = false)
    private String detailState = "작성 중";

    public static Funding FundingCreate(FundingRequest.Create request, Maker maker, Category category){
        return Funding.builder()
                .maker(maker)
                .category(category)
                .title(request.getTitle())
                .targetPrice(request.getTargetPrice())
                .projectIntroduction(request.getProjectIntroduction())
                .company(request.getCompany())
                .businessLicenseNum(request.getBusinessLicenseNum())
                .endDate(request.getEndDate())
                .startDate(request.getStartDate())
                .build();
    }
}
