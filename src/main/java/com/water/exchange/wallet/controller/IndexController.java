
package com.water.exchange.wallet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @auther: Water
* @time: 28 Feb 2018 14:52:25
* 
*/

@RestController
public class IndexController {

	@RequestMapping("/")
	public String index(){
		return "exchange wallet index !";
	}
	
}
