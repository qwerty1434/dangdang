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
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.mapstruct.MappingConstants.NULL;

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
    private final BusinessService businessService;
    private final JWTUtil jwtUtil;

    public void join(BusinessRequest input, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken, IOException, ParseException {


        // Header에 담겨있는 토큰으로 찾은 userId 값
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));

        BusinessRequest b_input = new BusinessRequest(input.getCompanyNo(),
                input.getCompanyName(), input.getOpenDay(), input.getManagerName());
        String valid = businessService.parseJSON(b_input);
        if(valid.equals("01")) {
            Maker maker = Maker.builder().user(user.get()).companyNumber(input.getCompanyNo())
                    .companyName(input.getCompanyName()).img("").fundingSum(0L).build();
            makerRepository.save(maker);
        } else {
            throw new NotFoundException("등록되지 않은 사업자 정보입니다.");
        }
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
            for(OrderHistory h: histories){
                set.add(h.getUser().getId().toString());
            }
        }
        return new TotalSupportResponse(set.size());
    }

    public List<FundingListResponse> findFundingList(MakerFundingListRequest input, Pageable pageable, HttpServletRequest req){

        String uuid = input.getMakerId();
        User user = userRepository.findById(UUID.fromString(uuid)).get();

        List<Funding> fundings = fundingRepository.findByMakerIdAndState(user.getId(),input.getState(),pageable);
        List<FundingListResponse> output = new LinkedList<>();
        for(Funding f: fundings){
            LocalDateTime start = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            LocalDateTime end = f.getEndDate().toLocalDateTime();
            int day = (int) ChronoUnit.DAYS.between(start, end);

            List<FundThumbnail> imgList = fundThumbnailRepository.findByFundingId(f.getId().toString());
            FundThumbnail ff=new FundThumbnail();
            for(FundThumbnail img : imgList){
                if(img.getSequence()==0) ff=img;
            }
            FundingListResponse result = new FundingListResponse(f.getId().toString(), f.getTitle(), f.getCompany(),
                    ff.getImg(), f.getNowPrice(), (f.getNowPrice()*100/ f.getTargetPrice()),
                    f.getEndDate(),f.getDetailState(), day, f.getCategory().getType());
            output.add(result);
        }

        return output;
    }

    public void changeImg(MakerUrlRequest input, HttpServletRequest req) throws NotValidateAccessToken, NotFoundException {
        String uuid = jwtUtil.getUserIdByHeaderAccessToken(req);
        Maker maker = makerRepository.findByUserId(uuid);
        if(maker==null) throw new NotFoundException("유효한 사용자가 아닙니다.");

        maker.setImg(input.getImgUrl());
        makerRepository.save(maker);
    }

    public void changeMyImg(MakerEmailUrlRequest input) throws NotValidateAccessToken, NotFoundException {
        User user = userRepository.findByEmail(input.getEmail());
        Maker maker = makerRepository.findByUserId(user.getId().toString());
        if(maker==null) throw new NotFoundException("유효한 사용자가 아닙니다.");
        maker.setImg(input.getImgUrl());
        makerRepository.save(maker);
    }

    public MakerCompanyResponse findCompanyInfo(HttpServletRequest req) throws NotValidateAccessToken, NotFoundException {
        String uuid = jwtUtil.getUserIdByHeaderAccessToken(req);
        Maker maker = makerRepository.findByUserId(uuid);
        if(maker==null) throw new NotFoundException("유효한 사용자가 아닙니다.");

        return new MakerCompanyResponse(maker.getCompanyName(), maker.getCompanyNumber());
    }

}