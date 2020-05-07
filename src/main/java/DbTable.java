import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.skyscreamer.jsonassert.FieldComparisonFailure;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

import java.util.List;

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

    void performHttpGetJsonArrayResponseComparison(String firstServerMethodName, String secondServerMethodName, boolean isResponseIncludesStatus, String identityField, GenerateSyncQueries generateSyncQueries, boolean isDebugEnabled) {

        try {

//            JSONAssert.assertEquals(performHttpGetOnFirstServer(firstServerMethodName), performHttpGetOnSecondServer(secondServerMethodName), JSONCompareMode.STRICT);

            JSONArray firstJson = new JSONArray(performHttpGetOnFirstServer(firstServerMethodName));
            JSONArray secondJson = new JSONArray(performHttpGetOnSecondServer(secondServerMethodName));

            JSONCompareResult jsonCompareResult = JSONCompare.compareJSON(firstJson, secondJson, JSONCompareMode.STRICT);

            if (jsonCompareResult.failed()) {

//                System.out.println(jsonCompareResult.getMessage() + "\n");
//                System.out.println(jsonCompareResult.getFieldFailures() + "\n");

                List<FieldComparisonFailure> fieldComparisonFailures = jsonCompareResult.getFieldFailures();

                if (isDebugEnabled) {

                    for (FieldComparisonFailure fieldComparisonFailure : fieldComparisonFailures) {

                        String fieldWithIndexNumber = fieldComparisonFailure.getField();
                        int fieldIndexNumber = trimJsonIndexNumber(fieldWithIndexNumber);
                        String fieldName = fieldWithIndexNumber.substring(fieldWithIndexNumber.indexOf(".") + 1);

                        if (fieldIndexNumber == 0 && isResponseIncludesStatus) {

                            //TODO : Handle Status mismatch

                        } else {


//                        System.out.println("Expected JSON Object : " + firstJson.getJSONObject(fieldIndexNumber));
                            System.out.println("Expected Value of " + identityField + " " + firstJson.getJSONObject(fieldIndexNumber).getString(identityField) + " for " + fieldName + " : " + fieldComparisonFailure.getExpected());
//                        System.out.println("Actual JSON Object : " + secondJson.getJSONObject(fieldIndexNumber));
                            System.out.println("Actual Value of " + identityField + " " + secondJson.getJSONObject(fieldIndexNumber).getString(identityField) + " for " + fieldName + " : " + fieldComparisonFailure.getActual() + "\n");
                        }
                    }
                }

                generateSyncQueries.generate(fieldComparisonFailures, firstJson);
            }
            else{
//                System.out.println("firstServerMethodName = " + firstServerMethodName + ", secondServerMethodName = " + secondServerMethodName + ", isResponseIncludesStatus = " + isResponseIncludesStatus + ", identityField = " + identityField + ", generateSyncQueries = " + generateSyncQueries + ", isDebugEnabled = " + isDebugEnabled);
                System.out.println("JSON Outputs are same...");
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }
    }

    int trimJsonIndexNumber(String jsonIndexNumber) {

        return Integer.parseInt(jsonIndexNumber.substring(0, jsonIndexNumber.indexOf(".")).replace("[", "").replace("]", ""));
    }
}
