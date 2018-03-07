
package com.water.exchange.wallet.message;

/**
* @auther: Water
* @time: 4 Mar 2018 17:17:15
* 
*/

public class GatherMsg {
	
	private double balance;
	private String address;
	private boolean result;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
