package api.steps.practice;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class TokenGenerationSteps {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"email\": \"mikeApi@gmail.com\",\n" +
                        "  \"password\": \"Mike@api123\"\n" +
                        "}");
    }
}
