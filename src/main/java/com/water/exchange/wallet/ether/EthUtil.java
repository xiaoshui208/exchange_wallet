
package com.water.exchange.wallet.ether;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
* @auther: Water
* @time: 23 Jan 2018 14:36:24
* 
*/

public class EthUtil {
	
	public static String createTokenTransferData(String to, double tokenValue){
		StringBuffer dataBuffer = new StringBuffer("0x");
		dataBuffer.append(WalletConstants.CONTRACT_METHOD_TRANSFER_CODE);
		dataBuffer.append(WalletConstants.CONTRACT_DATA_ADDRESS_PRE);
		dataBuffer.append(to.substring(2));
		String partValueStr = EthUtil.multiply(tokenValue, WalletConstants.BALANCE_DECIMAL_DEFAULT).toString(16);
		int dataValueLeng = WalletConstants.CONTRACT_METHOD_DATA_LENGTH;
		for(int i=0; i<dataValueLeng-partValueStr.length(); i++){
			dataBuffer.append("0");
		}
		dataBuffer.append(partValueStr);
		String data = dataBuffer.toString();
		return data;	
	}
	
	public static String createTokenBalanceData(String to){
		StringBuffer dataBuffer = new StringBuffer("0x");
		dataBuffer.append(WalletConstants.CONTRACT_METHOD_BALANCEOF_CODE);
		dataBuffer.append(WalletConstants.CONTRACT_DATA_ADDRESS_PRE);
		dataBuffer.append(to.substring(2));
		String data = dataBuffer.toString();
		return data;	
	}

	public static String toWeiHexStrFromEther(double ether){
		BigInteger weiInt = multiply(ether, WalletConstants.BALANCE_DECIMAL_DEFAULT);
		System.out.println("toWeiHexStrFromEther : " + "0x"+weiInt.toString(16));
		return "0x"+weiInt.toString(16);
	}
		
	public static int toGasPriceInt(String eth_gasPrice){
		BigInteger gasPriceInt = parseHexString(eth_gasPrice);
		int gasPrice = gasPriceInt.intValue();
		return gasPrice;
	}
	
	public static String toGas(String eth_gasPrice, double gasEther){		
		int gasPrice = EthUtil.toGasPriceInt(eth_gasPrice);
		return toGas(gasPrice,gasEther);
	}
	
/*	public static double toGasWeiFee(String eth_gasPrice, int gas){
		
	}*/
	
	public static String toGas(int gasPrice, double gasEther){
		BigDecimal gasDec = new BigDecimal((gasEther*Math.pow(10, 18)/gasPrice));
	    BigInteger gasInt = gasDec.toBigInteger();
	    String gas = "0x"+gasInt.toString(16);
		return gas;
	}
	
	public static BigInteger multiply(double ether, int decimal){
		BigDecimal weiDec = new BigDecimal(ether*Math.pow(10,decimal));
		BigInteger weiInt = weiDec.toBigInteger();
		return weiInt;
	}
	
	public static double toEtherFromWeiStr(String hexval){
		BigInteger intVal = parseHexString(hexval);
		double result = intVal.doubleValue()/Math.pow(10, 18);	
	/*	Long.parseLong(val,16);*/
		System.out.println("parseAmount:" + hexval);
		System.out.println("parseAmount:" + intVal);
		System.out.println("parseAmount:" + result);	
		return result;
	}
	
    public static String toHexString(double doubleVal){
    	BigDecimal bigDecVal = new BigDecimal(doubleVal);
    	BigInteger bigIntVal = bigDecVal.toBigInteger();
    	return toHexString(bigIntVal);
    }
    
    public static String toHexString(long val){
    	return "0x"+Long.toHexString(val);
    }
    
    public static String toHexString(int val){
    	return "0x"+Integer.toHexString(val);
    }
	
    public static String toHexString(BigInteger bigIntVal){
    	return "0x"+bigIntVal.toString(16);
    }
	
    public static BigInteger parseHexString(String hexval){
    	if(hexval==null||"".equals(hexval)||hexval.length()<=2){
			throw new NumberFormatException("Not hex");
		}
    	String val = hexval.substring(2);
    	BigInteger intVal = new BigInteger(val,16);
    	return intVal;
    }
    
    public static int getIntByHexString(String hexval){
    	if(hexval==null||"".equals(hexval)||hexval.length()<=2){
			throw new NumberFormatException("Not hex");
		}
    	String val = hexval.substring(2);
    	int result = Integer.parseInt(val, 16);
    	return result;
    }
    
    public static long getLongByHexString(String hexval){
    	if(hexval==null||"".equals(hexval)||hexval.length()<=2){
			throw new NumberFormatException("Not hex");
		}
    	String val = hexval.substring(2);
    	long result = Long.parseLong(val, 16);
    	return result;
    }
    
	public static double getEtherWeiRatio(){
		return Math.pow(10, 18);
	}
	
	public static void main(String[] args){
		BigInteger intVal = new BigInteger("500");
		System.out.println(intVal);
		System.out.println(intVal.doubleValue());
		System.out.println(Math.pow(3, 2));
		double ether = 100;
	//	long ether = 10000000000;
		System.out.println(ether*Math.pow(10,18));
		System.out.println(toWeiHexStrFromEther(10));
	}
}
