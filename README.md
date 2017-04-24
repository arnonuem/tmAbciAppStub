# tmAbciAppStub
tendermint abci java app stub

Run the ABCI-App. It should open a Tendermint socket and wait for Tendermint connecting to it.

Run Tendermint `tendermint node` (see Tendermint docs). Tendermint should connect to the ABCI-App.

To talk to the Tendermint node open a websocket connection by sending a `POST` to `http://localhost:8080/api/startwebsocket`.