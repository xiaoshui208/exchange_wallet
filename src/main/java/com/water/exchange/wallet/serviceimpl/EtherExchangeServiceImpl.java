
package com.water.exchange.wallet.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.water.exchange.wallet.ether.BlockTransaction;
import com.water.exchange.wallet.ether.EthUtil;
import com.water.exchange.wallet.ether.EtherHelper;
import com.water.exchange.wallet.ether.TransactionModel;
import com.water.exchange.wallet.ether.WalletConstants;
import com.water.exchange.wallet.message.ConfirmDeposit;
import com.water.exchange.wallet.message.GatherMsg;
import com.water.exchange.wallet.message.TokenDepositMsg;
import com.water.exchange.wallet.message.TokenTransaction;
import com.water.exchange.wallet.service.EtherExchangeService;

/**
* @auther: Water
* @time: 6 Mar 2018 09:34:39
* 
*/

public class EtherExchangeServiceImpl implements EtherExchangeService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EtherHelper etherHelper;
	
	@Autowired
	private Environment environment;
	
	@Override
	public String checkAllBalance(String req){
		double balance = 0;
		String contracAddress = req;
		try {
			List<String> addressList = etherHelper.eth_accounts();
			if(contracAddress==null||"".equals(contracAddress.trim())){
				for(String account: addressList){
					balance += etherHelper.eth_getBalance(account);
				}	
			}
			else{
				for(String account: addressList){
					balance += etherHelper.getTokenBalance(contracAddress,account);
				}	
			}
		} catch (Throwable e) {		
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("balance", balance);
		String result = json.toJSONString();
		return result;
	}
	
	@Override
	public String checkDeposit(String req){	
		String res = "";
		try {
			long lastCheckBlockNumber = Long.parseLong(req);
			List<String> addressList = etherHelper.eth_accounts();
			res = checkDeposit(lastCheckBlockNumber, addressList);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			e.printStackTrace();	
		}	
		return res;
	}
	
	public String checkDeposit(long lastCheckBlockNumber,  List<String> addressList){	
		long blockNumber = lastCheckBlockNumber + 1;
		long currentBlockNumber = etherHelper.eth_blockNumber();
		TokenDepositMsg tokenDepositMsg = new TokenDepositMsg();
		List<TokenTransaction> tokenTransactions = new ArrayList<TokenTransaction>();	
		while(lastCheckBlockNumber<=currentBlockNumber){
			long txCount = etherHelper.eth_getBlockTransactionCountByNumber(blockNumber);
			for(long i=0; i<txCount; i++){
				BlockTransaction blockTransaction = etherHelper.eth_getTransactionByBlockNumberAndIndex(blockNumber, i);
				if(addressList.contains(blockTransaction.getTo())){
					TokenTransaction tokenTransaction = new TokenTransaction();
					String valueHexStr = blockTransaction.getValue();
					long value = EthUtil.getLongByHexString(valueHexStr);
					tokenTransaction.setTo(blockTransaction.getTo());
					tokenTransaction.setValue(value);
					tokenTransaction.setTxHash(blockTransaction.getHash());
					tokenTransactions.add(tokenTransaction);
				}				
				tokenDepositMsg.setTokenTransactions(tokenTransactions);
			}
			blockNumber++;
		}
		tokenDepositMsg.setBlockNumber(blockNumber);
		return JSON.toJSONString(tokenDepositMsg);
	}
	
	public String confirmDeposit(String req){
		List<String> hashList = JSON.parseArray(req, String.class);
		return confirmDeposit(hashList);
	}
	
	public String confirmDeposit(List<String> hashList){
		List<ConfirmDeposit> confirmDeposits = new ArrayList<ConfirmDeposit>();
		for(String hash : hashList){
			ConfirmDeposit confirmDeposit = new ConfirmDeposit();
			BlockTransaction blockTransaction = etherHelper.eth_getTransactionByHash(hash);
			String blockNumberHexStr = blockTransaction.getBlockNumber();
			long blockNumber = EthUtil.getLongByHexString(blockNumberHexStr);
			long currentBlockNumber = etherHelper.eth_blockNumber();
			if(currentBlockNumber>=blockNumber+10){
				confirmDeposit.setTxHash(hash);
				confirmDeposit.setConfirm(true);
				confirmDeposits.add(confirmDeposit);
			}
		}
		return JSON.toJSONString(confirmDeposits);
	}
	
	public String gatherEther(){
		JSONObject json = new JSONObject();	
		try {
			List<String> addressList = etherHelper.eth_accounts();
			for(String address : addressList){
				gatherEthByAddress(address);
			}
			json.put("result", true);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			json.put("result", false);
		}
		return json.toJSONString();
	}
	
	public String gatherEthByAddress(String address){
		String txId = "";
		String lockPassword = WalletConstants.ACCOUNT_COMMON_PASSWORD;
		
		GatherMsg gatherMsg = new GatherMsg();
		gatherMsg.setAddress(address);
		gatherMsg.setResult(true);
		try {
			double balance = etherHelper.eth_getBalance(address);
			gatherMsg.setBalance(balance);
			if(balance>0.001){
				TransactionModel transactionModel = new TransactionModel();
				transactionModel.setTo(environment.getProperty("wallet.eth.mainAddress"));	
				transactionModel.setFrom(address);
				transactionModel.setValue(EthUtil.toWeiHexStrFromEther(balance));
				etherHelper.personal_unlockAccount(address, lockPassword);
				txId = etherHelper.eth_sendTransaction(transactionModel);
				if(txId==null||"".equals(txId.trim())){
					gatherMsg.setResult(false);
				}
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			gatherMsg.setResult(false);
		}
		
		String result = JSON.toJSONString(gatherMsg);	
		return result;
	}
	
}
