package net.arnonuem.tmstub.consensus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.jtendermint.jabci.types.Types.ResponseInitChain;

@Service
public class InitChainService {

	private static final Logger log = LoggerFactory.getLogger( InitChainService.class );
	
	public ResponseInitChain noop() {
		log.debug( "ResponseInitChain default listener implementation" );
		return ResponseInitChain.newBuilder().build();
	}
	
}
