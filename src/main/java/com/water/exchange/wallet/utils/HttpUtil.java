
package com.water.exchange.wallet.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
* @auther: Water
* @time: 22 Jan 2018 14:46:19
* 
*/

public class HttpUtil {
	
	public static void main(String[] args){
/*		String url = "http://127.0.0.1:8082/querybalance";
		getResult(url);*/
		String url = "http://119.23.108.120:8545";
//		String body = "{\"jsonrpc\":\"2.0\",\"method\":\"web3_clientVersion\",\"params\":[],\"id\":67}";
//		String body = "{\"jsonrpc\": \"1.0\", \"id\":\"curltest\", \"method\": \"getreceivedbyaddress\", \"params\": [\"mkE5E7uFfDPoemDyMFgy6UkHsLpyqeWALf\"] }";
//		String body = "{\"jsonrpc\": \"2.0\", \"id\":1, \"method\": \"eth_getBalance\", \"params\": [\"0x8db3f0f1173a045fa009edc65d228064d89d3f12\",\"latest\"] }";  
		String body = "{\"jsonrpc\": \"2.0\", \"id\":1, \"method\": \"eth_protocolVersion\", \"params\": []}";  	
		String result = post(url, body);
		System.out.println("result:" + result);
	}
	
	public static String getResult(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			CloseableHttpResponse httpRes = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpRes.getEntity();
			String result = EntityUtils.toString(httpEntity);
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(httpClient!=null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}
	
	public static String post(String url, String body){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		try {
			StringEntity stringEntity = new StringEntity(body);
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
						
			CloseableHttpResponse httpRes = httpClient.execute(httpPost); 
			HttpEntity httpEntity = httpRes.getEntity();
			String result = EntityUtils.toString(httpEntity);
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		
		return "";
	}

}
