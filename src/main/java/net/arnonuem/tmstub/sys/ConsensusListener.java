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

import com.github.jtendermint.jabci.api.IBeginBlock;
import com.github.jtendermint.jabci.api.ICommit;
import com.github.jtendermint.jabci.api.IDeliverTx;
import com.github.jtendermint.jabci.api.IEndBlock;
import com.github.jtendermint.jabci.api.IInitChain;

/**
 * Used by CONSENSUS connection.
 * 
 * The consensus connection is used only when a new block is committed, and communicates all information from 
 * the block in a series of requests: BeginBlock, [DeliverTx, ...], EndBlock, Commit. 
 * That is, when a block is committed in the consensus, we send a list of DeliverTx requests 
 * (one for each transaction) sandwiched by BeginBlock and EndBlock requests, and followed by a Commit.
 * 
 * https://tendermint.com/docs/guides/app-development
 * 
 * @author arnonuem
 */
public interface ConsensusListener extends IInitChain, IBeginBlock, IDeliverTx, IEndBlock, ICommit {

}
