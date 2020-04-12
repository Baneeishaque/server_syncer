import okhttp3.Request;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public abstract class DbTable {

    Server firstserver = configureFirstServer();
    Server secondServer = configureSecondServer();

    abstract Server configureSecondServer();

    abstract Server configureFirstServer();

    private String performHttpGetOnFirstServer(String methodName) {

        return OkHttpUtils.performHttpGet(new Request.Builder().url(firstserver.constructMethodUrl(methodName)).build());
    }

    private String performHttpGetOnSecondServer(String methodName) {

        return OkHttpUtils.performHttpGet(new Request.Builder().url(secondServer.constructMethodUrl(methodName)).build());
    }

    void performHttpGetJsonResponseComparison(String firstServerMethodName, String secondServerMethodName) {

        try {

            JSONAssert.assertEquals(performHttpGetOnFirstServer(firstServerMethodName), performHttpGetOnSecondServer(secondServerMethodName), JSONCompareMode.STRICT);

        } catch (JSONException e) {

            e.printStackTrace();
        }
    }
}
