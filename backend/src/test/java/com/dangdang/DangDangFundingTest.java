package com.dangdang;

import com.dangdang.blockchain.dto.DepositHistoryResponse;
import com.dangdang.blockchain.dto.UseHistoryResponse;
import com.dangdang.blockchain.service.EthereumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Credentials;

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
//    private final String UUID = "550e8400-e29b-41d4-a716-446655440003";
//    private final String tempAddress1 = "0x37e27e5F784CF0A7a7ffe722980bcdc8D5d188b1";
//    private final String tempAddress2 = "";
//
//    //[5]
//    @Test
//    public void createFunding() {
//        System.out.println("Create Funding");
//        ethereumService.createFundingInBlockChain(UUID, PRIVATE_KEY);
//    }
//
//    //[1]
//    @Test
//    public void setFundingStateToStart() {
//        System.out.println("Set fundingState to start");
//        ethereumService.setFundingStateToFundRaising(UUID);
//    }
//
//    // [6]
//    @Test
//    public void buyFunding() {
//        System.out.println("Buy Funding");
//        ethereumService.sendMoneyToFunding(UUID, "test3", PRIVATE_KEY, false, 1000);
//    }
//
//    // [101] 모금 히스토리 (서포터 히스토리)
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
//    // [2]
//    @Test
//    public void setFundingStatusProduction() {
//        System.out.println("Set fundingState to production");
//        ethereumService.setFundingStatusProduction(UUID);
//    }
//
//    // [7] 펀딩 금액 사용
//    @Test
//    public void sendMoneyToManufacture() {
//        System.out.println("send Money To Manufacture");
//        ethereumService.sendMoneyToManufacture(UUID, 190, "테스트 목적");
//    }
//
//    // [8] 펀딩 남은 금액 조회
//    @Test
//    public void checkLeftMoneyInFunding() {
//        System.out.println("check Left Money In Funding");
//        System.out.println("left money " + ethereumService.checkLeftMoneyInFunding(UUID, tempAddress1));
//    }
//
//    // [102] 사용 히스토리 (서포터 히스토리)
//    @Test
//     public void getUseHistory() {
//        System.out.println("get Use History");
//
//        List<UseHistoryResponse> useHistory = ethereumService.getUseHistory(UUID);
//
//        for (UseHistoryResponse d : useHistory) {
//            System.out.println(d);
//        }
//    }
//
//    @Test
//    public void getWonBalance() {
//        int wonBalance = ethereumService.getWonBalance(PRIVATE_KEY);
//        System.out.println(wonBalance);
//    }
//
//    @Test
//    public void getWeiBalance() {
//        String privateKey = "f6e96f416bbaebf1ddd007e635e86a86a4804a8b22eaeac959878567f9fa9722";
//        String weiBalance = ethereumService.getWeiBalance(privateKey);
//        System.out.println(weiBalance);
//    }
//
//    @Test
//    public void generatePrivateKey(){
//        System.out.println(ethereumService.createAccount());
//    }
//
//    @Test
//    public void getAddressFromPrivateKey() {
//        String privateKey = "f6e96f416bbaebf1ddd007e635e86a86a4804a8b22eaeac959878567f9fa9722";
//        Credentials cs = Credentials.create(privateKey);
//        System.out.println( cs.getAddress());
//    }
//
//    @Test
//    public void sendMoneyToTargetAddressFromAdmin(){
//        ethereumService.sendMoneyToTargetAddressFromAdmin("f6e96f416bbaebf1ddd007e635e86a86a4804a8b22eaeac959878567f9fa9722", 1000);
//    }
}
