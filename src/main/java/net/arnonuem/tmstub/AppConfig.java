/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2017 - 2018
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.arnonuem.tmstub;

import java.io.File;

import javax.websocket.CloseReason;

import org.cryptacular.bean.EncodingHashBean;
import org.cryptacular.spec.CodecSpec;
import org.cryptacular.spec.DigestSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.messaging.SubscribableChannel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jtmsp.websocket.Websocket;
import com.github.jtmsp.websocket.WebsocketException;
import com.github.jtmsp.websocket.WebsocketStatus;

/**
 * 
 * @author arnonuem
 */
@Configuration
public class AppConfig {

	private static final Logger log = LoggerFactory.getLogger( AppConfig.class );
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public Websocket websocket() {
		return new Websocket( new WebsocketStatus() {
			@Override
			public void wasOpened() {
				log.info( "WS was opened" );
			}


			@Override
			public void wasClosed( CloseReason cr ) {
				if( !cr.getReasonPhrase().isEmpty() ) {
					log.warn( "WS was closed: " + cr.getReasonPhrase() );
					try {
						websocket().reconnectWebsocket();
					} catch( WebsocketException e ) {
						e.printStackTrace();
					}
				} else {
					log.warn( "WS was closed" );
				}
			}


			@Override
			public void hadError( Throwable t ) {
				throw new RuntimeException( t );
			}
		} );
	}
	
	@Bean
	public EncodingHashBean encodingHashBean() {
		EncodingHashBean bean = new EncodingHashBean();
		bean.setDigestSpec( new DigestSpec( "SHA3", 256 ) );
		bean.setCodecSpec( CodecSpec.HEX );
		bean.setIterations( 5 );
		return bean;
	}

	
	@Bean
	SubscribableChannel pubSubChannel() {
		return MessageChannels.publishSubscribe().get();
	}
	
	

	/**
	 * just for testing - has nothing to do with Tendermint and should be removed soon.
	 */
	@Bean
	IntegrationFlow integrationFlow( @Value("${input-dir:file://${user.home}/Desktop/in}") File in ) {
		long delay = 1000L;
		return IntegrationFlows.from( Files.inboundAdapter( in )
				.autoCreateDirectory( true ), poller -> poller.poller( spec -> spec.fixedRate( delay ) ) )
				.transform( File.class, File::getAbsolutePath )
				.channel( pubSubChannel() )
				.get();
	}

	
	
}
