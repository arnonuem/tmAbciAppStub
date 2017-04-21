package net.arnonuem.tmstub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jtendermint.jabci.api.ABCIAPI;
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

public class TSockListener implements ABCIAPI {

	private static final Logger log = LoggerFactory.getLogger( TSockListener.class );

	@Override
	public ResponseDeliverTx receivedDeliverTx(RequestDeliverTx arg0) {
		log.debug( "receivedDeliverTx" );
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBeginBlock requestBeginBlock(RequestBeginBlock arg0) {
		log.debug( "requestBeginBlock" );
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseCheckTx requestCheckTx(RequestCheckTx arg0) {
		log.debug( "requestCheckTx" );
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseCommit requestCommit(RequestCommit arg0) {
		log.debug( "requestCommit" );
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEndBlock requestEndBlock(RequestEndBlock arg0) {
		log.debug( "requestEndBlock" );
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseFlush requestFlush(RequestFlush arg0) {
		log.debug( "requestFlush" );
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseInfo requestInfo(RequestInfo arg0) {
		log.debug( "requestInfo" );
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseInitChain requestInitChain(RequestInitChain arg0) {
		log.debug( "requestInitChain" );
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseQuery requestQuery(RequestQuery arg0) {
		log.debug( "requestQuery" );
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseSetOption requestSetOption(RequestSetOption arg0) {
		log.debug( "requestSetOption" );
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEcho requestEcho(RequestEcho arg0) {
		log.debug( "requestEcho" );
		// TODO Auto-generated method stub
		return null;
	}
	

}
