
package com.water.exchange.wallet.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.water.exchange.wallet.ether.BlockTransaction;
import com.water.exchange.wallet.ether.EthUtil;
import com.water.exchange.wallet.ether.EtherHelper;
import com.water.exchange.wallet.ether.EtherHttpHelper;
import com.water.exchange.wallet.ether.TransactionModel;
import com.water.exchange.wallet.ether.WalletConstants;


/**
* @auther: Water
* @time: 25 Jan 2018 17:27:16
* 
*/


public class EthHttpHelperTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static EtherHttpHelper etherHelper;
	
	@BeforeClass
	public static void before(){
		etherHelper = new EtherHttpHelper("http://119.23.108.120:8545");
	//	 ethHelper = new EthHttpHelper("120.79.62.129", "8545");
	}
	
	@Test
	public void eth_gasPrice_test(){
		String eth_gasPrice;
		try {
			eth_gasPrice = etherHelper.eth_gasPrice();
			logger.info("eth_gasPrice:" + eth_gasPrice);	
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void eth_getBalance_test(){
		try {
			double eth_getBalance = etherHelper.eth_getBalance("0x251a02b66543ee93fe2f4214a302e2609da07659");
			logger.info("eth_getBalance:" + eth_getBalance);	
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void personal_newAccount_test(){	
		try {
			String personal_newAccount = etherHelper.personal_newAccount(WalletConstants.ACCOUNT_COMMON_PASSWORD);
			logger.info("personal_newAccount:" + personal_newAccount);	
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void eth_accounts_test(){	
		try {
			List<String> eth_accounts = etherHelper.eth_accounts();
			logger.info("eth_accounts:" + eth_accounts);	
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void eth_blockNumber(){
		long blockNumber = etherHelper.eth_blockNumber();
		logger.info("eth_blockNumber : " + blockNumber);
	}
	
	@Test
	public void eth_getBlockTransactionCountByNumber(){		
		long blockNumber = etherHelper.eth_blockNumber();
		long count = etherHelper.eth_getBlockTransactionCountByNumber(blockNumber);
		logger.info("count:" + count);
	}
	
	@Test
	public void eth_getTransactionByBlockNumberAndIndex(){
		long blockNumber = etherHelper.eth_blockNumber();
		long count = etherHelper.eth_getBlockTransactionCountByNumber(blockNumber);
		for(int i=0; i<count; i++){
			BlockTransaction result = etherHelper.eth_getTransactionByBlockNumberAndIndex(blockNumber, i);
			logger.info("result:" + JSON.toJSONString(result));
		}
	}
		
	@Test
	public void personal_lockAccount_test(){
		try {
			boolean personal_lockAccount = etherHelper.personal_lockAccount("0x251a02b66543ee93fe2f4214a302e2609da07659");
			logger.info("personal_lockAccount:" + personal_lockAccount);	
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void personal_unlockAccount_test(){
		try {
		    boolean personal_unlockAccount = etherHelper.personal_unlockAccount("0x251a02b66543ee93fe2f4214a302e2609da07659",WalletConstants.ACCOUNT_COMMON_PASSWORD);
		    logger.info("personal_unlockAccount:" + personal_unlockAccount);	
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void eth_sendTransaction_test(){
		try {		
		//	String eth_gasPrice = ethHelper.eth_gasPrice();
/*			boolean personal_unlockAccount = etherHelper.personal_unlockAccount("0x251a02b66543ee93fe2f4214a302e2609da07659",WalletConstants.ACCOUNT_COMMON_PASSWORD);
			System.out.println("personal_unlockAccount:" + personal_unlockAccount);		*/	
			TransactionModel transactionModel = new TransactionModel();
			transactionModel.setFrom("0x251a02b66543ee93fe2f4214a302e2609da07659");
			transactionModel.setTo("0x867b8bc99328cB8Bbd781Be90d92Cb9E81aC0EC7");			
			double transferEther = 0.001;
			transactionModel.setValue(EthUtil.toWeiHexStrFromEther(transferEther));
/*			double gasEther = 0.0001;
			String gas = CommonUtil.toGas(eth_gasPrice, gasEther);
			transactionModel.setGas(gas);
			transactionModel.setGasPrice(eth_gasPrice);*/
			String eth_sendTransaction = (String) etherHelper.eth_sendTransaction(transactionModel);
			logger.info("eth_sendTransaction:" + eth_sendTransaction);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void eth_sendTransaction_contract_test(){
		try {
			boolean personal_unlockAccount = etherHelper.personal_unlockAccount("0x251a02b66543ee93fe2f4214a302e2609da07659","123456");
			System.out.println("personal_unlockAccount:" + personal_unlockAccount);			
			TransactionModel transactionModel = new TransactionModel();
			transactionModel.setFrom("0xb4030736db86567a34675f581f0a46098bfd905a");
			transactionModel.setTo("0xe39431266a0c594517cf8d05be319bc2d48043d6");
			transactionModel.setContractAddress(WalletConstants.CONTRACT_ADDRESS_TEST);
/*			double transferEther = 2;
			transactionModel.setValue(CommonUtil.toWeiHexStrFromEther(transferEther));
			double gasEther = 0.001;
			String gas = CommonUtil.toGas(eth_gasPrice, gasEther);
			transactionModel.setGas(gas);
			transactionModel.setGasPrice(eth_gasPrice);*/
			
			double transferToken = 10000;
			System.out.println("CommonUtil.toWeiFromEther(transferToken):" + EthUtil.multiply(transferToken,18));
			String partValueStr = 	EthUtil.multiply(transferToken,18).toString(16);
			transactionModel.setValue(partValueStr);
			
			String eth_sendTransaction = (String) etherHelper.sendTokenTransaction(transactionModel);
			System.out.println("eth_sendTransaction:" + eth_sendTransaction);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
/*	@Test
	public void eth_call_test(){
		try {
			TransactionModel transactionModel = new TransactionModel();
			transactionModel.setTo("0x9a642d6b3368ddc662CA244bAdf32cDA716005BC");
			String eth_call = (String)ethHelper.eth_call(transactionModel);					
			System.out.println("eth_call:" + eth_call);			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void eth_estimateGas_test(){
		try {		
			TransactionModel transactionModel = new TransactionModel();
			transactionModel.setTo("0x9a642d6b3368ddc662CA244bAdf32cDA716005BC");
			String eth_estimateGas = (String)ethHelper.eth_estimateGas(transactionModel);
			System.out.println("eth_estimateGas:" + eth_estimateGas);		
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}*/
	
}
