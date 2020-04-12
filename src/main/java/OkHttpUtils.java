import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class OkHttpUtils {

    // one instance, reuse
    static final OkHttpClient httpClient = new OkHttpClient();

    static String performHttpGet(Request request) {

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException(response.toString());
            return Objects.requireNonNull(response.body()).string();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return "";
    }
}
