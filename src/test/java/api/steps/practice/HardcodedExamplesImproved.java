package api.steps.practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HardcodedExamplesImproved {

    /*
    Rest Assured:
    GIVEN  - prepare your request
    WHEN - you are making the call to the endpoint
    THEN - validating
     */

    static String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    static String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTQzMTU0NTYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NDM1ODY1NiwidXNlcklkIjoiMzcxNCJ9.KzlmgI-bhoA-fny68iujumEoSQYbUlOeDExxc9oqEe4";
    static String employeeID;

    @Test
    public void aPOSTcreateEmployee() {
        RequestSpecification createEmployeeRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body("""
                        {
                          "emp_firstname": "John",
                          "emp_lastname": "Wick",
                          "emp_middle_name": "A.",
                          "emp_gender": "M",
                          "emp_birthday": "1990-03-15",
                          "emp_status": "Independent contractor",
                          "emp_job_title": "SDET Engineer"
                        }
                        """);
        Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");
        createEmployeeResponse.prettyPrint();

        createEmployeeResponse.then().assertThat().statusCode(201); // we can validate/verify if new employee creation was successful.

        // END of class 01 on Rest-Assured, below start of new day, class 02(API Class 08):

        // jsonPath to view response body. In this case, we get employee ID.
        // We store employeeID as a global variable so that we may re-use it with other calls.
        employeeID = createEmployeeResponse.jsonPath().getString("Employee.employee_id"); //if json body was inside "Employee": {["employee_id":"123abc45"]}, then we would use 'Employee[0].employee.id'

        // Optional to print employeeID
        System.out.println("employeeID = " + employeeID);
        createEmployeeResponse.then().assertThat().body("Message", equalTo("Employee Created")); // manually import static hamcrest.Matchers for this to fork
        // Verifying server using then().header()
        createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
        // Verifying Content-Type using assertThat().header()
        createEmployeeResponse.then().assertThat().header("Content-Type", "application/json");
    }

    @Test
    public void bGETcreatedEmployee() {
        // Preparing request for '/getOneEmployee.php' endpoint.
        RequestSpecification getCreatedEmployeeRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .queryParam("employee_id", employeeID);
        // Making call to retrieve created employee - still not working for some reason.
        Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().log().all().get("/getOneEmployee.php");
        getCreatedEmployeeResponse.prettyPrint();

        String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee.employee_id");
        boolean employeeIdMatch = empID.equalsIgnoreCase(employeeID);
        Assert.assertTrue(employeeIdMatch);

    }
}
