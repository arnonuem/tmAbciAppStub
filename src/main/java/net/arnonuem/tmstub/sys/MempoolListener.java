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

import com.github.jtendermint.jabci.api.ICheckTx;

/**
 * Used by QUERY/INFO connection.
 * 
 * CheckTx requests run concurrently with block processing; so they should run against a copy of the main application state which 
 * is reset after every block. This copy is necessary to track transitions made by a sequence of CheckTx requests before they are 
 * included in a block. When a block is committed, the application must ensure to reset the mempool state to the latest committed 
 * state. Tendermint Core will then filter through all transactions in the mempool, removing any that were included in the block, 
 * and re-run the rest using CheckTx against the post-Commit mempool state.
 * 
 * https://tendermint.com/docs/guides/app-development
 * 
 * @author arnonuem
 */
public interface MempoolListener extends ICheckTx {
	
}
