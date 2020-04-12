import org.json.JSONArray;
import org.skyscreamer.jsonassert.FieldComparisonFailure;

import java.util.List;

public interface GenerateSyncQueries {

    void generate(List<FieldComparisonFailure> fieldComparisonFailures, JSONArray firstJson);
}
