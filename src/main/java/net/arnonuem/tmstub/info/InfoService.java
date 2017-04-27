package net.arnonuem.tmstub.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.jtendermint.jabci.types.Types.ResponseInfo;

@Service
public class InfoService {
	
	private static final Logger log = LoggerFactory.getLogger( InfoService.class );
	
	public ResponseInfo noop() {
		log.trace("ResponseInfo default listener implementation");
		return ResponseInfo.newBuilder().setData("NO_INFO").build();
	}
	
}
