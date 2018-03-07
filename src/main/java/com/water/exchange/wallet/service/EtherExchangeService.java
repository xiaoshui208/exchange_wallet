
package com.water.exchange.wallet.service;

/**
* @auther: Water
* @time: 6 Mar 2018 09:34:52
* 
*/

public interface EtherExchangeService {
	
	public String checkAllBalance(String req);
	public String checkDeposit(String req);
	public String confirmDeposit(String req);
	public String gatherEther();
	
}
