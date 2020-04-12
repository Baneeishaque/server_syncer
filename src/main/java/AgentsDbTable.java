import org.json.JSONArray;
import org.json.JSONException;
import org.skyscreamer.jsonassert.FieldComparisonFailure;

import java.util.List;

public class AgentsDbTable extends MudraSSquredSync {

    private boolean isResponseIncludesStatus = true;
    private String identityField = "userid";
    private String tableName = "agents";
    private boolean isDebugEnabled = true;

    public static void main(String[] args) {

        AgentsDbTable agentsDbTable = new AgentsDbTable();
        agentsDbTable.performComparison();
    }

    void performComparison() {

//        // form parameters
//        RequestBody formBody = new FormBody.Builder()
//                .add("username", "abc")
//                .add("password", "123")
//                .add("custom", "secret")
//                .build();
//
//        Request request = new Request.Builder()
//                .url("https://httpbin.org/post")
//                .addHeader("User-Agent", "OkHttp Bot")
//                .post(formBody)
//                .build();
//
//        try (Response response = httpClient.newCall(request).execute()) {
//
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            // Get response body
//            System.out.println(Objects.requireNonNull(response.body()).string());
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }

        performHttpGetJsonArrayResponseComparison(MudraServer.GET_AGENTS, MudraServer.GET_AGENTS, isResponseIncludesStatus, identityField, new GenerateSyncQueries() {

            @Override
            public void generate(List<FieldComparisonFailure> fieldComparisonFailures, JSONArray firstJson) {

                System.out.println("Preparing to Sync...");
                for (FieldComparisonFailure fieldComparisonFailure : fieldComparisonFailures) {

                    String fieldWithIndexNumber = fieldComparisonFailure.getField();
                    int fieldIndexNumber = trimJsonIndexNumber(fieldWithIndexNumber);
                    String fieldName = fieldWithIndexNumber.substring(fieldWithIndexNumber.indexOf(".") + 1);

                    try {

                        String query = "UPDATE " + tableName + " SET " + fieldName + "=" + fieldComparisonFailure.getExpected() + " WHERE " + identityField + "=" + firstJson.getJSONObject(fieldIndexNumber).getString(identityField);
                        System.out.println(query);

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
            }
        }, isDebugEnabled);
    }
}