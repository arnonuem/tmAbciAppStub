package net.arnonuem.tmstub.sys;

import com.github.jtendermint.jabci.api.IBeginBlock;
import com.github.jtendermint.jabci.api.ICommit;
import com.github.jtendermint.jabci.api.IDeliverTx;
import com.github.jtendermint.jabci.api.IEndBlock;
import com.github.jtendermint.jabci.api.IInitChain;

/**
 * Used by CONSENSUS connection.
 * 
 * The consensus connection is used only when a new block is committed, and communicates all information from 
 * the block in a series of requests: BeginBlock, [DeliverTx, ...], EndBlock, Commit. 
 * That is, when a block is committed in the consensus, we send a list of DeliverTx requests 
 * (one for each transaction) sandwiched by BeginBlock and EndBlock requests, and followed by a Commit.
 * 
 * https://tendermint.com/docs/guides/app-development
 */
public interface ConsensusListener extends IInitChain, IBeginBlock, IDeliverTx, IEndBlock, ICommit {

}
