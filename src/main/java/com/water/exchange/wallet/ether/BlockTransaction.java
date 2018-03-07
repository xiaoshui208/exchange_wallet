
package com.water.exchange.wallet.ether;

/**
* @auther: Water
* @time: 5 Mar 2018 16:19:11
* 
*/

public class BlockTransaction extends TransactionModel{
	
	private String blockHash;
	private String transactionIndex;
	private String nonce;
	private String input;
	private String blockNumber;
	private String hash;
	
	public String getBlockHash() {
		return blockHash;
	}
	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}
	public String getTransactionIndex() {
		return transactionIndex;
	}
	public void setTransactionIndex(String transactionIndex) {
		this.transactionIndex = transactionIndex;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
}
