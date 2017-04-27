package net.arnonuem.tmstub.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.jtendermint.jabci.types.Types.CodeType;
import com.github.jtendermint.jabci.types.Types.ResponseQuery;

@Service
public class QueryService {

	private static final Logger log = LoggerFactory.getLogger( QueryService.class );

	public ResponseQuery noop() {
		log.debug( "ResponseQuery default listener implementation" );
		return ResponseQuery.newBuilder().setCode( CodeType.OK ).build();
	}
}
