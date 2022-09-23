package com.dangdang.order.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.blockchain.service.EthereumService;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.repository.FundingRepository;
import com.dangdang.member.domain.User;
import com.dangdang.member.repository.UserRepository;
import com.dangdang.order.domain.OrderHistory;
import com.dangdang.order.domain.OrderReward;
import com.dangdang.order.dto.*;
import com.dangdang.order.repository.OrderHistoryRepository;
import com.dangdang.order.repository.OrderRewardRepository;
import com.dangdang.reward.domain.Reward;
import com.dangdang.reward.dto.RewardRequest;
import com.dangdang.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-local.properties"),
        @PropertySource("classpath:application-personal.properties")
})
public class OrderService {

    private final FundingRepository fundingRepository;
    private final RewardRepository rewardRepository;
    private final UserRepository userRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderRewardRepository orderRewardRepository;

    private final EthereumService ethereumService;


    @Transactional(rollbackFor = {NotFoundException.class})
    public OrderResponse.Regist RegistOrder(OrderRequest.Create request) throws NotFoundException {
        // 주문내역 저장
        Optional<Funding> funding = fundingRepository.findById(request.getFundingId());
        if(!funding.isPresent()){
            throw new NotFoundException("존재하지 않는 펀딩입니다.");
        }
        // 토큰에서 찾은 userId로 user 찾도록 코드 수정 필요함.
        User user = userRepository.findByEmail("ssafy@ssafy.com");
        OrderHistory orderHistory = OrderHistory.OrderHistoryCreate(user, funding.get(), request.getAddress(), request.getPhoneNumber());
        UUID orderHistoryId = orderHistoryRepository.save(orderHistory).getId();
        Optional<OrderHistory> savedOrderHistory = orderHistoryRepository.findById(orderHistoryId);

        int totalPrice = 0;

        // 주문내역 리워드 저장

        List<RewardRequest> rewards = request.getRewards();
        for(int i = 0; i < rewards.size(); i++){
            Optional<Reward> reward = rewardRepository.findById(rewards.get(i).getRewardId());
            if(!reward.isPresent()){
                throw new NotFoundException("해당 리워드가 존재하지 않습니다.");
            }
            int itemTotalPrice = rewards.get(i).getCount() * reward.get().getPrice();
            OrderReward orderReward = OrderReward.OrderRewardCreate(reward.get(), savedOrderHistory.get(), rewards.get(i).getCount(), itemTotalPrice);
            orderRewardRepository.save(orderReward);
            System.out.println(orderReward);
            totalPrice += itemTotalPrice;

        }

        savedOrderHistory.get().setTotalPrice(totalPrice);
        orderHistoryRepository.save(savedOrderHistory.get());
        System.out.println(savedOrderHistory.get());

        // 현재 펀딩 모금액에 주문내역 금액 추가하기
        int fundingNowPrice = funding.get().getNowPrice();
        fundingNowPrice += totalPrice;
        funding.get().setNowPrice(fundingNowPrice);
        fundingRepository.save(funding.get());

        // 펀딩 구매 시 입금하는 블록체인 코드
        ethereumService.sendMoneyToFunding(String.valueOf(request.getFundingId()), user.getNickname(), user.getPublicKey(), request.isAnonymous(), Integer.toString(totalPrice));

        return OrderResponse.Regist.build(true);




    }

    // 유저가 마이페이지에서 펀딩 별 본인이 주문한 주문내역을 확인하는 기능
    public OrderResponse.UserOrderList FindMyPageOrderList(String fundingId) throws NotFoundException {
        // userId는 토큰에서 가져오도록 수정해야 함.
        List<OrderHistory> orderHistory = orderHistoryRepository.findByUserIdAndFundingIdOrderByOrderDate(UUID.fromString("430b929f-bc2a-49fa-b358-2f876dae6ad8"), UUID.fromString(fundingId));
        List<UserOrder> response = new ArrayList<>();
        int orderTotalPrice = 0;
        for(int i = 0; i < orderHistory.size(); i++){
            OrderHistory nowOrderHistory = orderHistory.get(i);
            List<OrderReward> orderRewards = orderRewardRepository.findByOrderHistoryId(nowOrderHistory.getId());
            List<OrderRewardResponse> orderRewardResponseList = new ArrayList<>();
            for(int j = 0; j < orderRewards.size(); j++){
                OrderReward nowOrderReward = orderRewards.get(j);
                Reward nowReward = nowOrderReward.getReward();
                String rewardTitle = nowReward.getTitle();
                OrderRewardResponse orderRewardResponse = OrderRewardResponse.orderRewardResponseCreate(rewardTitle, nowOrderReward.getCount(), nowOrderReward.getItemTotalPrice(), nowReward.getSequence());
                orderRewardResponseList.add(orderRewardResponse);
            }
            Collections.sort(orderRewardResponseList, new Comparator<OrderRewardResponse>() {
                @Override
                public int compare(OrderRewardResponse o1, OrderRewardResponse o2) {
                    return o1.getSequence() - o2.getSequence();
                }
            });
            OrderHistoryResponse orderHistoryResponse = OrderHistoryResponse.OrderHistoryResponseCreate(nowOrderHistory.getAddress(), nowOrderHistory.getTotalPrice());
            UserOrder userOrder = UserOrder.UserOrderCreate(orderRewardResponseList,orderHistoryResponse);
            response.add(userOrder);
            orderTotalPrice += nowOrderHistory.getTotalPrice();
        }
        return OrderResponse.UserOrderList.build(response, orderTotalPrice);

    }
}

