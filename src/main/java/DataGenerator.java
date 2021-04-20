import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;

public class DataGenerator {
    @DataProvider(name = "amazonDataProvider")
    public static Object[][] amazonDataProviderMethod() throws IOException, ParseException {
        Object[][] params = new Object[1][2];
        JSONArray paramsJSON = (JSONArray) (new JSONParser().parse(
                new FileReader("src/test/resources/parameter.json")));
        for (int i = 0; i < paramsJSON.size(); i++) {
            params[i][0] = ((JSONObject) paramsJSON.get(i)).get("expectedSearch");
            params[i][1] = ((JSONObject) paramsJSON.get(i)).get("textExpected");
        }
        return params;
    }
}