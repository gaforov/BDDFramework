package api.steps.practice;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class TokenGenerationSteps {
    public static String token;
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"email\": \"mikeApi@gmail.com\",\n" +
                        "  \"password\": \"Mike@api123\"\n" +
                        "}");

        Response generateTokenResponse = request.when().post("/generateToken.php");
        //generateTokenResponse.prettyPrint();
        token = "Bearer " + generateTokenResponse.body().jsonPath().getString("token");
        //System.out.println(token);
    }
}
