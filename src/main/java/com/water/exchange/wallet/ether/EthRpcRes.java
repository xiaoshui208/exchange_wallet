
package com.water.exchange.wallet.ether;

/**
* @auther: Water
* @time: 22 Jan 2018 14:40:07
* 
*/

public class EthRpcRes {

	//{"jsonrpc":"2.0","id":67,"error":{"code":-32601,"message":"The method eth_isSyncing does not exist/is not available"}}
	private Integer id=1;
	private String jsonrpc = "2.0";
	private Object result;
	private Object error;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJsonrpc() {
		return jsonrpc;
	}
	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Object getError() {
		return error;
	}
	public void setError(Object error) {
		this.error = error;
	}
}
