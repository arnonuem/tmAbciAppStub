# tmAbciAppStub
tendermint abci java app stub

First run tendermint until you see messages like 
`WARN[04-21|15:46:54] abci.socketClient failed to connect to tcp://127.0.0.1:46658.  Retrying... module=abcicli`

Then run the app. It should opens a Tendermint socket and tendermint connects to it. you see messages like: `NOTE[04-21|15:47:06] ABCI Handshake module=consensus appHeight=0 appHash=`.
The app then opens up a websocket connection to the running tendermint node for communicating with tendermint.
