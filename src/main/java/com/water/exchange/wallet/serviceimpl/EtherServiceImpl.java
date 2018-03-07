
package com.water.exchange.wallet.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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
import com.water.exchange.wallet.message.TransferMsg;
import com.water.exchange.wallet.service.EtherService;

/**
* @auther: Water
* @time: 1 Mar 2018 12:08:05
* 
*/

@Service
public class EtherServiceImpl implements EtherService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EtherHelper etherHelper;
	
	@Autowired
	private Environment environment;
	
	
	
	@Override
	public String newAccount(String password) {
		String account = "";
		try {
			account = etherHelper.personal_newAccount(password);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			e.printStackTrace();	
		}
/*		JSONObject json = new JSONObject();
		json.put("account", account);
		String res = json.toJSONString();
		*/
		return account;
	}
	
	@Override
	public String getAccounts() {
		List<String> accountList = Collections.emptyList();
		try {
			accountList = etherHelper.eth_accounts();
		} catch (Throwable e) {			
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		String result = JSON.toJSONString(accountList);
		return result;
	}
	
	@Override
	public String getBalance(String account){
		double balance = 0;
		try {
			balance = etherHelper.eth_getBalance(account);
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
	public String transfer(String req){
		String txId = "";
		String from = WalletConstants.ACCOUNT_MAIN_ADDRESS;
		String lockPassword = WalletConstants.ACCOUNT_COMMON_PASSWORD;	
		
		TransferMsg transferMsg = JSON.parseObject(req,TransferMsg.class);
		TransactionModel transactionModel = new TransactionModel();
		transactionModel.setTo(transferMsg.getTo());	
		transactionModel.setFrom(from);
		transactionModel.setValue(EthUtil.toWeiHexStrFromEther(transferMsg.getValue()));
		
		try {		
			etherHelper.personal_unlockAccount(from, lockPassword);
			txId = etherHelper.eth_sendTransaction(transactionModel);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return txId;
	}
	
	@Override
	public String transferToken(String req){
		String txId = "";
		String from = WalletConstants.ACCOUNT_MAIN_ADDRESS;
		String lockPassword = WalletConstants.ACCOUNT_COMMON_PASSWORD;	
		
		TransferMsg transferMsg = JSON.parseObject(req,TransferMsg.class);
		TransactionModel transactionModel = new TransactionModel();		
		transactionModel.setTo(transferMsg.getContractAddress());		
		transactionModel.setFrom(from);
		String data = EthUtil.createTokenTransferData(transferMsg.getTo(), transferMsg.getValue());		
		transactionModel.setData(data);
		transactionModel.setValue("0x0");
		
		System.out.println("Service, data:" + data); 
		System.out.println("Service, transferToken:" + JSON.toJSONString(transactionModel));
		
		try {
			etherHelper.personal_unlockAccount(from, lockPassword);
			txId = etherHelper.eth_sendTransaction(transactionModel);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return txId;
	}
	
	@Override
	public String getTokenBalance(String req){
		double balance = 0;		
		TransferMsg transferMsg = JSON.parseObject(req,TransferMsg.class);		
		try {
			balance = etherHelper.getTokenBalance(transferMsg.getContractAddress(), transferMsg.getTo());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		json.put("balance", balance);
		String result = json.toJSONString();
		return result;
	}	
	

}
