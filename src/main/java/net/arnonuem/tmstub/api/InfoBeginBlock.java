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

/**
 * 
 * @author arnonuem
 */
public class InfoBeginBlock extends AbstractBcInfoData {

	public long height;
	public String hash;
	public String chainId;
	public long numOfTxs;
	public long blockTime;
	public double avgBlockTime;
	
	public InfoBeginBlock() {}

	public InfoBeginBlock( long height, String hash, String chainId, long numOfTxs, long blockTime, double avgBlockTime ) {
		this.height = height;
		this.hash = hash;
		this.chainId = chainId;
		this.numOfTxs = numOfTxs;
		this.blockTime = blockTime;
		this.avgBlockTime = avgBlockTime;
	}
	
}
