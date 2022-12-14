package com.dangdang.order.service;

import com.dangdang.advice.exceptions.NotFoundException;
import com.dangdang.blockchain.service.EthereumService;
import com.dangdang.funding.domain.Funding;
import com.dangdang.funding.repository.FundingRepository;
import com.dangdang.member.domain.User;
import com.dangdang.member.exception.InsufficientfundsException;
import com.dangdang.member.exception.NotValidateAccessToken;
import com.dangdang.member.repository.UserRepository;
import com.dangdang.order.domain.OrderHistory;
import com.dangdang.order.domain.OrderReward;
import com.dangdang.order.dto.*;
import com.dangdang.order.repository.OrderHistoryRepository;
import com.dangdang.order.repository.OrderRewardRepository;
import com.dangdang.reward.domain.Reward;
import com.dangdang.reward.dto.RewardRequest;
import com.dangdang.reward.repository.RewardRepository;
import com.dangdang.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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

    private final JWTUtil jwtUtil;


    @Transactional(rollbackFor = {NotFoundException.class, InsufficientfundsException.class})
    public OrderResponse.Regist RegistOrder(OrderRequest.Create request, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken, InsufficientfundsException {
        // ???????????? ??????
        Optional<Funding> funding = fundingRepository.findById(request.getFundingId());
        if(!funding.isPresent()){
            throw new NotFoundException("???????????? ?????? ???????????????.");
        }
        // Header??? ???????????? ???????????? ?????? userId ???
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        Optional<User> user = userRepository.findById(UUID.fromString(userId));

        OrderHistory orderHistory = OrderHistory.OrderHistoryCreate(user.get(), funding.get(), request.getAddress(), request.getPhoneNumber());
        UUID orderHistoryId = orderHistoryRepository.save(orderHistory).getId();
        Optional<OrderHistory> savedOrderHistory = orderHistoryRepository.findById(orderHistoryId);

        int totalPrice = 0;

        // ???????????? ????????? ??????

        List<RewardRequest> rewards = request.getRewards();
        for(int i = 0; i < rewards.size(); i++){
            Optional<Reward> reward = rewardRepository.findById(rewards.get(i).getRewardId());
            if(!reward.isPresent()){
                throw new NotFoundException("?????? ???????????? ???????????? ????????????.");
            }
            int itemTotalPrice = rewards.get(i).getCount() * reward.get().getPrice();
            OrderReward orderReward = OrderReward.OrderRewardCreate(reward.get(), savedOrderHistory.get(), rewards.get(i).getCount(), itemTotalPrice);
            if(orderReward.getCount()==0){
                continue;
            }
            orderRewardRepository.save(orderReward);
            System.out.println(orderReward);
            totalPrice += itemTotalPrice;

        }

        savedOrderHistory.get().setTotalPrice(totalPrice);
        orderHistoryRepository.save(savedOrderHistory.get());
        System.out.println(savedOrderHistory.get());

        // ?????? ?????? ???????????? ???????????? ?????? ????????????
        int fundingNowPrice = funding.get().getNowPrice();
        fundingNowPrice += totalPrice;
        funding.get().setNowPrice(fundingNowPrice);
        fundingRepository.save(funding.get());

        int remainMoney = ethereumService.getWonBalance(user.get().getPublicKey());
        if(remainMoney < totalPrice){
            throw new InsufficientfundsException();
        }



        // ?????? ?????? ??? ???????????? ???????????? ??????
        ethereumService.sendMoneyToFunding(String.valueOf(request.getFundingId()), user.get().getNickname(), user.get().getPublicKey(), request.isAnonymous(), totalPrice);

        return OrderResponse.Regist.build(true);




    }

    // ????????? ????????????????????? ?????? ??? ????????? ????????? ??????????????? ???????????? ??????
    public OrderResponse.UserOrderList FindMyPageOrderList(String fundingId, HttpServletRequest req) throws NotFoundException, NotValidateAccessToken {

        // Header??? ???????????? ???????????? ?????? userId ???
        String userId = jwtUtil.getUserIdByHeaderAccessToken(req);
        List<OrderHistory> orderHistory = orderHistoryRepository.findByUserIdAndFundingIdOrderByOrderDate(UUID.fromString(userId), UUID.fromString(fundingId));
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

