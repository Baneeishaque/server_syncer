public class MudraServer extends Server {

    @Override
    String configureFileExtension() {

        return ".php";
    }

    @Override
    String configureHttpApiFolder() {

        return "http_API";
    }

    @Override
    String configureApplicationFolder() {

        return "wp-production";
    }

    @Override
    String configureDomainName() {

        return "mudrasports.com";
    }

    @Override
    String configureHttpProtocol() {

        return "http";
    }

    //Methods
    public static final String GET_AGENTS = "get_agents";
}
