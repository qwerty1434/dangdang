package com.dangdang;

import com.dangdang.blockchain.dto.DepositHistoryResponse;
import com.dangdang.blockchain.service.EthereumService;
import kotlin.UInt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.*;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class Web3JTest {
//
//    @Autowired
//    EthereumService ethereumService;
//
//    private final static String ROPSTEN_URL = "https://ropsten.infura.io/v3/bf3b9c7878494afe9a5b37dd08f7a24a";
//    private final static String URL = "http://127.0.0.1:7545";
//
//    private final static String CONTRACT = "0x8aBda8885Fb7A8C968A6F12758A5e488ed8B3839";
//    private final static String FROM = "0x4B6a1380f2502fDBAe88B47824707D10BEa692f5";
//    private final static String PRIVATE_KEY = "3c1dfce1f2bb852c755943ee8f392a33bac51c734a0dfe1dd4705fe69ab9f156";
//    private final static String DANGDANG_CONTRACT = "0xD0b9805a43D1a5229a6CF45c5e0Bf4888d0608d6";
//
//    @Test
//    public void getBalance() throws IOException {
//        Web3j web3j = Web3j.build(new HttpService(URL));
//        EthGetBalance balanceWei = web3j.ethGetBalance(FROM, DefaultBlockParameterName.LATEST).send();
//        System.out.println("balance in wei: " + balanceWei.getBalance());
//    }
//
//    @Test
//    public void getPot() throws IOException, ExecutionException, InterruptedException {
//        Function function = new Function("getPot",
//                Collections.emptyList(),
//                Arrays.asList(new TypeReference<Uint256>() {
//                }));
//
//        ethereumService.ethCall(FROM, CONTRACT, function);
//    }
//
//    @Test
//    public void setPot() throws IOException, ExecutionException, InterruptedException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException {
//        int changeNum = 11;
//
//        Function function = new Function("setPot",
//                Arrays.asList(new Uint256(changeNum)),
//                Collections.emptyList());
//
//        String txHash = ethereumService.ethSendRawTransaction(function, PRIVATE_KEY, CONTRACT);
//        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
//        System.out.println("receipt = " + receipt);
//    }
//
//    @Test
//    public void seeEventInLottery() throws IOException, ExecutionException, InterruptedException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException {
//        Web3j web3j = Web3j.build(new HttpService(URL));
//
//        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, CONTRACT);
//        Event event = new Event("showHistory", Arrays.<TypeReference<?>>asList(
//                new TypeReference<Utf8String>(true) {
//                },
//                new TypeReference<Uint>(false) {
//                },
//                new TypeReference<Bool>(false) {
//                }));
////        filter.addOptionalTopics("0x822Cf73Ee577CE4c1dc17CB3438a9B8667238e22", null); // filter: event parameters (gameId: no filter, player1: no filter, player2: filter)
////        filter.addSingleTopic(EventEncoder.encode(event)); // filter: event type (topic[0])
//
////        filter.addOptionalTopics( null, new Uint(new BigInteger("123")).toString());
//        String topic = EventEncoder.encode(event);
//        filter.addSingleTopic(topic);
//        filter.addOptionalTopics(Hash.sha3String("www"));
//
////        String val =  new Address(256, "0x5aD822B5788c21814e963c62408dC082e3d63C71").toString();
//        //0x0000000000000000000000005ad822b5788c21814e963c62408dc082e3d63c71
////        filter.addOptionalTopics(val , null);
////        filter.addOptionalTopics(null, "0x0000000000000000000000000000000000000000000000000000000000000141");
//
////        System.out.println("val" + val);
////        System.out.println("val2" + val2);
////        System.out.println("address " + new Address(256, "0x5aD822B5788c21814e963c62408dC082e3d63C71").toString());
////        Utf8String string = new Utf8String("qqq");
////        System.out.println("String " + string);
////        String encoded = TypeEncoder.encode(string);
////        System.out.println("encoded " + encoded);
////        String hash = Hash.sha3(encoded);
////        System.out.println("hash " + hash);
//
////        String s = Hash.sha3String("qqq");
////        System.out.println(s);
//
//
//        System.out.println("before");
//        web3j.ethLogFlowable(filter).subscribe(log -> {
//            System.out.println("Event received");
//            System.out.println(log);
//            //-------------------------------------------------
////            Address arg1 = (Address) FunctionReturnDecoder.decodeIndexedValue(log.getTopics().get(1), new TypeReference<Address>() {
////            });
//            Uint arg2 = (Uint) FunctionReturnDecoder.decodeIndexedValue(log.getTopics().get(1), new TypeReference<Uint>() {
//            });
//
//
//            List<Type> nonIndexedValues = FunctionReturnDecoder.decode(log.getData(), event.getNonIndexedParameters());
////            long timestamp = Long.parseLong(arg2.getValue().toString());
////            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
////            Date date = new Date();
////            date.setTime(timestamp);
////
////            String Datetime = sdf.format(date);
//
////            System.out.println("address : " + arg1.getValue());
//            System.out.println("pot : " + arg2.getValue());
////            System.out.println(Datetime);
//            System.out.println(nonIndexedValues.get(0).getValue().toString());
//            System.out.println(nonIndexedValues.get(1).getValue().toString());
//
//            System.out.println();
//
//        }, error -> {
//            System.out.println("Error: " + error);
//        });
//    }
//
//    @Test
//    public void setPot2() throws
//            IOException, ExecutionException, InterruptedException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException {
////        int changeNum = 101;
//        Web3j web3j = Web3j.build(new HttpService(URL));
////
////        Function function = new Function("setPot",
////                Arrays.asList(new Uint256(changeNum)),
////                Collections.emptyList());
////
////        String txHash = ethereumService.ethSendRawTransaction(function, PRIVATE_KEY, FROM, CONTRACT);
////        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
////        System.out.println("receipt = " + receipt);
////        String s = receipt.getLogs().get(0).getTopics().get(0);
////        System.out.println("s = " + s);
//
//        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, CONTRACT);
//        Event MY_EVENT = new Event("depositHistory", Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {
//        }, new TypeReference<Uint>(true) {
//        }, new TypeReference<Uint>(false) {
//        }, new TypeReference<Bool>(false) {
//        }));
//
//        web3j.ethLogFlowable(filter).subscribe(log -> {
//            System.out.println("Event received");
//            System.out.println(log);
//            Address arg1 = (Address) FunctionReturnDecoder.decodeIndexedValue(log.getTopics().get(1), new TypeReference<Address>() {
//            });
//            Uint arg2 = (Uint) FunctionReturnDecoder.decodeIndexedValue(log.getTopics().get(2), new TypeReference<Uint>() {
//            });
//            Uint data = (Uint) FunctionReturnDecoder.decodeIndexedValue(log.getData(), new TypeReference<Uint>() {
//            });
//
//            List<Type> nonIndexedValues = FunctionReturnDecoder.decode(log.getData(), MY_EVENT.getNonIndexedParameters());
//            long timestamp = Long.parseLong(arg2.getValue().toString());
//            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//            Date date = new Date();
//            date.setTime(timestamp);
//
//            String Datetime = sdf.format(date);
//
//            System.out.println(arg1.getValue());
//            System.out.println(Datetime);
//            System.out.println(data.getValue());
//            System.out.println("data " + log.getData());
//            System.out.println(nonIndexedValues.get(0).getValue().toString());
//            System.out.println(nonIndexedValues.get(1).getValue().toString());
//
//            System.out.println();
//
//        }, error -> {
//            System.out.println("Error: " + error);
//        });
//
////// Event definition
////        Event MY_EVENT = new Event("showHistory", Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint>(false) {}));
////
////// Event definition hash
////        String MY_EVENT_HASH = EventEncoder.encode(MY_EVENT);
////
////// Filter
//////        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, CONTRACT);
////
////// Pull all the events for this contract
//////        web3j.ethLogFlowable(filter).subscribe(log -> {
//////            String eventHash = log.getTopics().get(0); // Index 0 is the event definition hash
//////
//////            if(eventHash.equals(MY_EVENT_HASH)) { // Only MyEvent. You can also use filter.addSingleTopic(MY_EVENT_HASH)
//////                // address indexed _arg1
//////                Address arg1 = (Address) FunctionReturnDecoder.decodeIndexedValue(log.getTopics().get(0), new TypeReference<Address>() {});
//////                // bytes32 indexed _arg2// uint8 _arg3
//////                Uint arg2 = (Uint) FunctionReturnDecoder.decodeIndexedValue(log.getTopics().get(1), new TypeReference<Uint>() {});
//////                System.out.println(arg1.getValue() + " " + arg2.getValue());
//////            }
//////
//////        });
////
//
//    }
//
//    @Test
//    public void getDepositHistory() throws
//            IOException, ExecutionException, InterruptedException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException {
//        Collection<DepositHistoryResponse> depositHistory = ethereumService.getDepositHistory("123");
//        for (DepositHistoryResponse d : depositHistory) {
//            System.out.println(d);
//        }
//    }
//
//
//    @Test
//    public void createAccount() throws
//            InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException {
//        String password = "secr3t";
//        ECKeyPair keyPair = Keys.createEcKeyPair();
//        WalletFile wallet = Wallet.createStandard("wcwe", keyPair);
//        WalletFile wallet2 = Wallet.createStandard("SDF", keyPair);
//        //password?
//        System.out.println("Private key: " + keyPair.getPrivateKey().toString(16));
//        System.out.println("Account: " + wallet.getAddress());
//        System.out.println("Account: " + wallet2.getAddress());
//
//    }
//
//    @Test
//    public void testDecodeIndexedStringValue() {
//        Utf8String string = new Utf8String("some text");
//        String encoded = TypeEncoder.encode(string);
//        String hash = Hash.sha3(encoded);
//
//        assertEquals(
//                FunctionReturnDecoder.decodeIndexedValue(hash, new TypeReference<Utf8String>() {
//                }),
//                (new Bytes32(Numeric.hexStringToByteArray(hash))));
//    }
//
//
//    @Test
//    public void getAddressFromPrivateKey() {
//        Credentials cs = Credentials.create(PRIVATE_KEY);
//
//        String publicKey = cs.getEcKeyPair().getPublicKey().toString(16);
//        String address = cs.getAddress();
//    }
//
//    @Test
//    public void reateFilterAndRead() throws IOException {
//        Web3j web3j = Web3j.build(new HttpService(URL));
//
//        EthFilter ethFilter = new EthFilter(DefaultBlockParameterName.EARLIEST,
//                DefaultBlockParameterName.LATEST,
//                DANGDANG_CONTRACT);
//
////        ethFilter.addSingleTopic(EventEncoder.encode());
//        EthLog ethLog = web3j.ethGetLogs(ethFilter).send();
//        System.out.println(ethLog);
//        for (EthLog.LogResult e : ethLog.getLogs()) {
//            System.out.println(e.toString());
//        }
//    }
//
//    @Test
//    public void sendEther() {
//        System.out.println("Connecting to Ethereum ...");
//        Web3j web3 = Web3j.build(new HttpService(ROPSTEN_URL));
//        System.out.println("Successfuly connected to Ethereum");
//        try {
//            String privetKey = "ed21a9034322bc810c282209ad36730e9c5139aa36ad1346ca3d3f3faaab850c"; // Add a private key here
//            String recipientAddress = "0x0A50Ca4c71bf88fD4f08907bf7aD30244eFfB118";
//
//            String amountToBeSent = "1";
//
//            // Decrypt private key into Credential object
//            Credentials credentials = Credentials.create(privetKey);
//            System.out.println("Account address: " + credentials.getAddress());
//            System.out.println("Balance: "
//                    + Convert.fromWei(web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
//                    .send().getBalance().toString(), Convert.Unit.ETHER));
//
//            // Get the latest nonce of current account
//            EthGetTransactionCount ethGetTransactionCount = web3
//                    .ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
//            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
//
//            // Recipient address
//            // Value to transfer (in wei)
//            System.out.println("Enter Amount to be sent:");
//            BigInteger value = Convert.toWei(amountToBeSent, Convert.Unit.ETHER).toBigInteger();
//
//            // Gas Parameter
//            BigInteger gasLimit = BigInteger.valueOf(21000);
//            BigInteger gasPrice = Convert.toWei("1", Convert.Unit.GWEI).toBigInteger();
//
//            // Prepare the rawTransaction
//            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit,
//                    recipientAddress, value);
//
//            // Sign the transaction
//            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
//            String hexValue = Numeric.toHexString(signedMessage);
//
//            // Send transaction
//            EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();
//            String transactionHash = ethSendTransaction.getTransactionHash();
//            System.out.println("transactionHash: " + transactionHash);
//
//            // Wait for transaction to be mined
//            Optional<TransactionReceipt> transactionReceipt = null;
//            do {
//                System.out.println("checking if transaction " + transactionHash + " is mined....");
//                EthGetTransactionReceipt ethGetTransactionReceiptResp = web3.ethGetTransactionReceipt(transactionHash)
//                        .send();
//                transactionReceipt = ethGetTransactionReceiptResp.getTransactionReceipt();
//                Thread.sleep(3000); // Wait for 3 sec
//            } while (!transactionReceipt.isPresent());
//
//            System.out.println("Transaction " + transactionHash + " was mined in block # "
//                    + transactionReceipt.get().getBlockNumber());
//            System.out.println("Balance: "
//                    + Convert.fromWei(web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
//                    .send().getBalance().toString(), Convert.Unit.ETHER));
//
//        } catch (IOException | InterruptedException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
}