(function() {
	var connStatus = document.getElementById('connStatus'), 
		connections = document.getElementById('connections') 
	;


	function connectionOpen(open) {
		connStatus.className = open ? 'open' : '';
		connStatus.innerHTML = open ? 'Active connection to server'
				: 'Connection dropped - trying to reopen';
	}
	
	function updateBeginBlock( data ) {
		document.getElementById('endblock').innerHTML = "chain id: " + data.chainId + "<br>block height (header): " + data.height + "<br>block hash: " + data.hash;
	}


	var source = new EventSource('/api/bcinfo');
	source.onopen = function( event ) { connectionOpen(true); };
	source.onerror = function( event ) { connectionOpen(false); };
	
	source.onconnections = function( event ) { console.log("connections") };
	source.onrequests = function( event ) { console.log("requests") };
	source.onupdate = function( event ) { console.log("uptime") };
	source.ontime = function( event ) { console.log("time") }; 
	source.onmessage = function( event ) {
		var payload = JSON.parse( event.data );
		if( 'beginblock' === payload.type ) {
			updateBeginBlock( payload.data );
		}
	};
	
})();