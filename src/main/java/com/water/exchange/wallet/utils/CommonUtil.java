
package com.water.exchange.wallet.utils;

/**
* @auther: Water
* @time: 4 Mar 2018 17:00:16
* 
*/

public class CommonUtil {

	public static boolean isEmptyString(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}
		else{
			return false;
		}
	}
	
}
