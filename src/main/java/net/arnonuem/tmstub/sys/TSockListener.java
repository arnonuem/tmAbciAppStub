/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2017 - 2018
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.arnonuem.tmstub.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jtendermint.jabci.api.IEcho;
import com.github.jtendermint.jabci.api.IFlush;
import com.github.jtendermint.jabci.types.Types.RequestBeginBlock;
import com.github.jtendermint.jabci.types.Types.RequestCheckTx;
import com.github.jtendermint.jabci.types.Types.RequestCommit;
import com.github.jtendermint.jabci.types.Types.RequestDeliverTx;
import com.github.jtendermint.jabci.types.Types.RequestEcho;
import com.github.jtendermint.jabci.types.Types.RequestEndBlock;
import com.github.jtendermint.jabci.types.Types.RequestFlush;
import com.github.jtendermint.jabci.types.Types.RequestInfo;
import com.github.jtendermint.jabci.types.Types.RequestInitChain;
import com.github.jtendermint.jabci.types.Types.RequestQuery;
import com.github.jtendermint.jabci.types.Types.RequestSetOption;
import com.github.jtendermint.jabci.types.Types.ResponseBeginBlock;
import com.github.jtendermint.jabci.types.Types.ResponseCheckTx;
import com.github.jtendermint.jabci.types.Types.ResponseCommit;
import com.github.jtendermint.jabci.types.Types.ResponseDeliverTx;
import com.github.jtendermint.jabci.types.Types.ResponseEcho;
import com.github.jtendermint.jabci.types.Types.ResponseEndBlock;
import com.github.jtendermint.jabci.types.Types.ResponseFlush;
import com.github.jtendermint.jabci.types.Types.ResponseInfo;
import com.github.jtendermint.jabci.types.Types.ResponseInitChain;
import com.github.jtendermint.jabci.types.Types.ResponseQuery;
import com.github.jtendermint.jabci.types.Types.ResponseSetOption;

import net.arnonuem.tmstub.consensus.BeginBlockService;
import net.arnonuem.tmstub.consensus.CommitService;
import net.arnonuem.tmstub.consensus.DeliverTxService;
import net.arnonuem.tmstub.consensus.EndBlockService;
import net.arnonuem.tmstub.consensus.InitChainService;
import net.arnonuem.tmstub.info.InfoService;
import net.arnonuem.tmstub.info.QueryService;
import net.arnonuem.tmstub.info.SetOptionService;
import net.arnonuem.tmstub.mempool.CheckTxService;

/**
 * 
 * @author arnonuem
 */
@Component
public class TSockListener implements InfoQueryListener, MempoolListener, ConsensusListener, IFlush, IEcho {

	private static final Logger log = LoggerFactory.getLogger( TSockListener.class );

	private final TmCommunicatorService svcTmCommunication;
	private final InfoService svcInfo;
	private final QueryService svcQuery;
	private final SetOptionService svcSetOption;
	private final CheckTxService svcCheckTx;
	private final BeginBlockService svcBeginBlock;
	private final CommitService svcCommit;
	private final DeliverTxService svcDeliverTx;
	private final EndBlockService svcEndBlock;
	private final InitChainService svcInitChain;

	@Autowired
	public TSockListener( TmCommunicatorService svcTmCommunication, InfoService svcInfo, QueryService svcQuery, SetOptionService svcSetOption, CheckTxService svcCheckTx, BeginBlockService svcBeginBlock, CommitService svcCommit, DeliverTxService svcDeliverTx, EndBlockService svcEndBlock, InitChainService svcInitChain ) {
		this.svcTmCommunication = svcTmCommunication;
		this.svcInfo = svcInfo;
		this.svcQuery = svcQuery;
		this.svcSetOption = svcSetOption;
		this.svcCheckTx = svcCheckTx;
		this.svcBeginBlock = svcBeginBlock;
		this.svcCommit = svcCommit;
		this.svcDeliverTx = svcDeliverTx;
		this.svcEndBlock = svcEndBlock;
		this.svcInitChain = svcInitChain;
	}
	
	
	@Override
	public ResponseInfo requestInfo( RequestInfo req ) {
		svcTmCommunication.init(); // open a websocket to be able to send requests to tendermint
		return svcInfo.noop();
	}


	@Override
	public ResponseQuery requestQuery( RequestQuery req ) {
		return svcQuery.noop();
	}


	@Override
	public ResponseSetOption requestSetOption( RequestSetOption req ) {
		return svcSetOption.noop();
	}
	
	
	@Override
	public ResponseCheckTx requestCheckTx( RequestCheckTx req ) {
		return svcCheckTx.noop();
	}
	
	
	@Override
	public ResponseInitChain requestInitChain( RequestInitChain req ) {
		return svcInitChain.noop();
	}
	
	
	@Override
	public ResponseBeginBlock requestBeginBlock( RequestBeginBlock req ) {
		return svcBeginBlock.noop();
	}
	
	
	@Override
	public ResponseDeliverTx receivedDeliverTx( RequestDeliverTx req ) {
		return svcDeliverTx.process( req );
	}
	
	
	@Override
	public ResponseEndBlock requestEndBlock( RequestEndBlock req ) {
		return svcEndBlock.noop();
	}
	

	@Override
	public ResponseCommit requestCommit( RequestCommit requestCommit ) {
		return svcCommit.noop();
	}
	
	
	@Override
	public ResponseEcho requestEcho( RequestEcho req ) {
		log.debug( "ResponseEcho default listener implementation" );
		return ResponseEcho.newBuilder().setMessage( "NOECHO" ).build();
	}


	@Override
	public ResponseFlush requestFlush( RequestFlush reqfl ) {
		log.trace( "ResponseFlush default listener implementation" );
		return ResponseFlush.newBuilder().build();
	}
}
