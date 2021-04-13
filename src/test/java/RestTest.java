import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RestTest {
    private int userId;
    private final String initialEmail = "Knarik" + System.currentTimeMillis() + "@test.io";

    private String getValueFromJsonPath(JsonPath jsonPath, String path) {
        return jsonPath.getString(path);
    }


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public-api/";
    }

    @Test
    public void postUserTest() {
        String initialName = "Knarik" + System.currentTimeMillis();
        String initialGender = "Female";
        String initialStatus = "Active";

        String body = "{\n" +
                "  \"name\": \"" + initialName + "\",\n" +
                "  \"email\": \"" + initialEmail + "\",\n" +
                "  \"gender\": \"" + initialGender + "\",\n" +
                "  \"status\": \"" + initialStatus + "\"" +
                "}";

        ValidatableResponse response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("users")
                .then()
                .spec(getResponseSpecification());

        JsonPath jsonPath = response
                .extract()
                .jsonPath();

        userId = jsonPath.getInt("data.id");

        String expectedName = getValueFromJsonPath(jsonPath, "data.name");
        String expectedEmail = getValueFromJsonPath(jsonPath, "data.email");
        String expectedGender = getValueFromJsonPath(jsonPath, "data.gender");
        String expectedStatus = getValueFromJsonPath(jsonPath, "data.status");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(initialName, expectedName);
        softAssert.assertEquals(initialEmail, expectedEmail);
        softAssert.assertEquals(initialGender, expectedGender);
        softAssert.assertEquals(initialStatus, expectedStatus);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void deleteUserTest() {
        ValidatableResponse response;

        response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .when()
                .delete("users/{id}", userId)
                .then()
                .spec(getResponseSpecification());

        Assert.assertEquals(200, response.extract().statusCode());

        response = RestAssured
                .get("users/{id}", userId)
                .then();

        JsonPath jsonPath = response
                .extract()
                .jsonPath();

        String message = getValueFromJsonPath(jsonPath, "data.message");
        Assert.assertEquals(message, "Resource not found");
    }

    @Test(priority = 1)
    public void duplicateEmailTest() {
        String body = " {\n" +
                "    \"name\": \"epam2\",\n" +
                "    \"email\": \"" + initialEmail + "\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"status\": \"Active\"\n" +
                "}";

        ValidatableResponse response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("users")
                .then()
                .spec(getResponseSpecification());

        JsonPath jsonPath = response
                .extract()
                .jsonPath();

        String message = getValueFromJsonPath(jsonPath, "data.message");
        Assert.assertEquals(message, "[has already been taken]");
    }

    private RequestSpecification getRequestSpecification() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        return specBuilder
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer 4e15f3cfd4e19968718a9589775e8cad3ec8f1bcbfb07ed6d5b466e6f9560dc1")
                .build();
    }

    private ResponseSpecification getResponseSpecification() {
        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder();
        return specBuilder
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }
}
