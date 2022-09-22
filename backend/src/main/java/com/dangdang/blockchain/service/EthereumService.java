package com.dangdang.blockchain.service;

import com.dangdang.blockchain.dto.DepositHistoryResponse;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
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
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EthereumService {

    private final static String URL = "https://ropsten.infura.io/v3/bf3b9c7878494afe9a5b37dd08f7a24a";
//    private final static String URL = "http://127.0.0.1:7545";

    public final static Admin ADMIN = Admin.build(new HttpService(URL));
    public final static String DANG_DANG_CONTRACT = "0x7BaD88fe93a914d51110d72278941a9977c5FcCc";
    public final static String ADMIN_PRIVATE_KEY = "ed21a9034322bc810c282209ad36730e9c5139aa36ad1346ca3d3f3faaab850c";
    public final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    public final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);

    //    테스트넷
    //    private final static String PRIVATE_KEY = "ed21a9034322bc810c282209ad36730e9c5139aa36ad1346ca3d3f3faaab850c";

    public String createAccount() {
        try {
            ECKeyPair keyPair = Keys.createEcKeyPair();
            return keyPair.getPrivateKey().toString();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

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

    public void sendMoneyToFunding(String fundingId, String nickname, String buyerPrivateKey, boolean isAnonymous, String ether) {
        Function function = new Function("sendMoneyToFunding",
                Arrays.asList(new Utf8String(fundingId), new Utf8String(nickname), new Bool(isAnonymous)),
                Collections.emptyList());
        try {
            ethSendRawTransactionWithEther(function, buyerPrivateKey, DANG_DANG_CONTRACT, ether);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
            String money = nonIndexedValues.get(1).getValue().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            date.setTime(Long.parseLong(nonIndexedValues.get(2).getValue().toString()));
            String nickname = nonIndexedValues.get(3).getValue().toString();
            String isAnonymous = nonIndexedValues.get(4).getValue().toString();
            ret.add(new DepositHistoryResponse(address, money, new Timestamp(date.getTime()), nickname, Boolean.parseBoolean(isAnonymous)));
        });
        return ret;
    }

    public Object ethCall(String from, String contract, Function function) throws IOException {
        Transaction transaction = Transaction.createEthCallTransaction(from, contract,
                FunctionEncoder.encode(function));

        EthCall ethCall = ADMIN.ethCall(transaction, DefaultBlockParameterName.LATEST).send();

        List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                function.getOutputParameters());

        System.out.println("ethCall.getResult() = " + ethCall.getResult());
        System.out.println("getValue = " + decode.get(0).getValue());
        System.out.println("getType = " + decode.get(0).getTypeAsString());

        return decode.get(0).getValue();
    }

    public String ethSendRawTransaction(Function function, String privateKey, String contract) throws IOException {
        EthGetTransactionCount ethGetTransactionCount = ADMIN.ethGetTransactionCount(
                getAddressFromPrivateKey(privateKey), DefaultBlockParameterName.PENDING).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
        BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);

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

    public void ethSendRawTransactionWithEther(Function function, String privateKey, String contract, String ether) throws IOException {
        EthGetTransactionCount ethGetTransactionCount = ADMIN.ethGetTransactionCount(
                getAddressFromPrivateKey(privateKey), DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        System.out.println(nonce);
        BigInteger value = Convert.toWei(ether, Convert.Unit.ETHER).toBigInteger();
        System.out.println(value);
        //----
        String encodedFunction = FunctionEncoder.encode(function);
        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, GAS_PRICE,
                GAS_LIMIT, contract, value, FunctionEncoder.encode(function));
        Credentials credentials = Credentials.create(privateKey);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = ADMIN.ethSendRawTransaction(hexValue).send();

//        Transaction transaction = Transaction.createFunctionCallTransaction(
//                getAddressFromPrivateKey(privateKey), nonce, GAS_PRICE, GAS_LIMIT, contract, value, encodedFunction);
//        EthSendTransaction transactionResponse = ADMIN.ethSendTransaction(transaction).send();
    }

    public TransactionReceipt getReceipt(String transactionHash) throws IOException {

        EthGetTransactionReceipt transactionReceipt = ADMIN.ethGetTransactionReceipt(transactionHash).send();

        if (transactionReceipt.getTransactionReceipt().isPresent()) {
            System.out.println("transactionReceipt.getResult().getContractAddress() = " +
                    transactionReceipt.getResult());
        } else {
            System.out.println("transaction complete not yet");
        }

        return transactionReceipt.getResult();
    }

    public String getAddressFromPrivateKey(String privateKey) {
        Credentials cs = Credentials.create(privateKey);
        return cs.getAddress();
    }

    public String getPublicKeyFromPrivateKey(String privateKey) {
        Credentials cs = Credentials.create(privateKey);
        return cs.getEcKeyPair().getPublicKey().toString(16);
    }
}
