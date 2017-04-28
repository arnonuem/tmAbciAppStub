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
package net.arnonuem.tmstub.sys;

/**
 * Provides read-only information for the Query and Mempool connection, 
 * but is only written by the Consensus connection.
 * @author arnonuem
 */
public class ApplicationState {

	private final String pubKey;
	private final String tendermintNetwork;
	private final String listenerAddress;
	private final String tendermintVersion;
	private final String latestBlockHash;
	private final String latestAppHash;
	private final Double latestBlockHeight;
	private final Double latestBlockTime;

	public ApplicationState( String pubKey, String network, String listenerAddress, String version, String latestBlockHash, String latestAppHash, Double latestBlockHeight, Double latestBlockTime ) {
		this.pubKey = pubKey;
		this.tendermintNetwork = network;
		this.listenerAddress = listenerAddress;
		this.tendermintVersion = version;
		this.latestBlockHash = latestBlockHash;
		this.latestAppHash = latestAppHash;
		this.latestBlockHeight = latestBlockHeight;
		this.latestBlockTime = latestBlockTime;
	}

	public String getPubKey() {
		return pubKey;
	}

	public String getNetwork() {
		return tendermintNetwork;
	}

	public String getListenerAddress() {
		return listenerAddress;
	}

	public String getVersion() {
		return tendermintVersion;
	}

	public String getLatestBlockHash() {
		return latestBlockHash;
	}

	public String getLatestAppHash() {
		return latestAppHash;
	}

	public Double getLatestBlockHeight() {
		return latestBlockHeight;
	}

	public Double getLatestBlockTime() {
		return latestBlockTime;
	}
	
}
