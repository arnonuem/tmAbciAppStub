package net.arnonuem.tmstub.sys;

import org.springframework.stereotype.Component;

@Component
public class CrossCuttingStatistics {

	private long txCount;
	private long blockHeight;

	private long lastBlockTime;
	private long lastBlockTimeDuration;
	private double lastAvgBlockTime;

	private double avgBlockTime;


	public CrossCuttingStatistics() {
		txCount = 0;
		blockHeight = 0;
		lastBlockTime = 0;
		lastBlockTimeDuration = 0;
		lastAvgBlockTime = 0;
		avgBlockTime = 0;
	}


	public void increaseTxCount() {
		this.txCount++;
	}


	public long txCount() {
		return this.txCount;
	}


	public void updateBlockHeight( long blockHeight ) {
		this.blockHeight = blockHeight;
	}


	public void updateBeginBlockTime( long blocktime ) {
		if( lastBlockTime == 0 ) {
			lastBlockTimeDuration = 0;
		} else {
			lastBlockTimeDuration = blocktime - lastBlockTime; // should be 1 driven by tendermint
		}

		avgBlockTime = calculateMovingAvgBlockTime();
		lastBlockTime = blocktime;
	}


	public double avgBlockTime() {
		return avgBlockTime;
	}


	private double calculateMovingAvgBlockTime() {
		double internalBlockHeight = new Double( blockHeight + 1 ); // we do not start at 0
		double internalLastBlockTimeDuration = new Double( lastBlockTimeDuration );
		double avgBlockTime = lastAvgBlockTime * (internalBlockHeight - 1) / internalBlockHeight + (internalLastBlockTimeDuration / internalBlockHeight);

		if( avgBlockTime == 0 )
			avgBlockTime = 1;

		lastAvgBlockTime = avgBlockTime;
		return avgBlockTime;
	}

}
