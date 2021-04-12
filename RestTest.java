import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestTest {
    JsonPath jsonPath;
    String postStatusCode;
    Header header = new Header("Authorization", "Bearer 4e15f3cfd4e19968718a9589775e8cad3ec8f1bcbfb07ed6d5b466e6f9560dc1");

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public-api/";
    }

    @Test(priority = 0)
    public void postUser() {
        String body1 = """
                    {
                    "name": "epam",
                    "email": "epam1@test.io",
                    "gender": "Female",
                    "status": "Active"
                }
                """;

        ValidatableResponse response = RestAssured
                .given()
                .header(header)
                .contentType(ContentType.JSON)
                .body(body1)
                .when()
                .post("users")
                .then();

        response.extract().response().prettyPrint();

        jsonPath = response.extract().jsonPath();
        postStatusCode = jsonPath.get("data.id").toString();
    }

    @Test(priority = 2)
    public void deleteUser() {
        Response response1 = RestAssured
                .given()
                .header(header)
                .when()
                .delete("users/{dd}", postStatusCode)
                .then()
                .extract().response();
        Assert.assertEquals(200, response1.statusCode());
    

    }
    @Test(priority = 1)
    public void duplicateEmail() {
        String body1 = """
                    {
                    "name": "epam2",
                    "email": "epam1@test.io",
                    "gender": "Female",
                    "status": "Active"
                }
                """;

        ValidatableResponse response = RestAssured
                .given()
                .header(header)
                .contentType(ContentType.JSON)
                .body(body1)
                .when()
                .post("users")
                .then();

        response.extract().response().prettyPrint();

    }

}