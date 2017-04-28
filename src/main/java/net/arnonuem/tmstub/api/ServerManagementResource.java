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
package net.arnonuem.tmstub.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jtmsp.websocket.jsonrpc.JSONRPCResult;
import com.github.jtmsp.websocket.jsonrpc.Method;
import com.google.gson.internal.LinkedTreeMap;

import net.arnonuem.tmstub.statistics.Status;
import net.arnonuem.tmstub.statistics.StatusConverter;
import net.arnonuem.tmstub.sys.Message;
import net.arnonuem.tmstub.sys.TmCommunicatorService;

/**
 * 
 * @author arnonuem
 */
@RestController
@RequestMapping( "/api" )
public class ServerManagementResource {

	private final TmCommunicatorService tmCommunicator;
	private final StatusConverter statusConverter;

	@Autowired
	public ServerManagementResource( TmCommunicatorService tmCommunicator, StatusConverter statusConverter) {
		this.tmCommunicator = tmCommunicator;
		this.statusConverter = statusConverter;
	}


	@GetMapping( "/status" )
	public JSONRPCResult status() {
		return tmCommunicator.sendMessage( Method.STATUS, null );
	}


	@GetMapping( "/netinfo" )
	public JSONRPCResult netInfo() {
		return tmCommunicator.sendMessage( Method.NET_INFO, null );
	}
	
	
	@GetMapping( "/statistics" )
	public Status statistics() {
		JSONRPCResult rpcResult = tmCommunicator.sendMessage( Method.STATUS, null );
		LinkedTreeMap<?,?> map = (LinkedTreeMap<?,?>)rpcResult.result.get(1);
		return statusConverter.convert( map );
	}

	
	@PostMapping( "message" )
	public String message( @RequestParam("data") String data ) {
    JSONRPCResult rpc = tmCommunicator.sendMessage( Method.BROADCAST_TX_ASYNC, new Message( "", "", data ) );
    System.err.println( rpc );
    return "Message sent.";
	}
	
}









