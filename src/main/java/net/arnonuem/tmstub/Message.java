package net.arnonuem.tmstub;

public class Message {

	public final String sender;
	public final String receiver;
	public final String data;
	public final long timestamp;


	public Message() {
		sender = "";
		receiver = "";
		data = "";
		timestamp = System.currentTimeMillis();
	}


	public Message( String sender, String receiver, String data ) {
		this.sender = sender;
		this.receiver = receiver;
		this.data = data;
		timestamp = System.currentTimeMillis();
	}


	@Override
	public String toString() {
		return "Message [sender=" + sender + ", receiver=" + receiver + ", data=" + data + ", timestamp=" + timestamp + "]";
	}

}