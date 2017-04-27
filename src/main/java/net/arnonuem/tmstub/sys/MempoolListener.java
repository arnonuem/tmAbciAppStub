package net.arnonuem.tmstub.sys;

import com.github.jtendermint.jabci.api.ICheckTx;

/**
 * Used by QUERY/INFO connection.
 * 
 * CheckTx requests run concurrently with block processing; so they should run against a copy of the main application state which 
 * is reset after every block. This copy is necessary to track transitions made by a sequence of CheckTx requests before they are 
 * included in a block. When a block is committed, the application must ensure to reset the mempool state to the latest committed 
 * state. Tendermint Core will then filter through all transactions in the mempool, removing any that were included in the block, 
 * and re-run the rest using CheckTx against the post-Commit mempool state.
 * 
 * https://tendermint.com/docs/guides/app-development
 */
public interface MempoolListener extends ICheckTx {
	
}
