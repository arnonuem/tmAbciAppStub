package net.arnonuem.tmstub.consensus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.jtendermint.jabci.types.Types.CodeType;
import com.github.jtendermint.jabci.types.Types.ResponseCommit;

@Service
public class CommitService {

	private static final Logger log = LoggerFactory.getLogger( CommitService.class );
	
	public ResponseCommit noop() {
		log.trace( "ResponseCommit default listener implementation" );
		return ResponseCommit.newBuilder().setCode( CodeType.OK ).build();
	}
}
