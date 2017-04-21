package net.arnonuem.tmstub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.jtmsp.socket.TSocket;


@SpringBootApplication
public class TmStubApplication {
	
	private static final Logger log = LoggerFactory.getLogger( TmStubApplication.class );
	
	public static void main( String[] args ) {
		SpringApplication.run( TmStubApplication.class, args );
		
		
		
		TSocket tsock = new TSocket();
		tsock.registerListener( new TSockListener() );
		new Thread( tsock::start ).start();		
		log.info( "Tendermint Socket started on port " + TSocket.DEFAULT_LISTEN_SOCKET_PORT + ", waiting for Tendermint to plug in consensus." );
			
	}

		
}
