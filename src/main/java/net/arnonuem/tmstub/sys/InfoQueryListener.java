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
 */
public interface InfoQueryListener extends IInfo, ISetOption, IQuery {

}
