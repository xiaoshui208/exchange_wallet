
package com.water.exchange.wallet.ether;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;




/**
* @auther: Water
* @time: 1 Mar 2018 14:18:18
* 
*/

public class EtherRpcHelper implements EtherHelper{
	
	private JsonRpcHttpClient jsonRpcHttpClient;
	
	public EtherRpcHelper(String url) throws MalformedURLException{
		this.jsonRpcHttpClient = new JsonRpcHttpClient(new URL(url));
	}
	
	public String personal_newAccount(String password) throws Throwable{
		List<String> params = new ArrayList<String>();
		params.add(password);
		String result=jsonRpcHttpClient.invoke(WalletConstants.PERSONAL_NEWACCOUNT, params, String.class);
		return result;
	}
	
	public String eth_gasPrice() throws Throwable{
		List<String> params = new ArrayList<String>();
		String gasPrice = jsonRpcHttpClient.invoke(WalletConstants.ETH_GASPRICE, params, String.class);
		return gasPrice;
	}
	
	public List<String> eth_accounts() throws Throwable{
		List<String> params = new ArrayList<String>();		
		List result = jsonRpcHttpClient.invoke(WalletConstants.ETH_ACCOUNTS, params,List.class);
		return result;
	}
	
	public String eth_coinbase() throws Throwable{
		List<String> params = new ArrayList<String>();
		String result = jsonRpcHttpClient.invoke(WalletConstants.ETH_COINBASE, params, String.class);
		return result;
	}
	
	public double eth_getBalance(String account) throws Throwable{
		List<String> params = new ArrayList<String>();
		params.add(account);
		params.add(WalletConstants.ETH_BLOCK_LATEST);
		String balance = jsonRpcHttpClient.invoke(WalletConstants.ETH_GETBALANCE, params, String.class);
		double ether =  EthUtil.toEtherFromWeiStr(balance);			
		return ether;
	}
	
	public String eth_call(TransactionModel transactionModel) throws Throwable{		
		List params = new ArrayList();		
		params.add(transactionModel);
		params.add(WalletConstants.ETH_BLOCK_LATEST);
		String result = jsonRpcHttpClient.invoke(WalletConstants.ETH_CALL, params, String.class);
		return result;
	}

	public boolean personal_lockAccount(String account) throws Throwable{
		List<String> params = new ArrayList<String>();
		params.add(account);
		Boolean result = jsonRpcHttpClient.invoke(WalletConstants.PERSONAL_LOCKACCOUNT, params, Boolean.class);
		return result;
	}
	
	public boolean personal_unlockAccount(String account, String password) throws Throwable{
		List<String> params = new ArrayList<String>();
		params.add(account);
		params.add(password);
		Boolean result = jsonRpcHttpClient.invoke(WalletConstants.PERSONAL_UNLOCKACCOUNT, params, Boolean.class);
		return result;
	}
	
	@Override
	public String eth_sendTransaction(TransactionModel transactionModel) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendTokenTransaction(TransactionModel tokenTransactionModel) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
