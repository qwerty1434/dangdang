package com.dangdang.member.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.dto.FundingSimpleInfo;
import com.dangdang.funding.repository.FundingRepository;
import com.dangdang.image.domain.FundThumbnail;
import com.dangdang.image.repository.FundThumbnailRepository;
import com.dangdang.member.domain.Maker;
import com.dangdang.member.domain.User;
import com.dangdang.member.dto.*;
import com.dangdang.member.exception.NotValidateAccessToken;
import com.dangdang.member.repository.MakerRepository;
import com.dangdang.member.repository.UserRepository;
import com.dangdang.order.domain.OrderHistory;
import com.dangdang.order.repository.OrderHistoryRepository;
import com.dangdang.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-local.properties"),
        @PropertySource("classpath:application-personal.properties")
})
public class MakerService {

    private final MakerRepository makerRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final FundingRepository fundingRepository;
    private final OrderHistoryRepository historyRepository;
    private final FundThumbnailRepository fundThumbnailRepository;

    private final JWTUtil jwtUtil;

    public void join(MakerJoinRequest input, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {


        // Header에 담겨있는 토큰으로 찾은 userId 값
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));

        /*
        사업자 등록 여부 등도 확인 해서 넣을 예정
        현재 생각하고 있는 방식
        1. 사업자 등록 여부와 관련 정보 추출 api 작성
        2. 프론트에서 해당 정보 받고, 메이커 등록 시 관련 정보 body에 담아서 전송
        3. 백이 받아서 다시 확인 후 메이커 생성
         */

        // 메이커 생성 시 하나의 사업자번호를 한명만 등록하게 했었는지 파악 후 예외처리 추가
        Maker maker = Maker.builder().user(user.get()).companyNumber(input.getCompanyNumber())
                .companyName(input.getCompanyName()).img(input.getImg()).fundingSum(0L).build();
        makerRepository.save(maker);
    }

    public MakerInfoResponse pjtInfo(String makerId) throws NotFoundException {

        userService.vaildUserId(makerId);
        Maker maker = makerRepository.findByUserId(makerId);
        if (maker.getCompanyNumber()==null) throw new NotFoundException("사업자 등록이 되어있지 않은 유저입니다.");

        List<Funding> fundingList = fundingRepository.findByMakerId(makerId);
        List<FundingSimpleInfo> output = new LinkedList<>();
        for(Funding f:fundingList){
            FundingSimpleInfo result = new FundingSimpleInfo(f.getId().toString(), f.getTitle(), f.getTargetPrice(), f.getNowPrice(),
                    f.getEndDate(), f.getDetailState());
            output.add(result);
        }

        //이후 블록체인 연결하기
        int supportNum = 0;
        MakerInfoResponse info = new MakerInfoResponse(makerId, maker.getCompanyName(), maker.getImg(), supportNum, maker.getFundingSum(), output);

        return info;
    }

    public MakerCompanyInfo makerInfo(String makerId) throws NotFoundException {
        userService.vaildUserId(makerId);
        Maker maker = makerRepository.findByUserId(makerId);
        if (maker.getCompanyNumber()==null) throw new NotFoundException("사업자 등록이 되어있지 않은 유저입니다.");

        return new MakerCompanyInfo(maker.getCompanyName(),maker.getCompanyNumber());
    }

    public TotalFundResponse totalFund(String makerId){
        Maker maker = makerRepository.findByUserId(makerId);
        return new TotalFundResponse(maker.getFundingSum());
    }

    public TotalSupportResponse totalSupport(String makerId){

        List<Funding> fundingList = fundingRepository.findByMakerId(makerId);
        Set<String> set = new HashSet<>();

        for(Funding f : fundingList){
            if(!f.getDetailState().equals("펀딩 성공")) continue;
            List<OrderHistory> histories = historyRepository.findByFundingId(f.getId());
            System.out.println("histories.size() = " + histories.size());
            for(OrderHistory h: histories){
                set.add(h.getUser().getId().toString());
            }
        }
        return new TotalSupportResponse(set.size());
    }

    public List<FundingListResponse> findFundingList(int state, Pageable pageable, HttpServletRequest req){
        //  토큰으로 가져오기

        String uuid = "8d146241-e2ca-4950-aac8-55f1135f3473";
        User user = userRepository.findById(UUID.fromString(uuid)).get();

        List<Funding> fundings = fundingRepository.findByState(state,pageable);
        List<FundingListResponse> output = new LinkedList<>();

        for(Funding f:fundings){
            LocalDateTime start = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            LocalDateTime end = f.getEndDate().toLocalDateTime();
            int day = (int) ChronoUnit.DAYS.between(start, end);
            System.out.println("f.getId() = " + f.getId());

            List<FundThumbnail> imgList = fundThumbnailRepository.findByFundingId(f.getId().toString());
            FundThumbnail ff=new FundThumbnail();
            for(FundThumbnail img : imgList){
                if(img.getSequence()==0) ff=img;
            }
            FundingListResponse result = new FundingListResponse(f.getId().toString(), f.getTitle(), f.getCompany(),
                    ff.getImg(), f.getNowPrice(), (1.0*f.getNowPrice()/ f.getTargetPrice()),
                    f.getEndDate(),f.getDetailState(), day, f.getCategory().getType());
            output.add(result);
        }

        return output;
    }
}
