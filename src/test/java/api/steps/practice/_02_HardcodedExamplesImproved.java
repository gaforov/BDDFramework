package api.steps.practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

// We may use below package - for now comment it out.
//import org.apache.hc.core5.http.ContentType;

// This (junit interface) annotation will execute @Test annotations in ascending order. Alphabetical order.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class _02_HardcodedExamplesImproved {

    /*
    Rest Assured:
    GIVEN  - prepare your request
    WHEN - you are making the call to the endpoint
    THEN - validating
     */

    static String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    static String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjE2NjczMDAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY2MTcxMDUwMCwidXNlcklkIjoiMzcxNCJ9.P8_EWzfb1oNEosmWc160qq3ui6bfUC2ZUGNxUQwkgcE";
    static String employeeID;

    @Test
    public void aPOSTcreateEmployee() {
        System.out.println("\n------ Method A : Create an employee --------------------");
        RequestSpecification createEmployeeRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(_03_EmployeePayloads.createEmployee());

        Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");
        createEmployeeResponse.prettyPrint();

        createEmployeeResponse.then().assertThat().statusCode(201); // we can validate/verify if new employee creation was successful.

        // END of class 01 on Rest-Assured, below start of new day, class 02(API Class 08):

        // jsonPath to view response body. In this case, we get employee ID.
        // We store employeeID as a global variable so that we may re-use it with other calls.
        employeeID = createEmployeeResponse.jsonPath().getString("Employee.employee_id"); //if json body was inside brackets like "Employee": {["employee_id":"123abc45"]}, then we would use 'Employee[0].employee.id'

        // Optional to print employeeID
        System.out.println("employeeID = " + employeeID);

        // Run some assertions/validations
        createEmployeeResponse.then().assertThat().body("Message", equalTo("Employee Created")); // manually import static org.hamcrest.Matchers for "equalTo()" to fork
        createEmployeeResponse.then().assertThat().body("Employee.emp_firstname", equalTo("John"));
        // Verifying server using then().header()
        createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
        // Verifying Content-Type using assertThat().header()
        createEmployeeResponse.then().assertThat().header("Content-Type", "application/json");
    }

    @Test
    public void bGETcreatedEmployee() {
        System.out.println("\n------ Method B : Get created employee --------------------");

//        System.out.println("employeeID = " + employeeID);  /// <=== WHY THIS IS NULL?? I assigned value to it when created employee and stored on global static variable.

        // Preparing request for '/getOneEmployee.php' endpoint.
        RequestSpecification getCreatedEmployeeRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .queryParam("employee_id", employeeID);
        // Making call to retrieve created employee - still not working for some reason.
        Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().get("/getOneEmployee.php");
        String prettyPrintResponse = getCreatedEmployeeResponse.prettyPrint();
//        getCreatedEmployeeResponse.body().jsonPath().prettyPrint(); // optional printing to see why global static employeeID is not recognized, but when entire class is run then it is recognized. individually not, why?

        String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee.employee_id");
        boolean employeeIdMatch = empID.equalsIgnoreCase(employeeID); // Also, can use contentEquals(employeeID)
        System.out.println(employeeIdMatch);
        Assert.assertTrue(employeeIdMatch);

        // Assert that status code is 200
        getCreatedEmployeeResponse.then().assertThat().statusCode(200);

        // Note: Here, we manually import MethodSorters() to make sure methods to execute in order. (along with @FixMethodOrder())

        // Using JsonPath to retrieve values from the response as a String. This also could've been done using body.jsonPath() like in line 82 above.
        JsonPath js = new JsonPath(prettyPrintResponse);
        String emploID = js.getString("employee.employee_id");  // same as line 82, just different way
        String firstName = js.getString("employee.emp_firstname");
        String middleName = js.getString("employee.emp_middle_name");
        String lastName = js.getString("employee.emp_lastname");
        String birthday = js.getString("employee.emp_birthday");

        // print some of them to make sure they're being retrieved.
        System.out.println("emploID = " + emploID);
        System.out.println("firstName = " + firstName);
        System.out.println("birthday = " + birthday);

        Assert.assertTrue(emploID.contentEquals(employeeID));
        Assert.assertEquals(employeeID, emploID); // another way for assertion

    }

    @Test
    public void cGETallEmployees() {
        System.out.println("\n------ Method C : Getting All employees and verifying newly-created employee is present --------------------");
        RequestSpecification getAllEmployeesRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        // Response will be, to pull all employees from the DB. Then print them using prettyPrint().
        Response getAllEmployeesResponse = getAllEmployeesRequest.when().get("/getAllEmployees.php");
//        getAllEmployeesResponse.prettyPrint(); // print all employees as json format

        String allEmployees = getAllEmployeesResponse.body().asString();
        JsonPath js = new JsonPath(allEmployees);
        int numberOfAllEmployees = js.getInt("Employees.size()"); // Retrieve total number of existing employees; returns List of employees thus size() is used.
        System.out.println("Total number of employees: " + numberOfAllEmployees);

        // Exercise: Print out all employee IDs.
        for (int i = 0; i < numberOfAllEmployees; i++) {
            String employeeId = js.getString("Employees[" + i + "].employee_id");
//            System.out.println(employeeId);

            // find an employee using empID
            if (employeeId.contentEquals(employeeID)) {
                System.out.println("Employee ID: " + employeeID + " is present.");
                String employeeFirstName = js.getString("Employees[" + i + "].emp_firstname");
                System.out.println("Employee First Name = " + employeeFirstName);
                break;
            }
        }

        // Print all employees first name and last name
//        for (int i = 0; i < numberOfAllEmployees; i++) {
//            String employeeFullName = js.getString("Employees[" + i + "].emp_firstname") + " " + js.getString("Employees[" + i + "].emp_lastname");  // trying concatenation inside one path didn't work.
//            System.out.println(employeeFullName);
//        }

    }

    @Test
    public void dPUTupdateCreatedEmployee() {
        System.out.println("\n------ Method D : Updating employee --------------------");

        /*
        Replace this --> {{employeeID}} <-- with this --> " + employeeID + " <--  if using three quotes then just this: "employeeID"
         */
        RequestSpecification updateCreatedEmployeeRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
//                .body("{\n" +
//                        "  \"employee_id\": \""+employeeID+"\",\n" +
//                        "  \"emp_firstname\": \"John\",\n" +
//                        "  \"emp_lastname\": \"Wick\",\n" +
//                        "  \"emp_middle_name\": \"A.\",\n" +
//                        "  \"emp_gender\": \"M\",\n" +
//                        "  \"emp_birthday\": \"1990-04-13\",\n" +
//                        "  \"emp_status\": \"Independent contractor - Updated\",\n" +
//                        "  \"emp_job_title\": \"Bodyguard - Updated\"\n" +
//                        "}");

                //this is the same as above body code, just body is imported from another class where payloads are stored.
                .body(_03_EmployeePayloads.updateCreatedEmpBody());

        // Print response
        Response updateCreatedEmployeeResponse = updateCreatedEmployeeRequest.when().put("/updateEmployee.php");
        String prettyPrint = updateCreatedEmployeeResponse.prettyPrint();

        // do some validation
        updateCreatedEmployeeResponse.then().assertThat().body("Message", equalTo("Employee record Updated"));
        updateCreatedEmployeeResponse.then().assertThat().body("Employee.emp_status", equalTo("Updated: Independent contractor"));
//        String empID = updateCreatedEmployeeResponse.body().jsonPath().getString("Employee.emp_firstname"); //Note: For some reason, cannot assert this line.
//        System.out.println(empID);
//        Assert.assertTrue(empID.contentEquals(employeeID));
    }

    @Test
    public void eGETUpdatedEmployee() {
        System.out.println("\n------ Method E : Get Updated employee --------------------");

        RequestSpecification request = given()
//                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .queryParam("employee_id", employeeID);
        Response response = request.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().body("employee.emp_job_title", equalTo("Updated: Bodyguard"));
        response.then().assertThat().body("employee.employee_id", equalTo(employeeID));
    }

    @Test
    public void fPATCHPartialUpdateEmployee() {
        System.out.println("\n------ Method F : Partially update employee --------------------");

        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body("{\n" +
                        "  \"employee_id\": \" "+employeeID+" \",\n" +
                        "  \"emp_job_title\": \"Partially Updated: Bodyguard\"\n" +
                        "}");

        Response response = request.when().patch("/updatePartialEmplyeesDetails.php");

        response.prettyPrint();

    }

    @Test
    public void gDeleteEmployee() {
        System.out.println("\n------ Method G : Delete employee --------------------");

        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .queryParam("employee_id", employeeID);
        Response response = request.when()
                .delete("/deleteEmployee.php");
        response.prettyPrint();
        response.then().assertThat().body("message", equalTo("Employee deleted"));
    }

}
