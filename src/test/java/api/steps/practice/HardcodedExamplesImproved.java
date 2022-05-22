package api.steps.practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class HardcodedExamplesImproved {

    /*
    Rest Assured:
    GIVEN  - prepare your request
    WHEN - you are making the call to the endpoint
    THEN - validating
     */

    static String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTMxNjUwMTMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1MzIwODIxMywidXNlcklkIjoiMzcxNCJ9.SHrebmxz3WVMGsWv0cmJNCiibn3BTUr5nKI_gJZ8EwQ";

    public void aPOSTcreateEmployee() {

    }
}
