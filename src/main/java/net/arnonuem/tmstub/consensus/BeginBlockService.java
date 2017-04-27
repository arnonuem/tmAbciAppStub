package net.arnonuem.tmstub.consensus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.jtendermint.jabci.types.Types.ResponseBeginBlock;

@Service
public class BeginBlockService {

	private static final Logger log = LoggerFactory.getLogger( BeginBlockService.class );
	
	public ResponseBeginBlock noop() {
		log.trace( "ResponseBeginBlock default listener implementation" );
		return ResponseBeginBlock.newBuilder().build();
	}
}
