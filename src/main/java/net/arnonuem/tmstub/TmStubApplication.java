package net.arnonuem.tmstub;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.github.jtendermint.jabci.socket.TSocket;
import com.github.jtmsp.websocket.Websocket;


@SpringBootApplication
public class TmStubApplication {
	
	private static final Logger log = LoggerFactory.getLogger( TmStubApplication.class );
	
	@Autowired private Websocket socketClient;
	
	public static void main( String[] args ) {
		ConfigurableApplicationContext context = SpringApplication.run( TmStubApplication.class, args );
		TmStubApplication app = context.getBean( TmStubApplication.class );

		app.init();
		
		log.info( "Tendermint Socket started on port " + TSocket.DEFAULT_LISTEN_SOCKET_PORT + ", waiting for Tendermint to plug in consensus." );
	}

		
	private void init() {
		startTendermintSocket();
		
		//MAKE SURE THAT TENDERMINT IS ALREADY RUNNING!!!
		connectWebsocket();
	}
	
	private void startTendermintSocket() {
		TSocket tsock = new TSocket();
		tsock.registerListener( new TSockListener() );
		new Thread( tsock::start ).start();
	}
		
	private void connectWebsocket() {
		log.info( "Connecting websocket" );
		socketClient.reconnectWebsocket();
	}
	
}
