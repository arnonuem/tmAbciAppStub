package net.arnonuem.tmstub;

import javax.websocket.CloseReason;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.jtmsp.websocket.Websocket;
import com.github.jtmsp.websocket.WebsocketStatus;

@Configuration
public class AppConfig {

	@Bean
	public Websocket websocket() {
		return new Websocket( new WebsocketStatus() {
			@Override
			public void wasOpened() {
				System.err.println("WS was opened");
			}
			@Override
			public void wasClosed(CloseReason cr) {
				System.err.println("WS was closed: " + cr.getReasonPhrase() );
			}
			@Override
			public void hadError( Throwable t ) {
				throw new RuntimeException( t );
			}
		});
	}
	
}
