package com.dangdang.funding.service;

import com.dangdang.advice.exceptions.NotFoundException;
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
import com.dangdang.member.repository.MakerRepository;
import com.dangdang.member.repository.UserRepository;
import com.dangdang.reward.domain.Reward;
import com.dangdang.reward.dto.RewardResponse;
import com.dangdang.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public FundingResponse.Regist RegistFunding(FundingRequest.Create request) throws NotFoundException {
        // 토큰에서 찾은 userId로 User 찾게 코드 수정 필요함
        User user = userRepository.findByEmail("ssafy@ssafy.com");
        Maker maker = makerRepository.findByUserId(user.getId().toString());
        System.out.println(maker);

        Category category = categoryRepository.findByType(request.getCategory());
        Funding funding = Funding.FundingCreate(request, maker, category, "승인완료");
        System.out.println(funding+" 저장할 펀딩");
        fundingRepository.save(funding);
        this.RegistReward(request.getRewards(), funding);
        this.RegistThumbnail(request.getThumbnails(), funding);
        this.RegistBodyImg(request.getBodyImgs(), funding);
        return FundingResponse.Regist.build(true);
    }

    public FundingResponse.Regist TempStorage(FundingRequest.Create request) throws NotFoundException {
        // 토큰에서 찾은 userId로 User 찾게 코드 수정 필요함
        User user = userRepository.findByEmail("ssafy@ssafy.com");
        Maker maker = makerRepository.findByUserId(user.getId().toString());

        Category category = categoryRepository.findByType(request.getCategory());

        Funding funding = Funding.FundingCreate(request, maker, category, "작성중");
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

    // 메이커 마이페이지에서 펀딩 리스트 조회
    public FundingResponse.fundingList MakerFundingList(int state, Pageable pageable) {
        List<FundingContent> response = new ArrayList<>();
        // 토큰에서 MakerId 값 가져와서 조회해야 하도록 수정하기
        List<Funding> fundings = fundingRepository.findByMakerIdAndState(UUID.fromString("430b929f-bc2a-49fa-b358-2f876dae6ad8"), state, pageable);
        for(int i = 0; i < fundings.size(); i++){
            Funding funding = fundings.get(i);
            response.add(this.changeResponseFunding(funding));
        }
        return FundingResponse.fundingList.build(response);

    }

    // 카테고리 별 펀딩 리스트 조회 기능
    public FundingResponse.fundingList CategoryFundingList(String type, Pageable pageable) throws NotFoundException {

        List<FundingContent> response = new ArrayList<>();

        Category category = categoryRepository.findByType(type);
        if(category == null){
            throw new NotFoundException("존재하지 않는 카테고리 입니다.");
        }

        List<Funding> fundings = fundingRepository.findByCategoryIdAndState(category.getId(),1 ,pageable);
        System.out.println(fundings);
        for(int i = 0; i < fundings.size(); i++){
            response.add(this.changeResponseFunding(fundings.get(i)));
        }

        return FundingResponse.fundingList.build(response);
    }

    // 조회한 펀딩 응답 형태로 변경 (달성률, 메인 썸네일, 남은 펀딩 기간 추가)
    public FundingContent changeResponseFunding(Funding funding){
        if(funding.getDetailState().equals("작성중")){
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
        LocalDateTime start = funding.getStartDate().toLocalDateTime();
        LocalDateTime end = funding.getEndDate().toLocalDateTime();
        int day = (int)ChronoUnit.DAYS.between(start, end);
        String categoryType = funding.getCategory().getType();
        FundingContent fundingContent = FundingContent.Create(funding, achieveRate, img , day , categoryType);
        return fundingContent;
    }


    // 펀딩 리스트 조회 (마감임박, 인기순, 신제품)
    public FundingResponse.fundingList FundingList(String type, Pageable pageable) {
//        List<FundingContent> response = new ArrayList<>();
//        List<Funding> fundings = fundingRepository.findByState(1);
//        for(int i = 0; i < fundings.size(); i++){
//            response.add(this.changeResponseFunding(fundings.get(i)));
//        }
//        // 마감임박
//        if(type.equals("endedAt")){
//            Collections.sort(response, new Comparator<FundingContent>() {
//                @Override
//                public int compare(FundingContent o1, FundingContent o2) {
//                    return o1.getEndDate().compareTo(o2.getEndDate());
//                }
//            });
//            // 인기 상품
//        }else if(type.equals("popular")){
//            Collections.sort(response, new Comparator<FundingContent>() {
//                @Override
//                public int compare(FundingContent o1, FundingContent o2) {
//                    return -(o1.getAchieveRate()-o2.getAchieveRate());
//                }
//            });
//        }else{ // 신규 상품
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
        }
        fundings = fundingRepository.findByState(1, pageable);
        for(int i = 0; i < fundings.size(); i++){
            response.add(this.changeResponseFunding(fundings.get(i)));
        }

        return FundingResponse.fundingList.build(response);

    }

    // 펀딩 상세 정보 조회
    public FundingResponse.DetailFunding DetailFunding(String fundingId) throws NotFoundException {
        Optional<Funding> funding = fundingRepository.findById(UUID.fromString(fundingId));
        if(funding.get() == null){
            throw new NotFoundException("해당 펀딩이 존재하지 않습니다.");
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

          /*
           펀딩 상세페이지에서 확인하는 메이커 정보 중 서포터 수는 블록체인을 통해 가져와야 합니다.
           현재는 서포터 수 0으로 넣고 처리
           성공적으로 종료 된 펀딩이면 서포터랑 거래내역 블록체인을 통해 가져와서 제공해야 합니다.
         */

        Maker maker = funding.get().getMaker();
        MakerResponse.Res makerRes = MakerResponse.Res.build(maker.getId(), maker.getCompanyNumber(), maker.getCompanyName(), maker.getImg(), maker.getFundingSum(), 0);

        return FundingResponse.DetailFunding.build(rewardRes,bodyImgRes,thumbnailsRes,fundingContent, makerRes);

    }


//    public void FundingModify(FundingRequest.Create request) throws NotFoundException {
//        Optional<Funding> funding = fundingRepository.findById(UUID.fromString(request.getFundingId()));
//        if(!funding.isPresent()){
//            throw new NotFoundException("작성 중 펀딩을 찾을 수 없습니다.");
//        }
//    }
}