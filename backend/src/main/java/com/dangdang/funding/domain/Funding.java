package com.dangdang.funding.domain;

import com.dangdang.category.domain.Category;
import com.dangdang.funding.dto.FundingRequest;
import com.dangdang.member.domain.Maker;
import com.dangdang.order.domain.OrderHistory;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class Funding {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id", nullable = false)
    private Maker maker;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "title")
    private String title;

    @Column(name = "target_price")
    private int targetPrice;

    @Builder.Default
    @Column(name = "now_price" , nullable = false)
    private int nowPrice = 0;

    @Column(name = "project_introduction")
    private String projectIntroduction;

    @Column(name = "company" , nullable = false)
    private String company;

    @Column(name = "business_license_num" , nullable = false)
    private String businessLicenseNum;

    @Column(name = "end_date" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp endDate;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp startDate;


    @Builder.Default
    @ColumnDefault("0")
    @Column(name = "state", nullable = false)
    private int state = 0;

    @Column(name = "detail_state", nullable = false)
    private String detailState;

    @Builder.Default
    @OneToMany(mappedBy = "funding",cascade = CascadeType.ALL)
    private List<OrderHistory> supporters = new LinkedList<>();

    public static Funding FundingCreate(FundingRequest.Create request, Maker maker, Category category, String detailState, List<OrderHistory> supporters){
        return Funding.builder()
                .maker(maker)
                .category(category)
                .title(request.getTitle())
                .targetPrice(request.getTargetPrice())
                .projectIntroduction(request.getProjectIntroduction())
                .company(maker.getCompanyName())
                .businessLicenseNum(maker.getCompanyNumber())
                .endDate(request.getEndDate())
                .startDate(request.getStartDate())
                .detailState(detailState)
                .supporters(supporters)
                .build();
    }

    public void test(){
        System.out.println(this.supporters.size());
    }

    public void addSupporter(OrderHistory orderhistory){
        supporters.add(orderhistory);
    }

    public void subSupporter(OrderHistory orderHistory){
        supporters.remove(orderHistory);
    }
}
