package net.arnonuem.tmstub.mempool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.jtendermint.jabci.types.Types.CodeType;
import com.github.jtendermint.jabci.types.Types.ResponseCheckTx;

@Service
public class CheckTxService {

	private static final Logger log = LoggerFactory.getLogger( CheckTxService.class );
	
	public ResponseCheckTx noop() {
		log.debug( "ResponseCheckTx default listener implementation" );
		return ResponseCheckTx.newBuilder().setCode( CodeType.OK ).build();
	}
		
}
