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
import com.dangdang.image.repository.BodyImageRepository;
import com.dangdang.image.repository.FundThumbnailRepository;
import com.dangdang.member.domain.Maker;
import com.dangdang.member.domain.User;
import com.dangdang.member.repository.MakerRepository;
import com.dangdang.member.repository.UserRepository;
import com.dangdang.reward.domain.Reward;
import com.dangdang.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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

    public FundingResponse.fundingList MakerFundingList(int state) {
        List<FundingContent> response = new ArrayList<>();
        // 토큰에서 MakerId 값 가져와서 조회해야 하도록 수정하기
        List<Funding> fundings = fundingRepository.findByMakerIdAndState("430b929f-bc2a-49fa-b358-2f876dae6ad8", state);
        for(int i = 0; i < fundings.size(); i++){
            Funding funding = fundings.get(i);
            if(state == 0 && funding.getDetailState().equals("작성중")){
                continue;
            }
            int achieveRate = (int)(funding.getNowPrice() / (double)funding.getTargetPrice() * 100);
            FundThumbnail thumbnail = fundThumbnailRepository.findByFundingIdAndSequence(funding.getId().toString(), 0);
            String img = thumbnail.getImg();
            LocalDateTime start = funding.getStartDate().toLocalDateTime();
            LocalDateTime end = funding.getEndDate().toLocalDateTime();
            int day = (int)ChronoUnit.DAYS.between(start, end);
            String category = funding.getCategory().getType();
            FundingContent fundingContent = FundingContent.Create(funding, achieveRate, img , day , category);
            response.add(fundingContent);
        }
        return FundingResponse.fundingList.build(response);

    }



//    public void FundingModify(FundingRequest.Create request) throws NotFoundException {
//        Optional<Funding> funding = fundingRepository.findById(UUID.fromString(request.getFundingId()));
//        if(!funding.isPresent()){
//            throw new NotFoundException("작성 중 펀딩을 찾을 수 없습니다.");
//        }
//    }
}
