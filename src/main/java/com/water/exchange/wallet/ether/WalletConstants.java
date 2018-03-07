
package com.water.exchange.wallet.ether;

/**
* @auther: Water
* @time: 22 Jan 2018 14:27:04
* 
*/

public class WalletConstants {

	public static final String JSONRPC_DEFAULT = "2.0";
	public static final String JSONRPC_ID_DEFAULT = "2.0";
	
	public static final String ETH_PORT_DEFAULT = "8545";
	
	public static final String ETH_BLOCK_LATEST = "latest";
	public static final String ETH_BLOCK_EARLIEST = "earliest";
	public static final String ETH_BLOCK_PENDING = "pending";
	
	public static final String NET_PEERCOUNT = "net_peerCount";
	public static final String ETH_SYNCING = "eth_syncing";
	public static final String ETH_COINBASE = "eth_coinbase";
	public static final String ETH_MINING = "eth_mining";
	public static final String ETH_ACCOUNTS = "eth_accounts";
	public static final String ETH_BLOCKNUMBER = "eth_blockNumber";	
	public static final String ETH_GETBLOCKTRANSACTIONCOUNTBYNUMBER = "eth_getBlockTransactionCountByNumber";	
	public static final String ETH_GETBLOCKTRANSACTIONBYBLOCKNUMBERANDINDEX = "eth_getTransactionByBlockNumberAndIndex";	
	public static final String ETH_GETTRANSACTIONBYHASH = "eth_getTransactionByHash";
	public static final String ETH_GETBALANCE = "eth_getBalance";		
	public static final String ETH_GASPRICE = "eth_gasPrice";
	public static final String ETH_GETTRANSACTIONCOUNT = "eth_getTransactionCount";
	public static final String ETH_SENDTRANSACTION = "eth_sendTransaction";	
	public static final String ETH_GETBLOCKBYNUMBER = "eth_getBlockByNumber";	
	public static final String ETH_CALL = "eth_call";
	public static final String ETH_ESTIMATEGAS = "eth_estimateGas";
	public static final String PERSONAL_NEWACCOUNT = "personal_newAccount";
	public static final String PERSONAL_LOCKACCOUNT = "personal_lockAccount";	
	public static final String PERSONAL_UNLOCKACCOUNT = "personal_unlockAccount";	
	public static final String NET_LISTENING = "net_listening";
	
	public static final String CONTRACT_METHOD_TRANSFER_CODE = "a9059cbb";
	public static final String CONTRACT_METHOD_BALANCEOF_CODE = "70a08231";
	public static final String CONTRACT_METHOD_NAME_CODE = "06fdde03";
	public static final int CONTRACT_METHOD_DATA_LENGTH = 64;
	public static final String CONTRACT_DATA_ADDRESS_PRE = "000000000000000000000000";
	
	public static final String CONTRACT_ADDRESS_TEST = "0xa790ee58e977fa691e3a55494384aa2de0f6a832";
	
	public static final String ACCOUNT_COMMON_PASSWORD = "cc123456";
	public static final String ACCOUNT_MAIN_ADDRESS = "0x251a02b66543ee93fe2f4214a302e2609da07659";
	
	public static final int BALANCE_DECIMAL_DEFAULT = 18;
	
	
}
