package api.steps.practice;

import api.utils._03_EmployeePayloads;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class WorkflowAllSteps {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    RequestSpecification request;
    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        request = given().header("Content-Type", "application/json")
                .header("Authorization", TokenGenerationSteps.token)
                .body(_03_EmployeePayloads.createEmployee());
    }
    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
    }
    @Then("status code for creating an employee is {int}")
    public void status_code_for_creating_an_employee_is(Integer int1) {
    }
    @Then("employee is created")
    public void employee_is_created() {
    }
    @Then("employee ID is stored as a global variable to be used for other calls.")
    public void employee_id_is_stored_as_a_global_variable_to_be_used_for_other_calls() {
    }
}
