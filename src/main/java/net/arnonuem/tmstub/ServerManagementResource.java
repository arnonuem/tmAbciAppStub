package net.arnonuem.tmstub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ServerManagementResource {

	private final TmCommunicatorService tmCommunicator;
	
	@Autowired
	public ServerManagementResource( TmCommunicatorService tmCommunicator ) {
		this.tmCommunicator = tmCommunicator;
	}
	
	@PostMapping("/startwebsocket")
	public void startWebsocket() {
		
		//TODO make sure to call this once tendermint is connected
		tmCommunicator.init();
	}
	
}
