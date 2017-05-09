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
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jtendermint.jabci.types.Types.RequestBeginBlock;
import com.github.jtendermint.jabci.types.Types.ResponseBeginBlock;

import net.arnonuem.tmstub.api.BcInfo;
import net.arnonuem.tmstub.api.InfoBeginBlock;
import net.arnonuem.tmstub.sys.CrossCuttingStatistics;
import net.arnonuem.tmstub.sys.hash.HashUtil;

/**
 * 
 * @author arnonuem
 */
@Service
public class BeginBlockService {

	private static final Logger log = LoggerFactory.getLogger( BeginBlockService.class );

	private final SubscribableChannel pubSubChannel;
	private final HashUtil hashUtil;
	private final CrossCuttingStatistics statistics;

	@Autowired
	public BeginBlockService( SubscribableChannel pubSubChannel, HashUtil hashUtil, CrossCuttingStatistics statistics ) {
		this.pubSubChannel = pubSubChannel;
		this.hashUtil = hashUtil;
		this.statistics = statistics;
	}


	public ResponseBeginBlock process( RequestBeginBlock req ) {
		String blockchainID = req.getHeader().getChainId();
		long blockHeight = req.getHeader().getHeight();
		long numOfTxs = req.getHeader().getNumTxs();
		long blockTime = req.getHeader().getTime();
		String blockHeaderHash = hashUtil.convertBinaryBlockHash( req.getHash() );
		
		statistics.updateBeginBlockTime( blockTime );
		statistics.updateBlockHeight( blockHeight );

		BcInfo info = new BcInfo();
		info.type = "beginblock";
		info.data = new InfoBeginBlock( blockHeight, blockHeaderHash, blockchainID, numOfTxs, blockTime, statistics.avgBlockTime() );		
		
		pubSubChannel.send( new GenericMessage<>( createPayload( info ) ) );		
		
		return ResponseBeginBlock.newBuilder().build();
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
