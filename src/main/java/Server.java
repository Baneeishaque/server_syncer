public abstract class Server {

    String constructMethodUrl(String methodName) {

        return constructEndPointUrl() + "/" + constructMethodFileName(methodName);
    }

    private String constructMethodFileName(String methodName) {

        return methodName + configureFileExtension();
    }

    abstract String configureFileExtension();

    private String constructEndPointUrl() {

        return configureHttpProtocol() + "://" + configureDomainName() + "/" + configureApplicationFolder() + "/" + configureHttpApiFolder();
    }

    abstract String configureHttpApiFolder();

    abstract String configureApplicationFolder();

    abstract String configureDomainName();

    abstract String configureHttpProtocol() ;

}
