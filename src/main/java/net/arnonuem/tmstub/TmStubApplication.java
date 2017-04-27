package net.arnonuem.tmstub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.github.jtendermint.jabci.socket.TSocket;

import net.arnonuem.tmstub.sys.TSockListener;

@SpringBootApplication
public class TmStubApplication {

	private static final Logger log = LoggerFactory.getLogger( TmStubApplication.class );

	private final TSockListener listener;


	@Autowired
	public TmStubApplication( TSockListener listener ) {
		this.listener = listener;
	}


	public static void main( String[] args ) {
		ConfigurableApplicationContext context = SpringApplication.run( TmStubApplication.class, args );
		TmStubApplication app = context.getBean( TmStubApplication.class );

		app.startTendermintSocket();

		log.info( "Tendermint Socket started on port " + TSocket.DEFAULT_LISTEN_SOCKET_PORT + ", waiting for Tendermint to plug in consensus." );
	}


	private void startTendermintSocket() {
		log.info( "Starting Tendermint Socket" );
		TSocket tsock = new TSocket();
		tsock.registerListener( listener );
		new Thread( tsock::start ).start();
	}

}
