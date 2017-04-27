# tmAbciAppStub
Tendermint ABCI Java application stub for rapdid prototyping Tendermint apps.

## frameworks
The application is based on Spring Boot and JTendermint.

## run

Run the ABCI-App. It should open a Tendermint socket and wait for Tendermint connecting to it.

Run Tendermint `tendermint node` (see Tendermint docs). Tendermint should connect to the ABCI-App.

## development
All requests made by Tendermint are mapped into services, except `echo` and `flush`.
When implementing the desired app just wire the services and replace the `noop` methods with the desired content. 