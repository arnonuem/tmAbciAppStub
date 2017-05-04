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
package net.arnonuem.tmstub.consensus;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jtendermint.jabci.types.Types.CodeType;
import com.github.jtendermint.jabci.types.Types.RequestDeliverTx;
import com.github.jtendermint.jabci.types.Types.ResponseDeliverTx;

import net.arnonuem.tmstub.api.BcInfo;
import net.arnonuem.tmstub.api.InfoDeliverTx;
import net.arnonuem.tmstub.sys.CrossCuttingStatistics;
import net.arnonuem.tmstub.sys.Message;
import net.arnonuem.tmstub.sys.hash.HashUtil;

/**
 * 
 * @author arnonuem
 */
@Service
public class DeliverTxService {

	private static final Logger log = LoggerFactory.getLogger( DeliverTxService.class );

	private final SubscribableChannel pubSubChannel;
	private final HashUtil hashUtil;
	private final ObjectMapper mapper;
	private final CrossCuttingStatistics statistics;
	
	@Autowired
	public DeliverTxService( SubscribableChannel pubSubChannel, HashUtil hashUtil, ObjectMapper mapper, CrossCuttingStatistics statistics ) {
		this.pubSubChannel = pubSubChannel;
		this.hashUtil = hashUtil;
		this.mapper = mapper;
		this.statistics = statistics;
	}
	

	public ResponseDeliverTx process( RequestDeliverTx req ) {
		log.debug( "Processing incoming message" );
		
		if( req.getTx().size() == 0 ) {
      return ResponseDeliverTx.newBuilder().setCode(CodeType.BadNonce).setLog("transaction is empty").build();
		}
		
		String data = new String( req.getTx().toByteArray() );

		
		BcInfo info = new BcInfo();
		info.type = "delivertx";
		info.data = new InfoDeliverTx( data );

		pubSubChannel.send( new GenericMessage<>( createPayload( info ) ) );
		
		try {
			Message message = mapper.readValue( data, Message.class );
			log.debug( message.toString() );

			if( hashUtil.compare( message.sender, "Bob" ) && hashUtil.compare( message.receiver, "Alice" ) ) {
				log.debug( "Sender was Bob and receiver is Alice" );
			}

		} catch( IOException e ) {
			return ResponseDeliverTx.newBuilder().setCode( CodeType.InternalError ).build();
		} 
		
		statistics.txCount++;
		log.debug( "transactions made since startup: " + statistics.txCount );
		
		return ResponseDeliverTx.newBuilder().setCode( CodeType.OK ).build();
	}

	
	private String createPayload( Object input ) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString( input );
		} catch( JsonProcessingException e ) {
			throw new RuntimeException( e );
		}
	}
	
}