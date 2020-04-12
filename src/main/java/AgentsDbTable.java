import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class AgentsDbTable {

    public static void main(String[] args) {

        AgentsDbTable agentsDbTable = new AgentsDbTable();
        agentsDbTable.performComparison();
    }

    void performComparison() {

        // one instance, reuse
        final OkHttpClient httpClient = new OkHttpClient();

        // form parameters
        RequestBody formBody = new FormBody.Builder()
                .add("username", "abc")
                .add("password", "123")
                .add("custom", "secret")
                .build();

        Request request = new Request.Builder()
                .url("https://httpbin.org/post")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(Objects.requireNonNull(response.body()).string());

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}