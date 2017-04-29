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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jtendermint.jabci.types.Types.CodeType;
import com.github.jtendermint.jabci.types.Types.RequestDeliverTx;
import com.github.jtendermint.jabci.types.Types.ResponseDeliverTx;
import com.google.gson.Gson;

import net.arnonuem.tmstub.sys.Message;
import net.arnonuem.tmstub.sys.hash.HashUtil;

/**
 * 
 * @author arnonuem
 */
@Service
public class DeliverTxService {

	private static final Logger log = LoggerFactory.getLogger( DeliverTxService.class );
	
	private Gson gson = new Gson();
	
	@Autowired HashUtil hashUtil;
	
	public ResponseDeliverTx process( RequestDeliverTx req ) {
		log.debug( "Processing incoming message" );
		String data = new String( req.getTx().toByteArray() );
		Message message = gson.fromJson( data, Message.class );
		//TODO fire event or do something else with this message
		log.debug( message.toString() );
		
		if( hashUtil.compare( message.sender, "Bob" ) && hashUtil.compare( message.receiver, "Alice" ) ) {
			log.debug( "Sender was Bob and receiver is Alice" );
		}
		return ResponseDeliverTx.newBuilder().setCode( CodeType.OK ).build();
	}
	
}