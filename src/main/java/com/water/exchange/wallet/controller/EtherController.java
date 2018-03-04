
package com.water.exchange.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.water.exchange.wallet.ether.WalletConstants;
import com.water.exchange.wallet.service.EthereumService;

/**
* @auther: Water
* @time: 28 Feb 2018 14:59:22
* 
*/

@RestController
public class EtherController {
	
	@Autowired
	private EthereumService ethereumService;

	@RequestMapping("/wallet/newaccount/eth")
	public String newAccount(){
//	public String newAccount(@RequestBody String password){
		return ethereumService.newAccount(WalletConstants.ACCOUNT_COMMON_PASSWORD);
	}
	
	@RequestMapping("/wallet/accounts/eth")
	public String getAccounts(){
		return ethereumService.getAccounts();
	}
	
	@RequestMapping("/wallet/transfer/eth")
	public String transfer(@RequestBody String req){		
		String res = ethereumService.transfer(req);
		return res;
	}
	
	@RequestMapping("/wallet/balance/eth")
	public String getBalance(@RequestBody String account){
		return ethereumService.getBalance(account);
	}

	@RequestMapping("/wallet/transfer/ethtoken")
	public String transferToken(@RequestBody String req){		
		String res = ethereumService.transferToken(req);
		return res;
	}
	
	@RequestMapping("/wallet/balance/ethtoken")
	public String getTokenBalance(@RequestBody String req){		
		String res = ethereumService.getTokenBalance(req);
		return res;
	}
	
}
