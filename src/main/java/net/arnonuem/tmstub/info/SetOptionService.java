package net.arnonuem.tmstub.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.jtendermint.jabci.types.Types.ResponseSetOption;

@Service
public class SetOptionService {

	private static final Logger log = LoggerFactory.getLogger( SetOptionService.class );

	public ResponseSetOption noop() {
		log.debug( "ResponseSetOption default listener implementation" );
		return ResponseSetOption.newBuilder().build();
	}

}
