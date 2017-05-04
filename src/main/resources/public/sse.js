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
		document.getElementById('txdata').innerHTML = "";
		document.getElementById('beginblock').innerHTML = "chain id: " + data.chainId + "<br>block height (header): " + data.height + "<br>block hash: " + data.hash;
	}

	function updateEndBlock( data ) {
		document.getElementById('endblock').innerHTML = "block height: " + data.height;
	}
	
	function updateDeliverTx( data ) {
		const d = JSON.parse( data.tx );
		const date = formatDate( new Date( d.timestamp ) );
		
		document.getElementById('txdata').innerHTML = "sender: " + d.sender + "<br>receiver: " + d.receiver + "<br>data: " + d.data + "<br>time: " + date;
	}


	function formatDate( date ) {
		var yyyy = date.getFullYear();
		var mm = date.getMonth()+1; //January is 0!
		var dd = date.getDate();
		
		var h = date.getHours();
		var m = date.getMinutes();
		var s = date.getSeconds();
		
		if(dd<10){ dd='0'+dd; } 
		if(mm<10){ mm='0'+mm; }
		if(m<10){ m='0'+m; }
		if(s<10){ s='0'+s; }
		
		return dd+'.'+mm+'.'+yyyy+' '+h+':'+m+':'+s;
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
		if( 'endblock' === payload.type ) {
			updateEndBlock( payload.data );
		}
		if( 'delivertx' === payload.type ) {
			updateDeliverTx( payload.data );
		}
	};
	
	
})();