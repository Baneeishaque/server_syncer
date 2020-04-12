import okhttp3.Request;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

public abstract class DbTable {

    Server firstServer = configureFirstServer();
    Server secondServer = configureSecondServer();

    abstract Server configureSecondServer();

    abstract Server configureFirstServer();

    private String performHttpGetOnFirstServer(String methodName) {

        return OkHttpUtils.performHttpGet(new Request.Builder().url(firstServer.constructMethodUrl(methodName)).build());
    }

    private String performHttpGetOnSecondServer(String methodName) {

        return OkHttpUtils.performHttpGet(new Request.Builder().url(secondServer.constructMethodUrl(methodName)).build());
    }

    void performHttpGetJsonResponseComparison(String firstServerMethodName, String secondServerMethodName) {

        try {

//            JSONAssert.assertEquals(performHttpGetOnFirstServer(firstServerMethodName), performHttpGetOnSecondServer(secondServerMethodName), JSONCompareMode.STRICT);

            JSONCompareResult jsonCompareResult = JSONCompare.compareJSON(performHttpGetOnFirstServer(firstServerMethodName), performHttpGetOnSecondServer(secondServerMethodName), JSONCompareMode.STRICT);

            if (jsonCompareResult.failed()) {

                System.out.println(jsonCompareResult.getMessage());
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }
    }
}
