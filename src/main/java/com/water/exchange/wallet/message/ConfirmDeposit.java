
package com.water.exchange.wallet.message;

/**
* @auther: Water
* @time: 6 Mar 2018 09:27:57
* 
*/

public class ConfirmDeposit {

	private String txHash;
	private boolean confirm;
		
	public String getTxHash() {
		return txHash;
	}
	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}
	public boolean isConfirm() {
		return confirm;
	}
	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	
}
