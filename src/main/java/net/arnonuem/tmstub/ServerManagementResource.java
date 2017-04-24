package net.arnonuem.tmstub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jtmsp.websocket.jsonrpc.JSONRPCResult;
import com.github.jtmsp.websocket.jsonrpc.Method;

@RestController
@RequestMapping("/api")
public class ServerManagementResource {

	private final TmCommunicatorService tmCommunicator;
	
	@Autowired
	public ServerManagementResource( TmCommunicatorService tmCommunicator ) {
		this.tmCommunicator = tmCommunicator;
	}
	
	/**
	 * Should only be called once tendermint is connected
	 */
	@PostMapping("/startwebsocket")
	public void startWebsocket() {
		tmCommunicator.init();		
	}
	
	
	@GetMapping("/status")
	public void status() {
		tmCommunicator.sendMessage( Method.STATUS, null, res -> {
			 JSONRPCResult result = (JSONRPCResult)res;
			 System.err.println( result );
		 } );
	}
	
}
