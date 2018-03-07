
package com.water.exchange.wallet.ether;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.water.exchange.wallet.utils.CommonUtil;
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
		double ether = 0;
		List<String> params = new ArrayList<String>();
		params.add(account);
		params.add(WalletConstants.ETH_BLOCK_LATEST);
		String balance = (String)rpcHttpAction(WalletConstants.ETH_GETBALANCE, params);	
		ether = EthUtil.toEtherFromWeiStr(balance);	
		return ether;
	}
	
	public long eth_blockNumber(){
		List<String> params = new ArrayList<String>();
		String blockNumber = (String)rpcHttpAction(WalletConstants.ETH_BLOCKNUMBER, params);
		long result = EthUtil.getLongByHexString(blockNumber);
		return result;
	}
	
	public long eth_getBlockTransactionCountByNumber(long blockNumber){
		List<String> params = new ArrayList<String>();
		String numberHex = EthUtil.toHexString(blockNumber);
		params.add(numberHex);
		String count = (String)rpcHttpAction(WalletConstants.ETH_GETBLOCKTRANSACTIONCOUNTBYNUMBER, params);
		long result = EthUtil.getLongByHexString(count);
		return result;
	}
	
	public BlockTransaction eth_getTransactionByBlockNumberAndIndex(long blockNumber, long index){
		List<String> params = new ArrayList<String>();
		String blockNumberHex = EthUtil.toHexString(blockNumber);
		String indexHex = EthUtil.toHexString(index);
		params.add(blockNumberHex);
		params.add(indexHex);
		JSONObject jsonObject = (JSONObject)rpcHttpAction(WalletConstants.ETH_GETBLOCKTRANSACTIONBYBLOCKNUMBERANDINDEX, params);
		BlockTransaction blockTransaction = JSON.toJavaObject(jsonObject, BlockTransaction.class);
		return blockTransaction;
	}
	
	public BlockTransaction eth_getTransactionByHash(String hash){
		List<String> params = new ArrayList<String>();
		params.add(hash);
		JSONObject jsonObject = (JSONObject)rpcHttpAction(WalletConstants.ETH_GETTRANSACTIONBYHASH, params);
		BlockTransaction blockTransaction = JSON.toJavaObject(jsonObject, BlockTransaction.class);
		return blockTransaction;
	}
	
	public double getTokenBalance(String contractAddress, String account) throws Throwable{
		TransactionModel transactionModel = new TransactionModel();	
		transactionModel.setTo(contractAddress);	
		String data = EthUtil.createTokenBalanceData(account);
		transactionModel.setData(data);
		transactionModel.setValue("0x0");

		String balanceHexStr = eth_call(transactionModel);
		double balance = EthUtil.toEtherFromWeiStr(balanceHexStr);
		return balance;
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
		System.out.println("JSON.toJSONString(params):" + JSON.toJSONString(params));
		
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
