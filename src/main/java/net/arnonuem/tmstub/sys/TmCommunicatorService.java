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
package net.arnonuem.tmstub.sys;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jtmsp.websocket.Websocket;
import com.github.jtmsp.websocket.WebsocketException;
import com.github.jtmsp.websocket.jsonrpc.JSONRPC;
import com.github.jtmsp.websocket.jsonrpc.JSONRPCResult;
import com.github.jtmsp.websocket.jsonrpc.Method;
import com.github.jtmsp.websocket.jsonrpc.calls.EmptyParam;
import com.github.jtmsp.websocket.jsonrpc.calls.StringParam;

/**
 * 
 * @author arnonuem
 */
@Service
public class TmCommunicatorService {

	private static final Logger log = LoggerFactory.getLogger( TmCommunicatorService.class );

	private final Websocket socketClient;
	private final ObjectMapper mapper;


	@Autowired
	public TmCommunicatorService( Websocket socketClient, ObjectMapper mapper ) {
		this.socketClient = socketClient;
		this.mapper = mapper;
	}


	public void init() {
		final int time = 5;
		final TimeUnit unit = TimeUnit.SECONDS;

		log.info( "scheduling ({} {}) the opening of a websocket for RPC requests", time, unit );

		Executors.newScheduledThreadPool( 1 ).schedule( () -> {
			if( !socketClient.isOpen() ) {
				log.info( "Connecting websocket" );
				try {
					socketClient.reconnectWebsocket();
				} catch( WebsocketException e ) {
					log.error( e.getMessage(), e );
				}
			} else
				throw new RuntimeException( "Socket connection already initialized" );
		}, time, unit );
	}


	public JSONRPCResult sendMessage( Method method, Message message ) {
		if( !socketClient.isOpen() ) {
			throw new RuntimeException( "No message will be sent because there is no open socket connection to tendermint" ); 
		}
		
		
		CompletableFuture<JSONRPCResult> future = new CompletableFuture<>();

		try {
			JSONRPC rpc = message != null ? new StringParam( method, mapper.writeValueAsBytes( message ) ) : new EmptyParam( method );
			socketClient.sendMessage( rpc, res -> {
				future.complete( (JSONRPCResult) res );
			} );
			
			return future.get();
		} catch( InterruptedException | ExecutionException | JsonProcessingException e ) {
			throw new RuntimeException( e );
		}
	}
}
