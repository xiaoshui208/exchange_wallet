
package com.water.exchange.wallet.message;

import java.util.List;

/**
* @auther: Water
* @time: 5 Mar 2018 18:41:29
* 
*/

public class TokenDepositMsg {

	private long blockNumber;
	private List<TokenTransaction> tokenTransactions;
	
	public long getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(long blockNumber) {
		this.blockNumber = blockNumber;
	}
	public List<TokenTransaction> getTokenTransactions() {
		return tokenTransactions;
	}
	public void setTokenTransactions(List<TokenTransaction> tokenTransactions) {
		this.tokenTransactions = tokenTransactions;
	}
	
}
