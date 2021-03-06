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
package net.arnonuem.tmstub.sys.hash;

import java.security.NoSuchAlgorithmException;

import org.cryptacular.bean.EncodingHashBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.ByteString;

/**
 * 
 * @author arnonuem
 */
@Component
public class HashUtil {
	
	private final EncodingHashBean encodingHashBean;
	
	@Autowired
	public HashUtil( EncodingHashBean encodingHashBean ) {
		this.encodingHashBean = encodingHashBean;
	}
	
	public String makeHash( String input ) throws NoSuchAlgorithmException {
		//Nonce saltSource = new RBGNonce(8);
		//TODO use EncryptedNonce???
		String hexHash = encodingHashBean.hash( input/*, saltSource.generate()*/ );
		return hexHash;
	}
	
	public boolean compare( String hash, Object... data ) {
		return encodingHashBean.compare( hash, data[0] );
	}
	
	
	public String convertBinaryBlockHash( ByteString input ) {
		StringBuilder sb = new StringBuilder();
		for( byte b : input.toByteArray() ) {
			sb.append( String.format( "%02x", b ) );
		}
		return sb.toString();
	}
}
