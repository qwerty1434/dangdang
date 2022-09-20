package com.dangdang.blockchain.service;

import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.*;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

@Service
public class EthereumService {

    private final String URL = "https://ropsten.infura.io/v3/bf3b9c7878494afe9a5b37dd08f7a24a";
    private final Admin ADMIN = Admin.build(new HttpService(URL));

    public String createAccount() {
        try {
            String password = "secr3t";
            ECKeyPair keyPair = Keys.createEcKeyPair();
//            WalletFile wallet = Wallet.createStandard(password, keyPair);
//            System.out.println("Private key: " + keyPair.getPrivateKey().toString(16));
//            System.out.println("Account: " + wallet.getAddress());
            return keyPair.getPrivateKey().toString();

        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public Object ethCall(String from, String contract, Function function) throws IOException {
        Transaction transaction = Transaction.createEthCallTransaction(from, contract,
                FunctionEncoder.encode(function));

        EthCall ethCall = ADMIN.ethCall(transaction, DefaultBlockParameterName.LATEST).send();

        //4. 결과값 decode
        List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                function.getOutputParameters());

        System.out.println("ethCall.getResult() = " + ethCall.getResult());
        System.out.println("getValue = " + decode.get(0).getValue());
        System.out.println("getType = " + decode.get(0).getTypeAsString());

        return decode.get(0).getValue();
    }

    public String ethSendRawTransaction(Function function, String privateKey, String from, String contract) throws IOException {
        //nonce 조회
        EthGetTransactionCount ethGetTransactionCount = ADMIN.ethGetTransactionCount(
                from, DefaultBlockParameterName.PENDING).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        System.out.println(nonce);

        BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
        BigInteger GAS_LIMIT = BigInteger.valueOf(772197L);

        // 트랜잭션 생성
        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, GAS_PRICE,
                GAS_LIMIT, contract, FunctionEncoder.encode(function));

        // 트랜잭션 서명
        Credentials credentials = Credentials.create(privateKey);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        // 트랜잭션 전송
        EthSendTransaction ethSendTransaction = ADMIN.ethSendRawTransaction(hexValue).send();

        if (ethSendTransaction.hasError()) {
            System.out.println("Transcation error : " + ethSendTransaction.getError().getMessage());
        }

        return ethSendTransaction.getTransactionHash();
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
}
