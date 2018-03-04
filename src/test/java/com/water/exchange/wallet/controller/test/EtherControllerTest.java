
package com.water.exchange.wallet.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.water.exchange.wallet.SpringBootApp;
import com.water.exchange.wallet.controller.EtherController;
import com.water.exchange.wallet.ether.WalletConstants;
import com.water.exchange.wallet.message.TransferMsg;

/**
* @auther: Water
* @time: 4 Mar 2018 14:16:16
* 
*/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class EtherControllerTest {

	@Autowired
	private EtherController etherController;
	
	@Test
	public void newAccount(){
		String result = etherController.newAccount();
		System.out.println("getAccountsTest:" + result);
	}
	
	@Test
	public void getAccountsTest(){
		 String result = etherController.getAccounts();
		 System.out.println("getAccountsTest:" + result);
	}
	
	@Test
	public void transfer(){	
		TransferMsg transferMsg = new TransferMsg();
		transferMsg.setTo("0x867b8bc99328cB8Bbd781Be90d92Cb9E81aC0EC7");
		transferMsg.setValue(0.0013);		
		String req = JSON.toJSONString(transferMsg);
		System.out.println("transfer req:" + req);
		
		String result = etherController.transfer(req);
		System.out.println("transfer Test:" + result);
	}
	
	@Test
	public void getBalanceTest(){
		String result = etherController.getBalance(WalletConstants.ACCOUNT_COMMON_ADDRESS);
		System.out.println("getBalanceTest:" + result);
	}
	
	@Test
	public void transferToken(){	
		TransferMsg transferMsg = new TransferMsg();
		transferMsg.setContractAddress(WalletConstants.CONTRACT_ADDRESS_TEST);
		transferMsg.setTo("0x03Df91c545AfB6c5b0C54EdC476217F4eaB67C9D");
		transferMsg.setValue(6);		
		String req = JSON.toJSONString(transferMsg);
		
		System.out.println("transferToken req:" + req);
		String result = etherController.transferToken(req);
		System.out.println("transferTokenTest:" + result);
	}
	
	@Test
	public void getTokenBalance(){
		TransferMsg transferMsg = new TransferMsg();
		transferMsg.setContractAddress(WalletConstants.CONTRACT_ADDRESS_TEST);
		transferMsg.setTo("0x251a02b66543ee93fe2f4214a302e2609da07659");
		String req = JSON.toJSONString(transferMsg);
		
		System.out.println("getTokenBalance req:" + req);
		String result = etherController.getTokenBalance(req);
		System.out.println("getTokenBalance:" + result);
	}
	
}
