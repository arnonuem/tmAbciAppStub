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

import com.github.jtendermint.jabci.api.IInfo;
import com.github.jtendermint.jabci.api.IQuery;
import com.github.jtendermint.jabci.api.ISetOption;

/**
 * Used by QUERY/INFO connection.
 * 
 * This connection is used to query the application without engaging consensus. It’s exposed over the tendermint core rpc, 
 * so clients can query the app without exposing a server on the app itself, but they must serialize each query as a single byte array. 
 * Additionally, certain “standardized” queries may be used to inform local decisions, for instance about which peers to connect to.
 * Tendermint Core currently uses the Query connection to filter peers upon connecting, according to IP address or public key.
 * 
 * https://tendermint.com/docs/guides/app-development
 * 
 * @author arnonuem
 */
public interface InfoQueryListener extends IInfo, ISetOption, IQuery {

}
