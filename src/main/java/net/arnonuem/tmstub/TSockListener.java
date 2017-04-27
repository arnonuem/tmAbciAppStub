package net.arnonuem.tmstub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jtendermint.jabci.api.IBeginBlock;
import com.github.jtendermint.jabci.api.ICheckTx;
import com.github.jtendermint.jabci.api.ICommit;
import com.github.jtendermint.jabci.api.IDeliverTx;
import com.github.jtendermint.jabci.api.IEcho;
import com.github.jtendermint.jabci.api.IEndBlock;
import com.github.jtendermint.jabci.api.IFlush;
import com.github.jtendermint.jabci.api.IInfo;
import com.github.jtendermint.jabci.api.IInitChain;
import com.github.jtendermint.jabci.api.IQuery;
import com.github.jtendermint.jabci.api.ISetOption;
import com.github.jtendermint.jabci.types.Types.CodeType;
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

import net.arnonuem.tmstub.info.InfoService;
import net.arnonuem.tmstub.sys.TmCommunicatorService;

//TODO workaround since there is an issue with ABCIAPI
@Component
public class TSockListener /* implements ABCIAPI */ implements IDeliverTx, IBeginBlock, ICheckTx, ICommit, IEndBlock, IFlush, IInfo, IInitChain, IQuery, ISetOption, IEcho {

	private static final Logger log = LoggerFactory.getLogger( TSockListener.class );

	private final TmCommunicatorService svcTmCommunication;
	private final InfoService svcInfo;


	@Autowired
	public TSockListener( TmCommunicatorService svcTmCommunication, InfoService svcInfo ) {
		this.svcTmCommunication = svcTmCommunication;
		this.svcInfo = svcInfo;
	}


	@Override
	public ResponseDeliverTx receivedDeliverTx( RequestDeliverTx req ) {
		log.debug( "ResponseDeliverTx default listener implementation" );
		return ResponseDeliverTx.newBuilder().setCode( CodeType.OK ).build();
	}


	@Override
	public ResponseFlush requestFlush( RequestFlush reqfl ) {
		log.trace( "ResponseFlush default listener implementation" );
		return ResponseFlush.newBuilder().build();
	}


	@Override
	public ResponseCommit requestCommit( RequestCommit requestCommit ) {
		log.trace( "ResponseCommit default listener implementation" );
		return ResponseCommit.newBuilder().setCode( CodeType.OK ).build();
	}


	@Override
	public ResponseBeginBlock requestBeginBlock( RequestBeginBlock req ) {
		log.trace( "ResponseBeginBlock default listener implementation" );
		return ResponseBeginBlock.newBuilder().build();
	}


	@Override
	public ResponseCheckTx requestCheckTx( RequestCheckTx req ) {
		log.debug( "ResponseCheckTx default listener implementation" );
		return ResponseCheckTx.newBuilder().setCode( CodeType.OK ).build();
	}


	@Override
	public ResponseEndBlock requestEndBlock( RequestEndBlock req ) {
		log.trace( "ResponseEndBlock default listener implementation" );
		return ResponseEndBlock.newBuilder().build();
	}


	@Override
	public ResponseInfo requestInfo( RequestInfo req ) {
		svcTmCommunication.init(); // open a websocket to be able to send requests to tendermint
		return svcInfo.noop();
	}


	@Override
	public ResponseInitChain requestInitChain( RequestInitChain req ) {
		log.debug( "ResponseInitChain default listener implementation" );
		return ResponseInitChain.newBuilder().build();
	}


	@Override
	public ResponseQuery requestQuery( RequestQuery req ) {
		log.debug( "ResponseQuery default listener implementation" );
		return ResponseQuery.newBuilder().setCode( CodeType.OK ).build();
	}


	@Override
	public ResponseSetOption requestSetOption( RequestSetOption req ) {
		log.debug( "ResponseSetOption default listener implementation" );
		return ResponseSetOption.newBuilder().build();
	}


	@Override
	public ResponseEcho requestEcho( RequestEcho req ) {
		log.debug( "ResponseEcho default listener implementation" );
		return ResponseEcho.newBuilder().setMessage( "NOECHO" ).build();
	}
}
