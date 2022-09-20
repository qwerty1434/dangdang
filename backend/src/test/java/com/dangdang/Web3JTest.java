package com.dangdang;

import com.dangdang.blockchain.service.EthereumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


@SpringBootTest
public class Web3JTest {

    @Autowired
    EthereumService ethereumService;

    private final static String URL = "https://ropsten.infura.io/v3/bf3b9c7878494afe9a5b37dd08f7a24a";
    private final static String CONTRACT = "0x8D43311510ac3Ea4DE89d465d5dFb6C25fdF3025";
    private final static String FROM = "0x4fFF5eE62De5Ce582b955eF6c00AB898cf7613c0";
    private final static String PRIVATE_KEY = "ed21a9034322bc810c282209ad36730e9c5139aa36ad1346ca3d3f3faaab850c";

    @Test
    public void getBalance() throws IOException {
        Web3j web3j = Web3j.build(new HttpService(URL));
        EthGetBalance balanceWei = web3j.ethGetBalance(FROM, DefaultBlockParameterName.LATEST).send();
        System.out.println("balance in wei: " + balanceWei.getBalance());
    }

    @Test
    public void getPot() throws IOException, ExecutionException, InterruptedException {
        Function function = new Function("getPot",
                Collections.emptyList(),
                Arrays.asList(new TypeReference<Uint256>() {
                }));

        ethereumService.ethCall(FROM, CONTRACT, function);
    }

    @Test
    public void setPot() throws IOException, ExecutionException, InterruptedException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException {
        int changeNum = 11;

        Function function = new Function("setPot",
                Arrays.asList(new Uint256(changeNum)),
                Collections.emptyList());

        String txHash = ethereumService.ethSendRawTransaction(function, PRIVATE_KEY, FROM, CONTRACT);
        TransactionReceipt receipt = ethereumService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }


    @Test
    public void createAccount() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException {
        String password = "secr3t";
        ECKeyPair keyPair = Keys.createEcKeyPair();
        WalletFile wallet = Wallet.createStandard("wcwe", keyPair);
        WalletFile wallet2 = Wallet.createStandard("SDF", keyPair);
        //password?
        System.out.println("Private key: " + keyPair.getPrivateKey().toString(16));
        System.out.println("Account: " + wallet.getAddress());
        System.out.println("Account: " + wallet2.getAddress());

    }

    @Test
    public void getAddressFromPrivateKey(){
        Credentials cs = Credentials.create(PRIVATE_KEY);

        String publicKey = cs.getEcKeyPair().getPublicKey().toString(16);
        String address = cs.getAddress();
    }

    @Test
    public void sendEther() {
        System.out.println("Connecting to Ethereum ...");
        Web3j web3 = Web3j.build(new HttpService(URL));
        System.out.println("Successfuly connected to Ethereum");
        try {
            String privetKey = "ed21a9034322bc810c282209ad36730e9c5139aa36ad1346ca3d3f3faaab850c"; // Add a private key here
            String recipientAddress = "0x0A50Ca4c71bf88fD4f08907bf7aD30244eFfB118";

            String amountToBeSent = "1";

            // Decrypt private key into Credential object
            Credentials credentials = Credentials.create(privetKey);
            System.out.println("Account address: " + credentials.getAddress());
            System.out.println("Balance: "
                    + Convert.fromWei(web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                    .send().getBalance().toString(), Convert.Unit.ETHER));

            // Get the latest nonce of current account
            EthGetTransactionCount ethGetTransactionCount = web3
                    .ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            // Recipient address
            // Value to transfer (in wei)
            System.out.println("Enter Amount to be sent:");
            BigInteger value = Convert.toWei(amountToBeSent, Convert.Unit.ETHER).toBigInteger();

            // Gas Parameter
            BigInteger gasLimit = BigInteger.valueOf(21000);
            BigInteger gasPrice = Convert.toWei("1", Convert.Unit.GWEI).toBigInteger();

            // Prepare the rawTransaction
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit,
                    recipientAddress, value);

            // Sign the transaction
            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);

            // Send transaction
            EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();
            String transactionHash = ethSendTransaction.getTransactionHash();
            System.out.println("transactionHash: " + transactionHash);

            // Wait for transaction to be mined
            Optional<TransactionReceipt> transactionReceipt = null;
            do {
                System.out.println("checking if transaction " + transactionHash + " is mined....");
                EthGetTransactionReceipt ethGetTransactionReceiptResp = web3.ethGetTransactionReceipt(transactionHash)
                        .send();
                transactionReceipt = ethGetTransactionReceiptResp.getTransactionReceipt();
                Thread.sleep(3000); // Wait for 3 sec
            } while (!transactionReceipt.isPresent());

            System.out.println("Transaction " + transactionHash + " was mined in block # "
                    + transactionReceipt.get().getBlockNumber());
            System.out.println("Balance: "
                    + Convert.fromWei(web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                    .send().getBalance().toString(), Convert.Unit.ETHER));

        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}