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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.github.jtendermint.jabci.socket.TSocket;

import net.arnonuem.tmstub.sys.TSockListener;

/**
 * 
 * @author arnonuem
 */
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
