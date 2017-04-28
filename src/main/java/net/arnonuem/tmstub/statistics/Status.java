package net.arnonuem.tmstub.statistics;

public class Status {

	private final String pubKey;
	private final String tendermintNetwork;
	private final String listenerAddress;
	private final String tendermintVersion;
	private final String latestBlockHash;
	private final String latestAppHash;
	private final Double latestBlockHeight;
	private final Double latestBlockTime;

	public Status( String pubKey, String network, String listenerAddress, String version, String latestBlockHash, String latestAppHash, Double latestBlockHeight, Double latestBlockTime ) {
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
