package com.dangdang;

import com.dangdang.blockchain.dto.DepositHistoryResponse;
import com.dangdang.blockchain.service.EthereumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

@SpringBootTest
public class DangDangFundingTest {
//
//    private final static String PRIVATE_KEY = EthereumService.ADMIN_PRIVATE_KEY;
//
////    테스트넷
////    private final static String PRIVATE_KEY = "ed21a9034322bc810c282209ad36730e9c5139aa36ad1346ca3d3f3faaab850c";
//
//    @Autowired
//    EthereumService ethereumService;
//
//    private final String UUID = "550e8400-e29b-41d4-a716-446655440000";
//
//    @Test
//    public void createFunding() {
//        System.out.println("Create Funding");
//        ethereumService.createFundingInBlockChain(UUID, PRIVATE_KEY);
//    }
//
//    @Test
//    public void setFundingStateToStart() {
//        System.out.println("Set fundingState to start");
//        ethereumService.setFundingStateToFundRaising(UUID);
//    }
//
//    @Test
//    public void buyFunding() {
//        System.out.println("Buy Funding");
//        ethereumService.sendMoneyToFunding(UUID, "test", PRIVATE_KEY, false, "0.02");
//    }
//
//    @Test
//    public void seeDepositHistory() {
//        System.out.println("get Deposit History");
//
//        List<DepositHistoryResponse> depositHistory = ethereumService.getDepositHistory(UUID);
//
//        for (DepositHistoryResponse d : depositHistory) {
//            System.out.println(d);
//        }
//    }
//
//    @Test
//    public void createFundingAndSendMoney() {
//        System.out.println("Create Funding");
//        ethereumService.createFundingInBlockChain(UUID, PRIVATE_KEY);
//
//        System.out.println("Set fundingState to start");
//        ethereumService.setFundingStateToFundRaising(UUID);
//
//        System.out.println("Buy Funding");
//        ethereumService.sendMoneyToFunding(UUID, "닉네임", PRIVATE_KEY, false,"1");
//
//        System.out.println("get Deposit History");
//
//        List<DepositHistoryResponse> depositHistory = ethereumService.getDepositHistory(UUID);
//
//        for (DepositHistoryResponse d : depositHistory) {
//            System.out.println(d);
//        }
//    }

}
