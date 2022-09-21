package com.dangdang.withdraw.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.repository.FundingRepository;
import com.dangdang.member.domain.Maker;
import com.dangdang.member.domain.User;
import com.dangdang.member.dto.CoinAppRequest;
import com.dangdang.member.repository.MakerRepository;
import com.dangdang.member.repository.UserRepository;
import com.dangdang.withdraw.domain.WithdrawForm;
import com.dangdang.withdraw.dto.WithdrawFormResponse;
import com.dangdang.withdraw.repository.WithdrawRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-local.properties"),
        @PropertySource("classpath:application-personal.properties")
})
public class WithdrawService {

    private final WithdrawRepository withdrawRepository;
    private final FundingRepository fundingRepository;
    private final UserRepository userRepository;

    public void coinApplication(CoinAppRequest input) {

        ZonedDateTime datetime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        Timestamp timestamp = Timestamp.valueOf(datetime.toLocalDateTime());

        // 토큰에서 userid 뽑아오기. 아직 토큰이 없으므로 static data로 대체
        String userId = "8d146241-e2ca-4950-aac8-55f1135f3473";

        User user = userRepository.findById(UUID.fromString(userId)).get();
        Funding funding = fundingRepository.findById(UUID.fromString(input.getFundingId())).get();

        WithdrawForm form = WithdrawForm.builder().id(UUID.fromString(userId)).funding(funding).amountUsed(input.getAmountUsed())
                .purpose(input.getPurpose()).date(timestamp).build();

        withdrawRepository.save(form);
    }
    
    public List<WithdrawFormResponse> applicationList(String fundingId){

        List<WithdrawForm> list = withdrawRepository.findAllByFundingId(fundingId);
        List<WithdrawFormResponse> output = new LinkedList<>();
        for(WithdrawForm w : list){
            WithdrawFormResponse now = new WithdrawFormResponse(w.getId().toString(),w.getAmountUsed(),w.getPurpose(),w.getDate(),w.getProcess(),w.getReferReason());
            output.add(now);
        }

        return output;
    }
}
