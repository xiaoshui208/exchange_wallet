
package com.water.exchange.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.water.exchange.wallet.service.EtherExchangeService;

/**
* @auther: Water
* @time: 6 Mar 2018 09:35:56
* 
*/

@RestController
public class EhterExchangeController {
	
	@Autowired
	private EtherExchangeService etherExchangeService;

	@RequestMapping("/wallet/exchange/checkallbalance/ether")
	public String checkAllBalance(@RequestBody String req){
		String res = etherExchangeService.checkAllBalance(req);
		return res;
	}
	
	@RequestMapping("/wallet/exchange/checkdeposit/ether")
	public String checkDeposit(@RequestBody String req){
		String res = etherExchangeService.checkDeposit(req);
		return res;
	}
	
	@RequestMapping("/wallet/exchange/gather/ether")
	public String gatherEther(){
		String res = etherExchangeService.gatherEther();
		return res;
	}
	
	@RequestMapping("/wallet/exchange/confirmdeposit/ether")
	public String confirmDeposit(@RequestBody String req){
		String res = etherExchangeService.confirmDeposit(req);
		return res;
	}

	
	
}
