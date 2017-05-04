package net.arnonuem.tmstub.sys;

import org.springframework.stereotype.Component;

@Component
public class CrossCuttingStatistics {

	public long txCount;

	public CrossCuttingStatistics() {
		txCount = 0;
	}
	
}
