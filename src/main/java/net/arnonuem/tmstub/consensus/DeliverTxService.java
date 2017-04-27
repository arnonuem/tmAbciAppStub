package net.arnonuem.tmstub.consensus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.jtendermint.jabci.types.Types.CodeType;
import com.github.jtendermint.jabci.types.Types.ResponseDeliverTx;

@Service
public class DeliverTxService {

	private static final Logger log = LoggerFactory.getLogger( DeliverTxService.class );
	
	public ResponseDeliverTx noop() {
		log.debug( "ResponseDeliverTx default listener implementation" );
		return ResponseDeliverTx.newBuilder().setCode( CodeType.OK ).build();
	}
	
}
