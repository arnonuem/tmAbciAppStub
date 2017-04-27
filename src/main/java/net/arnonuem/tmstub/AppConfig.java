package net.arnonuem.tmstub;

import javax.websocket.CloseReason;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.jtmsp.websocket.Websocket;
import com.github.jtmsp.websocket.WebsocketStatus;

@Configuration
public class AppConfig {

	private static final Logger log = LoggerFactory.getLogger( AppConfig.class );
	
	@Bean
	public Websocket websocket() {
		return new Websocket( new WebsocketStatus() {
			@Override
			public void wasOpened() {
				log.info( "WS was opened" );
			}


			@Override
			public void wasClosed( CloseReason cr ) {
				log.info( "WS was closed: " + cr.getReasonPhrase() );
			}


			@Override
			public void hadError( Throwable t ) {
				throw new RuntimeException( t );
			}
		} );
	}

}
