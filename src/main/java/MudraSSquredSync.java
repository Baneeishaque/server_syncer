public class MudraSSquredSync extends DbTable {

    @Override
    Server configureSecondServer() {

        return new SSquaredServer();
    }

    @Override
    Server configureFirstServer() {

        return new MudraServer();
    }
}
