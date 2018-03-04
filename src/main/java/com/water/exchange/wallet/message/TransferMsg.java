
package com.water.exchange.wallet.message;

import com.water.exchange.wallet.ether.CoinType;

/**
* @auther: Water
* @time: 4 Mar 2018 13:05:42
* 
*/

public class TransferMsg {

	private String contractAddress;
	private String from;
	private String to;
	private double value;
	private CoinType coinType;
	
	public String getContractAddress() {
		return contractAddress;
	}
	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public CoinType getCoinType() {
		return coinType;
	}
	public void setCoinType(CoinType coinType) {
		this.coinType = coinType;
	}
	
}
