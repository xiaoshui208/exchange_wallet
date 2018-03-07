
package com.water.exchange.wallet.service;

/**
* @auther: Water
* @time: 1 Mar 2018 12:07:29
* 
*/

public interface EtherService {

	public String getAccounts();
	public String newAccount(String password);
	public String getBalance(String account);
	public String transfer(String req);
	public String transferToken(String req);
	public String getTokenBalance(String req);
	
}
