
package com.water.exchange.wallet.ether;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;


/**
* @auther: Water
* @time: 1 Mar 2018 14:22:40
* 
*/

public class EtherWeb3jHepler implements EtherHelper{
	
	private Web3j web3j;
	
	public EtherWeb3jHepler(String url){
		this.web3j = Web3j.build(new HttpService(url));
	}
	
	@Override
	public String personal_newAccount(String password) throws Throwable {
		
		return null;
	}
	
	public String eth_gasPrice() throws IOException{
		EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
		BigInteger gasPrice = ethGasPrice.getGasPrice();
		return gasPrice.toString();
	//	return gasPrice.doubleValue()/Math.pow(10, 18);
	}
	
	public List<String> eth_accounts() throws IOException{
		EthAccounts ethAccounts = web3j.ethAccounts().send();
		return ethAccounts.getAccounts();
	}
	
	public String eth_coinbase() throws IOException{
		EthCoinbase ethCoinbase = web3j.ethCoinbase().send();
		return ethCoinbase.getAddress();
	}
	
	public double eth_getBalance(String account) throws IOException{
		EthGetBalance ethGetBalance = web3j.ethGetBalance(account, DefaultBlockParameter.valueOf(WalletConstants.ETH_BLOCK_LATEST)).send();
		BigInteger balance = ethGetBalance.getBalance();
		double result = balance.doubleValue()/Math.pow(10, 18);	
		return result;
	}
	
	@Override
	public String eth_call(TransactionModel transactionModel) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String eth_sendTransaction(TransactionModel transactionModel) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean personal_lockAccount(String account) throws Throwable {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean personal_unlockAccount(String account, String password) throws Throwable {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String sendTokenTransaction(TransactionModel tokenTransactionModel) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long eth_blockNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long eth_getBlockTransactionCountByNumber(long blockNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BlockTransaction eth_getTransactionByBlockNumberAndIndex(long blockNumber, long index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlockTransaction eth_getTransactionByHash(String hash) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTokenBalance(String contractAddress, String account) throws Throwable {
		// TODO Auto-generated method stub
		return 0;
	}





}
