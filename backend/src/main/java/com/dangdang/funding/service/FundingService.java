package com.dangdang.funding.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.blockchain.dto.UseHistoryResponse;
import com.dangdang.blockchain.service.EthereumService;
import com.dangdang.category.domain.Category;
import com.dangdang.category.repository.CategoryRepository;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.dto.FundingContent;
import com.dangdang.funding.dto.FundingRequest;
import com.dangdang.funding.dto.FundingResponse;
import com.dangdang.funding.repository.FundingRepository;
import com.dangdang.image.domain.BodyImage;
import com.dangdang.image.domain.FundThumbnail;
import com.dangdang.image.dto.BodyImgResponse;
import com.dangdang.image.dto.FundThumbnailResponse;
import com.dangdang.image.repository.BodyImageRepository;
import com.dangdang.image.repository.FundThumbnailRepository;
import com.dangdang.member.domain.Maker;
import com.dangdang.member.domain.User;
import com.dangdang.member.dto.MakerResponse;
import com.dangdang.member.dto.TotalSupportResponse;
import com.dangdang.member.exception.NotValidateAccessToken;
import com.dangdang.member.repository.MakerRepository;
import com.dangdang.member.repository.UserRepository;
import com.dangdang.member.service.MakerService;
import com.dangdang.reward.domain.Reward;
import com.dangdang.reward.dto.RewardResponse;
import com.dangdang.reward.repository.RewardRepository;
import com.dangdang.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
public class FundingService {

    private final FundingRepository fundingRepository;
    private final RewardRepository rewardRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final MakerRepository makerRepository;

    private final FundThumbnailRepository fundThumbnailRepository;

    private final BodyImageRepository bodyImageRepository;

    private final EthereumService ethereumService;

    private final JWTUtil jwtUtil;

    private final MakerService makerService;


