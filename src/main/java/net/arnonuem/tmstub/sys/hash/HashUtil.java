package net.arnonuem.tmstub.sys.hash;

import java.security.NoSuchAlgorithmException;

import org.cryptacular.bean.EncodingHashBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
}
