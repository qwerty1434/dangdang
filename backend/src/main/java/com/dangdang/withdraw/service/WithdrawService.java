package com.dangdang.withdraw.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.blockchain.service.EthereumService;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.repository.FundingRepository;
import com.dangdang.member.domain.Maker;
import com.dangdang.member.domain.User;
import com.dangdang.member.dto.CoinAppRequest;
import com.dangdang.member.dto.MakerInfoResponse;
import com.dangdang.member.exception.InsufficientfundsException;
import com.dangdang.member.exception.NotValidateAccessToken;
import com.dangdang.member.repository.MakerRepository;
import com.dangdang.member.repository.UserRepository;
import com.dangdang.member.service.UserService;
import com.dangdang.util.JWTUtil;
import com.dangdang.withdraw.domain.WithdrawForm;
import com.dangdang.withdraw.dto.WithdrawFormResponse;
import com.dangdang.withdraw.repository.WithdrawRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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

    private final EthereumService ethereumService;

    private final JWTUtil jwtUtil;

    public void coinApplication(CoinAppRequest input, HttpServletRequest req) throws NotValidateAccessToken, InsufficientfundsException {

        System.out.println(input.getPurpose());

        ZonedDateTime datetime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        Timestamp timestamp = Timestamp.valueOf(datetime.toLocalDateTime());

        // Header??? ???????????? ???????????? ?????? userId ???
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        User user = userRepository.findById(UUID.fromString(userId)).get();
        Funding funding = fundingRepository.findById(UUID.fromString(input.getFundingId())).get();

        WithdrawForm form = WithdrawForm.builder().id(UUID.fromString(userId)).funding(funding).amountUsed(input.getAmountUsed())
                .purpose(input.getPurpose()).date(timestamp).build();

        WithdrawForm savedform = withdrawRepository.save(form);

        // ????????? ???????????? , ?????? ?????? ????????? ??????????????? ????????? ??????

        int remain = ethereumService.checkLeftMoneyInFunding(funding.getId().toString(), user.getPublicKey());
        if(remain < input.getAmountUsed()){
            savedform.setProcess("?????????");
            savedform.setReferReason("?????? ??????");
            withdrawRepository.save(savedform);
            throw new InsufficientfundsException();
        }else{
            savedform.setProcess("?????????");
            withdrawRepository.save(savedform);
            ethereumService.sendMoneyToManufacture(funding.getId().toString(), input.getAmountUsed(), input.getPurpose());
        }


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