    public FundingResponse.Regist RegistFunding(FundingRequest.Create request, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        // Header??? ???????????? ???????????? ?????? userId ???
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));
        Maker maker = makerRepository.findByUserId(user.get().getId().toString());
        System.out.println(maker);

        Category category = categoryRepository.findByType(request.getCategory());
        Funding funding = Funding.FundingCreate(request, maker, category, "?????? ??????");
        System.out.println(funding+" ????????? ??????");
        UUID fundingId = fundingRepository.save(funding).getId();
        this.RegistReward(request.getRewards(), funding);
        this.RegistThumbnail(request.getThumbnails(), funding);
        this.RegistBodyImg(request.getBodyImgs(), funding);

        //?????? ????????? ?????? ?????? ?????? ?????? ?????? ????????? ?????? ?????? ???
        //??????????????? ?????? ????????? ?????? ??? ????????????????????? ??????
        ethereumService.createFundingInBlockChain(String.valueOf(fundingId), user.get().getPublicKey());



        return FundingResponse.Regist.build(true);
    }

    public FundingResponse.Regist TempStorage(FundingRequest.Create request , HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {
        // Header??? ???????????? ???????????? ?????? userId ???
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));

        Maker maker = makerRepository.findByUserId(user.get().getId().toString());

        Category category = categoryRepository.findByType(request.getCategory());

        Funding funding = Funding.FundingCreate(request, maker, category, "?????????");
        fundingRepository.save(funding);
        if(request.getRewards() != null){
            this.RegistReward(request.getRewards(), funding);
        }
        if(request.getThumbnails() != null){
            this.RegistThumbnail(request.getThumbnails(), funding);
        }
        if(request.getBodyImgs() != null){
            this.RegistBodyImg(request.getBodyImgs(), funding);
        }
        return FundingResponse.Regist.build(true);
    }

    public void RegistReward(List<Reward> rewards, Funding funding){
        for(int i = 0; i < rewards.size(); i++){
            Reward reward = Reward.RewardCreate(funding, rewards.get(i));
            rewardRepository.save(reward);
        }
    }

    public void RegistThumbnail(List<FundThumbnail> thumbnails, Funding funding){
        for(int i = 0; i < thumbnails.size(); i++){
            FundThumbnail thumbnail = FundThumbnail.FundThumbnailCreate(funding, thumbnails.get(i));
            fundThumbnailRepository.save(thumbnail);
        }
    }

    public void RegistBodyImg(List<BodyImage> bodyImages, Funding funding){
        for(int i = 0; i < bodyImages.size(); i++){
            BodyImage bodyImage = BodyImage.BodyImageCreate(funding, bodyImages.get(i));
            bodyImageRepository.save(bodyImage);
        }
    }

    // ????????? ????????????????????? ?????? ????????? ??????
    public FundingResponse.fundingList MakerFundingList(int state, Pageable pageable, HttpServletRequest req) throws NotValidateAccessToken {
        List<FundingContent> response = new ArrayList<>();

        // Header??? ???????????? ???????????? ?????? userId ???
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));

        List<Funding> fundings = fundingRepository.findByMakerIdAndState(user.get().getId(), state, pageable);
        for(int i = 0; i < fundings.size(); i++){
            Funding funding = fundings.get(i);
            response.add(this.changeResponseFunding(funding));
        }
        return FundingResponse.fundingList.build(response);
    }

    // ???????????? ??? ?????? ????????? ?????? ??????
    public FundingResponse.fundingList CategoryFundingList(String type, Pageable pageable) throws NotFoundException {

        List<FundingContent> response = new ArrayList<>();

        Category category = categoryRepository.findByType(type);
        if(category == null){
            throw new NotFoundException("???????????? ?????? ???????????? ?????????.");
        }

        List<Funding> fundings = fundingRepository.findByCategoryIdAndState(category.getId(),1 ,pageable);
        System.out.println(fundings);
        for(int i = 0; i < fundings.size(); i++){
            response.add(this.changeResponseFunding(fundings.get(i)));
        }

        return FundingResponse.fundingList.build(response);
    }

    // ????????? ?????? ?????? ????????? ?????? (?????????, ?????? ?????????, ?????? ?????? ?????? ??????)
    public FundingContent changeResponseFunding(Funding funding){
        if(funding.getDetailState().equals("?????????")){
            int achieveRate = 0;
            int day = 0;
            String categoryType = null;
            String img = null;
            if(funding.getCategory() != null){
                categoryType = funding.getCategory().getType();
            }
            FundThumbnail thumbnail = fundThumbnailRepository.findByFundingIdAndSequence(funding.getId().toString(), 0);
            if(thumbnail != null){
                img = thumbnail.getImg();
            }
            return FundingContent.Create(funding, achieveRate, img, day, categoryType);
        }
        int achieveRate = (int)(funding.getNowPrice() / (double)funding.getTargetPrice() * 100);
        FundThumbnail thumbnail = fundThumbnailRepository.findByFundingIdAndSequence(funding.getId().toString(), 0);
        String img = thumbnail.getImg();
        LocalDateTime end = funding.getEndDate().toLocalDateTime();
        int day = (int)ChronoUnit.DAYS.between(LocalDateTime.now(), end);
        String categoryType = funding.getCategory().getType();
        FundingContent fundingContent = FundingContent.Create(funding, achieveRate, img , day , categoryType);
        return fundingContent;
    }


    // ?????? ????????? ?????? (????????????, ?????????, ?????????)
    public FundingResponse.fundingList FundingList(String type, Pageable pageable) {
//        List<FundingContent> response = new ArrayList<>();
//        List<Funding> fundings = fundingRepository.findByState(1);
//        for(int i = 0; i < fundings.size(); i++){
//            response.add(this.changeResponseFunding(fundings.get(i)));
//        }
//        // ????????????
//        if(type.equals("endedAt")){
//            Collections.sort(response, new Comparator<FundingContent>() {
//                @Override
//                public int compare(FundingContent o1, FundingContent o2) {
//                    return o1.getEndDate().compareTo(o2.getEndDate());
//                }
//            });
//            // ?????? ??????
//        }else if(type.equals("popular")){
//            Collections.sort(response, new Comparator<FundingContent>() {
//                @Override
//                public int compare(FundingContent o1, FundingContent o2) {
//                    return -(o1.getAchieveRate()-o2.getAchieveRate());
//                }
//            });
//        }else{ // ?????? ??????
//            Collections.sort(response, new Comparator<FundingContent>() {
//                @Override
//                public int compare(FundingContent o1, FundingContent o2) {
//                    return -(o1.getStartDate().compareTo(o2.getStartDate()));
//                }
//            });
//        }
//        return FundingResponse.fundingList.build(response);



        List<FundingContent> response = new ArrayList<>();
        List<Funding> fundings = null;
        if(type.equals("popular")){
            fundings = fundingRepository.findByPopular(1, pageable);
        }else if(type.equals("plan")){
            fundings = fundingRepository.findByState(0, pageable);
        }
        else {
            fundings = fundingRepository.findByState(1, pageable);
        }
        for(int i = 0; i < fundings.size(); i++){
            response.add(this.changeResponseFunding(fundings.get(i)));
        }
        System.out.println(pageable.getPageSize());

        return FundingResponse.fundingList.build(response);

    }

    // ?????? ?????? ?????? ??????
    public FundingResponse.DetailFunding DetailFunding(String fundingId) throws NotFoundException {
        Optional<Funding> funding = fundingRepository.findById(UUID.fromString(fundingId));
        if(funding.get() == null){
            throw new NotFoundException("?????? ????????? ???????????? ????????????.");
        }
        FundingContent fundingContent = this.changeResponseFunding(funding.get());
        List<BodyImgResponse.Res> bodyImgRes = new ArrayList<>();
        List<BodyImage> bodyImages = bodyImageRepository.findByFundingId(funding.get().getId().toString());
        for(int i = 0; i < bodyImages.size(); i++){
            bodyImgRes.add(BodyImgResponse.Res.build(bodyImages.get(i).getImg(), bodyImages.get(i).getSequence()));
        }

        List<FundThumbnailResponse.Res> thumbnailsRes = new ArrayList<>();
        List<FundThumbnail> fundThumbnails = fundThumbnailRepository.findByFundingId(funding.get().getId().toString());
        for(int i = 0; i < fundThumbnails.size(); i++){
            thumbnailsRes.add(FundThumbnailResponse.Res.build(fundThumbnails.get(i).getImg(), fundThumbnails.get(i).getSequence()));
        }

        List<RewardResponse.Res> rewardRes = new ArrayList<>();
        List<Reward> rewards = rewardRepository.findByFundingId(funding.get().getId().toString());
        for(int i = 0; i < rewards.size(); i++){
            rewardRes.add(RewardResponse.Res.build(rewards.get(i).getId(), rewards.get(i).getPrice(),rewards.get(i).getTitle(), rewards.get(i).getContent(), rewards.get(i).getSequence()));
        }

        Maker maker = funding.get().getMaker();

        TotalSupportResponse supporters = makerService.totalSupport(maker.getId().toString());

        MakerResponse.Res makerRes = MakerResponse.Res.build(maker.getId(), maker.getCompanyNumber(), maker.getCompanyName(), maker.getImg(), maker.getFundingSum(), supporters.getSupportSum());

        return FundingResponse.DetailFunding.build(rewardRes,bodyImgRes,thumbnailsRes,fundingContent, makerRes);

    }
    @Scheduled(cron = "0 * * * * *") // 1??? ????????? ??????
    public void ScheduledFunding() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        String now = sdf.format(timestamp);
        Timestamp changeNow = Timestamp.valueOf(now);
        //????????? ?????? ???????????????(state 0 -> 1, detailState ???????????? -> ?????? ?????????)
        this.ChangeProceedingState(changeNow);
        //?????? ????????? -> ?????? (state 1 -> 2, ???????????? ?????? ?????? ??????, ?????? ????????? detailState ??????)
        this.ChangeEndState(changeNow);



    }

    // ?????? ?????? ????????? ????????? ?????? ???????????? ?????? ?????? ??????????????? ?????? ??????
    public void ChangeProceedingState(Timestamp now) {
        List<Funding> fundings = fundingRepository.findByDetailStateAndStartDate("?????? ??????" , now);
        for(int i = 0; i < fundings.size(); i++){
            Funding funding = fundings.get(i);
            funding.setState(1);
            funding.setDetailState("?????? ?????????");
            fundingRepository.save(funding);
            System.out.println("?????? ?????? ??????????????? ?????? ??????");

            // ???????????? ???????????? ?????? ?????? ????????? ??????
            ethereumService.setFundingStateToFundRaising(funding.getId().toString());
        }

    }

    // ?????? ???????????? ?????? ?????? ?????? ??????
    private void ChangeEndState(Timestamp now) {
        List<Funding> fundings = fundingRepository.findByDetailStateAndEndDate("?????? ?????????", now);
        for(int i = 0; i < fundings.size(); i++){
            Funding funding = fundings.get(i);
            funding.setState(2);
            if(funding.getTargetPrice() > funding.getNowPrice()){
                funding.setDetailState("?????? ??????");
                ethereumService.setFundingStatusFail(funding.getId().toString());
            }else{
                funding.setDetailState("?????? ??????");
                // ???????????? ?????? ??????
                ethereumService.setFundingStatusProduction(funding.getId().toString());
                Maker maker = funding.getMaker();
                Long nowSum = maker.getFundingSum();
                int fundingSum = funding.getNowPrice();
                maker.setFundingSum(nowSum+fundingSum);
                makerRepository.save(maker);
            }
            fundingRepository.save(funding);
            System.out.println("?????? ?????? ????????? ?????? ??????");

        }
    }

    public FundingResponse.fundingList searchFunding(String keyword, Pageable pageable) {
        List<FundingContent> response = new ArrayList<>();

        List<Funding> fundings = fundingRepository.findByKeywordAndState(keyword, 1, pageable);
        System.out.println(fundings);
        for(int i = 0; i < fundings.size(); i++){
            response.add(this.changeResponseFunding(fundings.get(i)));
       }
        return FundingResponse.fundingList.build(response);
    }

    public FundingResponse.RaisedListAndRemained RaisedList(String fundingId, HttpServletRequest req) throws NotValidateAccessToken {
        // Header??? ???????????? ???????????? ?????? userId ???
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));

        List<UseHistoryResponse> useHistory = ethereumService.getUseHistory(fundingId);
        int totalRemain = ethereumService.checkLeftMoneyInFunding(fundingId,user.get().getPublicKey() );
        return FundingResponse.RaisedListAndRemained.build(useHistory, totalRemain);
    }


//    public void FundingModify(FundingRequest.Create request) throws NotFoundException {
//        Optional<Funding> funding = fundingRepository.findById(UUID.fromString(request.getFundingId()));
//        if(!funding.isPresent()){
//            throw new NotFoundException("?????? ??? ????????? ?????? ??? ????????????.");
//        }
//    }
}
