
package com.water.exchange.wallet.ether;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.water.exchange.wallet.utils.HttpUtil;



/**
* @auther: Water
* @time: 1 Mar 2018 14:11:13
* 
*/

public class EtherHttpHelper implements EtherHelper{
	
	private String url = "http://119.23.108.120:" + WalletConstants.ETH_PORT_DEFAULT;

	public EtherHttpHelper(String url){
		this.url = url;
	}
		
	public String personal_newAccount(String password){
		List<String> params = new ArrayList<String>();
		params.add(password);
		String result = (String)rpcHttpAction(WalletConstants.PERSONAL_NEWACCOUNT, params);
		return result;
	}
	
	public String eth_gasPrice(){
		List<String> params = new ArrayList<String>();
		String gasPrice = (String)rpcHttpAction(WalletConstants.ETH_GASPRICE, params);
		return gasPrice;
	}
	
	public List<String> eth_accounts(){
		List<String> params = new ArrayList<String>();
		Object result = rpcHttpAction(WalletConstants.ETH_ACCOUNTS, params);
		if(result!=null && result instanceof JSONArray){
			JSONArray resultList = (JSONArray)result;
			List<String> strList = resultList.toJavaList(String.class);
			return strList;
		}
		return new ArrayList<String>();
	}
	
	public String eth_coinbase(){
		List<String> params = new ArrayList<String>();
		String result = (String)rpcHttpAction(WalletConstants.ETH_ACCOUNTS, params);
		return result;
	}
	
	public double eth_getBalance(String account) {
		List<String> params = new ArrayList<String>();
		params.add(account);
		params.add(WalletConstants.ETH_BLOCK_LATEST);
		String balance = (String)rpcHttpAction(WalletConstants.ETH_GETBALANCE, params);	
		double ether = EthUtil.toEtherFromWeiStr(balance);	
		return ether;
	}
	
	public String eth_call(TransactionModel transactionModel) throws Throwable{		
		List<Object> params = new ArrayList<Object>();		
		params.add(transactionModel);
		params.add(WalletConstants.ETH_BLOCK_LATEST);
		String result = (String)rpcHttpAction(WalletConstants.ETH_CALL, params);
		return result;
	}
	
	public boolean personal_lockAccount(String account){
		List<String> params = new ArrayList<String>();
		params.add(account);
		Boolean result = (Boolean)rpcHttpAction(WalletConstants.PERSONAL_LOCKACCOUNT, params);
		return result;
	}
	
	public boolean personal_unlockAccount(String account, String password){
		List<String> params = new ArrayList<String>();
		params.add(account);
		params.add(password);
		Boolean result = (Boolean)rpcHttpAction(WalletConstants.PERSONAL_UNLOCKACCOUNT, params);
		return result;
	}
	
	public String eth_sendTransaction(TransactionModel transactionModel) throws Throwable{		
		List<TransactionModel> params = new ArrayList<TransactionModel>();		
		params.add(transactionModel);
		String result = (String)rpcHttpAction(WalletConstants.ETH_SENDTRANSACTION, params);
		return result;
	}
	
	public String sendTokenTransaction(TransactionModel tokenTransactionModel) throws Throwable{
		TransactionModel transactionModel = new TransactionModel();
		transactionModel.setFrom(transactionModel.getFrom());
		transactionModel.setTo(tokenTransactionModel.getContractAddress());
		transactionModel.setValue("0");
		
/*		StringBuffer dataBuffer = new StringBuffer("0x");
		dataBuffer.append(WalletConstants.CONTRACT_METHOD_TRANSFER_CODE);
		dataBuffer.append(WalletConstants.CONTRACT_DATA_ADDRESS_PRE);
		dataBuffer.append(transactionModel.getTo());
		double tokenValue = tokenTransactionModel.getValue();
		String partValueStr = 	EthUtil.toWeiFromEther(tokenValue).toString(16);
		String partValueStr = tokenTransactionModel.getValue();
		int dataValueLeng = WalletConstants.CONTRACT_METHOD_DATA_LENGTH;
		for(int i=0; i<dataValueLeng-partValueStr.length(); i++){
			dataBuffer.append("0");
		}
		dataBuffer.append(tokenTransactionModel.getValue());
		String data = dataBuffer.toString();*/
		
		String data = EthUtil.createTokenTransferData(transactionModel.getTo(), 6);
		
		transactionModel.setData(data);
		
		System.out.println("JSON.toJSONString(transactionModel):" + JSON.toJSONString(transactionModel));		
		String eth_sendTransaction = (String) eth_sendTransaction(transactionModel);
		System.out.println("eth_sendTransaction:" + eth_sendTransaction);
		
		return eth_sendTransaction;
	}
	
	public Object rpcHttpAction(String method, List params){
		EthRpcReq ethRpcReq = new EthRpcReq();
		ethRpcReq.setMethod(method);
		ethRpcReq.setParams(params);
		ethRpcReq.setId(1);
		ethRpcReq.setJsonrpc(WalletConstants.JSONRPC_DEFAULT);
		String postData = JSON.toJSONString(ethRpcReq);
		String ethResult = HttpUtil.post(url, postData);
		System.out.println("ethResult:" + ethResult);
		EthRpcRes ethRpcRes = JSON.parseObject(ethResult, EthRpcRes.class);
		return ethRpcRes.getResult();	
	}

}
