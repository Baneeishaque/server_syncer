public class SSquaredServer extends MudraServer {

    @Override
    String configureDomainName() {

        return "ssquared.qa";
    }

    @Override
    String configureApplicationFolder() {

        return "log-files";
    }

    @Override
    String configureHttpApiFolder() {

        return "core_wind_server/http_API";
    }
}
