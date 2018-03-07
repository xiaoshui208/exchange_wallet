
package com.water.exchange.wallet.ether;

import java.util.List;

/**
* @auther: Water
* @time: 1 Mar 2018 14:09:51
* 
*/

public interface  EtherHelper{
	
	public String personal_newAccount(String password) throws Throwable;
	
	public String eth_gasPrice() throws Throwable;
	public List<String> eth_accounts() throws Throwable;
	public String eth_coinbase() throws Throwable;
	public double eth_getBalance(String account) throws Throwable;
	public long eth_blockNumber();
	public long eth_getBlockTransactionCountByNumber(long blockNumber);
	public BlockTransaction eth_getTransactionByBlockNumberAndIndex(long blockNumber, long index);
	public BlockTransaction eth_getTransactionByHash(String hash);
	public double getTokenBalance(String contractAddress, String account) throws Throwable;
	public String eth_call(TransactionModel transactionModel) throws Throwable;
	
	public boolean personal_lockAccount(String account) throws Throwable;
	public boolean personal_unlockAccount(String account, String password) throws Throwable;
	
	public String eth_sendTransaction(TransactionModel transactionModel) throws Throwable;	
	public String sendTokenTransaction(TransactionModel tokenTransactionModel) throws Throwable;
	
}
