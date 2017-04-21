package net.arnonuem.tmstub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jtmsp.websocket.Websocket;
import com.github.jtmsp.websocket.jsonrpc.JSONRPC;
import com.github.jtmsp.websocket.jsonrpc.Method;
import com.github.jtmsp.websocket.jsonrpc.calls.StringParam;
import com.google.gson.Gson;

@Component
public class MessageSender {
	
	private static final Logger log = LoggerFactory.getLogger( MessageSender.class );
	
	private Gson gson = new Gson();
	
	private final Websocket socketClient;
	
	@Autowired
	public MessageSender( Websocket socketClient ) {
		this.socketClient = socketClient;
	}
	
	public void sendMessage( Message message ) {
		JSONRPC rpc = new StringParam( Method.BROADCAST_TX_ASYNC, gson.toJson(message).getBytes() );
		
		 socketClient.sendMessage(rpc, e -> {
			 //no interest
			 System.err.println( e );
		 });
	
	}
	
	
	public void sendMessage( Method method, Message message ) {
		JSONRPC rpc = new StringParam( method, gson.toJson(message).getBytes() );
		
		 socketClient.sendMessage(rpc, e -> {
			 //no interest
			 System.err.println( e );
		 });
	}
}
