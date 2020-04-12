public class MudraSSquredSync extends DbTable {

    @Override
    Server configureSecondServer() {

        return new MudraServer();
    }

    @Override
    Server configureFirstServer() {

        return new MudraServer();
    }
}
