package com.dangdang.blockchain.service;

import com.dangdang.blockchain.dto.DepositHistoryResponse;
import com.dangdang.blockchain.dto.UseHistoryResponse;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class EthereumService {

    private final static String URL = "https://ropsten.infura.io/v3/bf3b9c7878494afe9a5b37dd08f7a24a";
//    private final static String URL = "http://127.0.0.1:7545";

    public final static Admin ADMIN = Admin.build(new HttpService(URL));
    public final static String DANG_DANG_CONTRACT = "0x4f553775f834F0a39373B5293b1115FC4b458159";
    public final static String ADMIN_PRIVATE_KEY = "ed21a9034322bc810c282209ad36730e9c5139aa36ad1346ca3d3f3faaab850c";
    public final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    public final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);

    //    테스트넷
    //    private final static String PRIVATE_KEY = "ed21a9034322bc810c282209ad36730e9c5139aa36ad1346ca3d3f3faaab850c";

    // [1] 펀딩 진행중 상태로 변경 (모금 가능 상태)
    public void setFundingStateToFundRaising(String fundingId) {
        Function function = new Function("setFundingStatusFundRaising",
                Arrays.asList(new Utf8String(fundingId)),
                Collections.emptyList());
        try {
            String txHash = ethSendRawTransaction(function, ADMIN_PRIVATE_KEY, DANG_DANG_CONTRACT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // [2] 펀딩 상태 변경, 판매자 물품 제작 단계(성공적인 종료 시)
    public void setFundingStatusProduction(String fundingId) {
        Function function = new Function("setFundingStatusProduction",
                Arrays.asList(new Utf8String(fundingId)),
                Collections.emptyList());
        try {
            String txHash = ethSendRawTransaction(function, ADMIN_PRIVATE_KEY, DANG_DANG_CONTRACT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // [3] 펀딩이 성공적으로 완전히 끝났을 때
    public void setFundingStatusDone(String fundingId) {
        Function function = new Function("setFundingStatusDone",
                Arrays.asList(new Utf8String(fundingId)),
                Collections.emptyList());
        try {
            String txHash = ethSendRawTransaction(function, ADMIN_PRIVATE_KEY, DANG_DANG_CONTRACT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // [4] 펀딩 실패 상태로 변경 및 환불
    public void setFundingStatusFail(String fundingId) {
        Function function = new Function("setFundingStatusFail",
                Arrays.asList(new Utf8String(fundingId)),
                Collections.emptyList());
        try {
            String txHash = ethSendRawTransaction(function, ADMIN_PRIVATE_KEY, DANG_DANG_CONTRACT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // [5] 블록체인 내 펀딩 스마트 컨트랙트 생성
    public void createFundingInBlockChain(String fundingId, String makerPrivateKey) {
        Function function = new Function("createFunding",
                Arrays.asList(new Utf8String(fundingId), new Address(getAddressFromPrivateKey(makerPrivateKey))),
                Collections.emptyList());
        try {
            String txHash = ethSendRawTransaction(function, ADMIN_PRIVATE_KEY, DANG_DANG_CONTRACT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // [6] 펀딩 구매 시 입금
    public void sendMoneyToFunding(String fundingId, String nickname, String buyerPrivateKey, boolean isAnonymous, int won) {
        Function function = new Function("sendMoneyToFunding",
                Arrays.asList(new Utf8String(fundingId), new Utf8String(nickname), new Bool(isAnonymous)),
                Collections.emptyList());

        try {
            ethSendRawTransactionWithWei(function, buyerPrivateKey, DANG_DANG_CONTRACT, changeWonToWei(won));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // [7] 펀딩 금액 사용
    public void sendMoneyToManufacture(String fundingId, String factoryAddress, int won, String purpose) {
        BigDecimal amount = Convert.toWei(changeWonToWei(won), Convert.Unit.WEI);

        Function function = new Function("sendMoneyToProductManufacture",
                Arrays.asList(new Utf8String(fundingId), new Address(factoryAddress),
                        new Uint256(new BigInteger(amount.toString())), new Utf8String(purpose)),
                Collections.emptyList());
        try {
            String txHash = ethSendRawTransaction(function, ADMIN_PRIVATE_KEY, DANG_DANG_CONTRACT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // [8] 펀딩 남은 금액 조회 (해당 동작을 호출하는 사람 기준 privateKey)
    public int checkLeftMoneyInFunding(String fundingId, String buyerPrivateKey) {
        Function function = new Function("checkLeftMoneyInFund",
                Arrays.asList(new Utf8String(fundingId)),
                Arrays.asList(new TypeReference<Uint256>() {
                }));
        try {
            String wei = (String) ethCall(function, getAddressFromPrivateKey(buyerPrivateKey), DANG_DANG_CONTRACT);
            return changeWeiToWon(wei);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    // [9] 펀딩 상태 조회
//    public String checkFundingStatus(String fundingId, String privateKey) {
//        Function function = new Function("checkFundingStatus",
//                Arrays.asList(new Utf8String(fundingId)),
//                Collections.emptyList());
//        try {
//            return (String) ethCall(function, getAddressFromPrivateKey(privateKey), DANG_DANG_CONTRACT);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // 프라이빗 키 생성
    public String createAccount() { // private_key 생성
        try {
            ECKeyPair keyPair = Keys.createEcKeyPair();
            return keyPair.getPrivateKey().toString(16);
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    // [101] 모금 히스토리 (서포터 히스토리)
    public List<DepositHistoryResponse> getDepositHistory(String fundingId) {
        ArrayList<DepositHistoryResponse> ret = new ArrayList<>();

        Web3j web3j = Web3j.build(new HttpService(URL));
        org.web3j.protocol.core.methods.request.EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, DANG_DANG_CONTRACT);
        Event event = new Event("DepositHistory", Arrays.<TypeReference<?>>asList(
                new TypeReference<Utf8String>(true) {
                }, new TypeReference<Address>(false) {
                }, new TypeReference<Uint>(false) {
                }, new TypeReference<Uint>(false) {
                }, new TypeReference<Utf8String>(false) {
                }, new TypeReference<Bool>(false) {
                }));
        String topic = EventEncoder.encode(event);
        filter.addSingleTopic(topic);
        filter.addOptionalTopics(Hash.sha3String(fundingId));

        web3j.ethLogFlowable(filter).subscribe(log -> {
            List<Type> nonIndexedValues = FunctionReturnDecoder.decode(log.getData(), event.getNonIndexedParameters());

            String address = nonIndexedValues.get(0).getValue().toString();
            int won = changeWeiToWon(nonIndexedValues.get(1).getValue().toString());
            Date date = changeUnixTimeToDate(nonIndexedValues.get(2).getValue().toString());
            String nickname = nonIndexedValues.get(3).getValue().toString();
            String isAnonymous = nonIndexedValues.get(4).getValue().toString();
            ret.add(new DepositHistoryResponse(address, won, new Timestamp(date.getTime()), nickname, Boolean.parseBoolean(isAnonymous)));
        });
        return ret;
    }

    // [102] 사용 히스토리 (서포터 히스토리)
    public List<UseHistoryResponse> getUseHistory(String fundingId) {
        ArrayList<UseHistoryResponse> ret = new ArrayList<>();

        Web3j web3j = Web3j.build(new HttpService(URL));
        org.web3j.protocol.core.methods.request.EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, DANG_DANG_CONTRACT);
        Event event = new Event("UseHistory", Arrays.<TypeReference<?>>asList(
                new TypeReference<Utf8String>(true) {
                }, new TypeReference<Address>(false) {
                }, new TypeReference<Uint>(false) {
                }, new TypeReference<Uint>(false) {
                }, new TypeReference<Uint>(false) {
                }, new TypeReference<Utf8String>(false) {
                }));
        String topic = EventEncoder.encode(event);
        filter.addSingleTopic(topic);
        filter.addOptionalTopics(Hash.sha3String(fundingId));

        web3j.ethLogFlowable(filter).subscribe(log -> {
            List<Type> nonIndexedValues = FunctionReturnDecoder.decode(log.getData(), event.getNonIndexedParameters());

            String factoryAddress = nonIndexedValues.get(0).getValue().toString();
            int spendMoney = changeWeiToWon(nonIndexedValues.get(1).getValue().toString());
            int leftMoney = changeWeiToWon(nonIndexedValues.get(2).getValue().toString());
            Date date = changeUnixTimeToDate(nonIndexedValues.get(3).getValue().toString());
            String purpose = nonIndexedValues.get(4).getValue().toString();

            ret.add(new UseHistoryResponse(factoryAddress, spendMoney, leftMoney, new Timestamp(date.getTime()), purpose));
        });
        return ret;
    }

    // [201] 유저 지갑 잔액 원으로 조회
    public int getWonBalance(String privateKey) {
        try {
            String address = getAddressFromPrivateKey(privateKey);
            EthGetBalance balanceWei = ADMIN.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
            return changeWeiToWon(balanceWei.getBalance().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // [201] 유저 지갑 잔액 wei로 조회
    public String getWeiBalance(String privateKey) {
        try {
            String address = getAddressFromPrivateKey(privateKey);
            EthGetBalance balanceWei = ADMIN.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
            return balanceWei.getBalance().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String ethCall(Function function, String address, String contract) throws IOException {
        Transaction transaction = Transaction.createEthCallTransaction(address, contract,
                FunctionEncoder.encode(function));
        EthCall ethCall = ADMIN.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
        List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                function.getOutputParameters());
        return decode.get(0).getValue().toString();
    }

    private String ethSendRawTransaction(Function function, String privateKey, String contract) throws IOException {
        EthGetTransactionCount ethGetTransactionCount = ADMIN.ethGetTransactionCount(
                getAddressFromPrivateKey(privateKey), DefaultBlockParameterName.PENDING).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, GAS_PRICE,
                GAS_LIMIT, contract, FunctionEncoder.encode(function));

        Credentials credentials = Credentials.create(privateKey);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = ADMIN.ethSendRawTransaction(hexValue).send();

        if (ethSendTransaction.hasError()) {
            //TODO 에러처리
        }

        return ethSendTransaction.getTransactionHash();
    }

    private void ethSendRawTransactionWithWei(Function function, String privateKey, String contract, String wei) throws IOException {
        EthGetTransactionCount ethGetTransactionCount = ADMIN.ethGetTransactionCount(
                getAddressFromPrivateKey(privateKey), DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        BigInteger value = Convert.toWei(wei, Convert.Unit.WEI).toBigInteger();
        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, GAS_PRICE,
                GAS_LIMIT, contract, value, FunctionEncoder.encode(function));
        Credentials credentials = Credentials.create(privateKey);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        ADMIN.ethSendRawTransaction(hexValue).send();
    }

    private TransactionReceipt getReceipt(String transactionHash) throws IOException {
        EthGetTransactionReceipt transactionReceipt = ADMIN.ethGetTransactionReceipt(transactionHash).send();
        if (transactionReceipt.getTransactionReceipt().isPresent()) {
            System.out.println("transactionReceipt.getResult().getContractAddress() = " +
                    transactionReceipt.getResult());
        } else {
            System.out.println("transaction complete not yet");
        }
        return transactionReceipt.getResult();
    }

    private String getAddressFromPrivateKey(String privateKey) {
        Credentials cs = Credentials.create(privateKey);
        return cs.getAddress();
    }

    private String getPublicKeyFromPrivateKey(String privateKey) {
        Credentials cs = Credentials.create(privateKey);
        return cs.getEcKeyPair().getPublicKey().toString(16);
    }

    private String changeWonToWei(int won) {
        return String.valueOf(won * 100000000000L);
    }

    private int changeWeiToWon(String wei) {
        return Integer.parseInt(String.valueOf(Long.parseLong(wei) / 100000000000L));
    }

    public Date changeUnixTimeToDate(String unixTimeStamp) {
        long unix_seconds = Long.parseLong(unixTimeStamp);
        return new Date(unix_seconds * 1000L);
    }
}
