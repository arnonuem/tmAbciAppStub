package net.arnonuem.tmstub.statistics;

import org.springframework.stereotype.Component;

import com.google.gson.internal.LinkedTreeMap;

@Component
public class StatusConverter {

	public Status convert( LinkedTreeMap<?,?> map ) {
		LinkedTreeMap<?,?> nodeInfo = getElement( map, "node_info" );
		
		return new Status( 
				getElement( nodeInfo, "pub_key" ),
				getElement( nodeInfo, "network" ),
				getElement( nodeInfo, "listen_addr" ),
				getElement( nodeInfo, "version" ),
				getElement( map, "latest_block_hash" ),
				getElement( map, "latest_app_hash" ),
				getElement( map, "latest_block_height" ),
				getElement( map, "latest_block_time" )
		);
	}
	
	@SuppressWarnings( "unchecked" )
	private <T> T getElement( LinkedTreeMap<?,?> node, String key ) {
		return (T)node.get( key );
	}
}
