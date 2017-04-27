package net.arnonuem.tmstub.consensus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.jtendermint.jabci.types.Types.ResponseEndBlock;

@Service
public class EndBlockService {

	private static final Logger log = LoggerFactory.getLogger( EndBlockService.class );
	
	public ResponseEndBlock noop() {
		log.trace( "ResponseEndBlock default listener implementation" );
		return ResponseEndBlock.newBuilder().build();
	}
	
}
