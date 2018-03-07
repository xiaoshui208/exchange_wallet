
package com.water.exchange.wallet.message;

/**
* @auther: Water
* @time: 5 Mar 2018 18:27:13
* 
*/

public class TokenTransaction {
	
	private String to;
	private String txHash;
	private long value;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getTxHash() {
		return txHash;
	}
	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	
}
