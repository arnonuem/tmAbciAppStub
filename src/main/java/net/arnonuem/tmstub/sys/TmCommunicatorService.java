package net.arnonuem.tmstub.sys;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jtmsp.websocket.Websocket;
import com.github.jtmsp.websocket.WebsocketException;
import com.github.jtmsp.websocket.jsonrpc.JSONRPC;
import com.github.jtmsp.websocket.jsonrpc.JSONRPCResult;
import com.github.jtmsp.websocket.jsonrpc.Method;
import com.github.jtmsp.websocket.jsonrpc.calls.EmptyParam;
import com.github.jtmsp.websocket.jsonrpc.calls.StringParam;
import com.google.gson.Gson;

@Service
public class TmCommunicatorService {

	private static final Logger log = LoggerFactory.getLogger( TmCommunicatorService.class );

	private final Websocket socketClient;

	private Gson gson = new Gson();


	@Autowired
	public TmCommunicatorService( Websocket socketClient ) {
		this.socketClient = socketClient;
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
				} catch (WebsocketException e) {
					log.error( e.getMessage(), e );
				}
			} else
				throw new RuntimeException( "Socket connection already initialized" );
		}, time, unit );
	}

	// public void sendMessage( Message message ) {
	// JSONRPC rpc = new StringParam( Method.BROADCAST_TX_ASYNC, gson.toJson(message).getBytes() );
	//
	// socketClient.sendMessage(rpc, e -> {
	// //no interest
	// System.err.println( e );
	// });
	//
	// }


	public JSONRPCResult sendMessage( Method method, Message message ) {
		CompletableFuture<JSONRPCResult> future = new CompletableFuture<>();
	
		JSONRPC rpc = message != null ? new StringParam( method, gson.toJson( message ).getBytes() ) : new EmptyParam( method );
		socketClient.sendMessage( rpc, res -> {		
			future.complete( (JSONRPCResult)res );
		} );
		
		try {
			return future.get();
		} catch( InterruptedException | ExecutionException e ) {
			throw new RuntimeException( e );
		}
	}
}
