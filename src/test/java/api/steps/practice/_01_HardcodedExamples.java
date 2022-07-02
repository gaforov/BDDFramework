package api.steps.practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class _01_HardcodedExamples {

    /*
    Rest Assured:
    GIVEN  - prepare your request
    WHEN - you are making the call to the endpoint
    THEN - validating
     */

    public static void main(String[] args) {

        // BaseURI for all calls
        RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

        // JWT required for all calls - expires every 12 hours.
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTMxNjUwMTMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1MzIwODIxMywidXNlcklkIjoiMzcxNCJ9.SHrebmxz3WVMGsWv0cmJNCiibn3BTUr5nKI_gJZ8EwQ";

        // Preparing /getOneEmployee.php request
        RequestSpecification getOneEmployeeRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .queryParam("employee_id", "33632A"); // .log().all(); <-- displays log data

        // Storing response
        Response getOneEmployeeResponse = getOneEmployeeRequest.when().get("/getOneEmployee.php");

        // Two ways to print the response
        getOneEmployeeResponse.prettyPrint();
        //String response = getOneEmployeeResponse.body().asString();
        //System.out.println(response);

        // Lastly, we do validation (verifying status code is correct)
        getOneEmployeeResponse.then().assertThat().statusCode(200);

    }
}
