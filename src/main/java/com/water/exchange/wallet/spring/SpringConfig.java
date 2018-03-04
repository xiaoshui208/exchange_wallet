
package com.water.exchange.wallet.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.water.exchange.wallet.ether.EtherHelper;
import com.water.exchange.wallet.ether.EtherHttpHelper;


/**
* @auther: Water
* @time: 1 Mar 2018 12:11:33
* 
*/

@Configuration
public class SpringConfig {

	@Autowired
	private Environment environment;
	
	@Bean
	public EtherHelper etherHepler(){
		EtherHelper etherHepler = new EtherHttpHelper(environment.getProperty("wallet.eth.url"));
		return etherHepler;
	}
	
}
