package api.steps.practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

// REST Assured 'static' imports
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

// Import this to make sure methods get executed in order
import org.junit.runners.MethodSorters;

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
    static String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTcxOTU5NTgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NzIzOTE1OCwidXNlcklkIjoiMzcxNCJ9.4FX-PVIz2AB7RmAA5bxEAt21Z1SUyT4PUBXm64w7iaw";
    static String employeeID;

    @Test
    public void aPOSTcreateEmployee() {
        System.out.println("\n------ Method A results start here --------------------");
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
                          "emp_status": "Full-Time Employee",
                          "emp_job_title": "SDET Engineer"
                        }
                        """);
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
        System.out.println("\n------ Method B results start here --------------------");

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
        String emploID = js.getString("employee.employee_id");
        String firstName = js.getString("employee.emp_firstname");
        String middleName = js.getString("employee.emp_middle_name");
        String lastName = js.getString("employee.emp_lastname");
        String birthday = js.getString("employee.emp_birthday");

        System.out.println("emploID = " + emploID);   // print some of them to make sure they're being retrieved.
        System.out.println("firstName = " + firstName);
        System.out.println("birthday = " + birthday);

        Assert.assertTrue(emploID.contentEquals(employeeID));
        Assert.assertEquals(employeeID, emploID); // another way for assertion

    }

    @Test
    public void cGETallEmployees() {
        System.out.println("\n------ Method C results start here --------------------");
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
        System.out.println("\n------ Method D results start here --------------------");

        /*
        Replace this --> {{employeeID}} <-- with this --> " + employeeID + " <--  if using three quotes then just this: "employeeID"
         */
        RequestSpecification updateCreatedEmployeeRequest = given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
//                .body("""
//                        {
//                          "employee_id": ""+employeeID+"",  // <== quite have not figured how to take care of this double quote inside triple quote, for now lets not use it.
//                          "emp_firstname": "John",
//                          "emp_lastname": "Wick",
//                          "emp_middle_name": "A.",
//                          "emp_gender": "M",
//                          "emp_birthday": "1990-04-13",
//                          "emp_status": "Independent contractor - Updated",
//                          "emp_job_title": "Bodyguard - Updated"
//                        }
//                        """);

                // paste body without triple quote, old way
                .body("{\n" +
                        "  \"employee_id\": \""+employeeID+"\",\n" +
                        "  \"emp_firstname\": \"John\",\n" +
                        "  \"emp_lastname\": \"Wick\",\n" +
                        "  \"emp_middle_name\": \"A.\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1990-04-13\",\n" +
                        "  \"emp_status\": \"Independent contractor - Updated\",\n" +
                        "  \"emp_job_title\": \"Bodyguard - Updated\"\n" +
                        "}");

//                .body(_03_EmployeePayloads.updateCreatedEmpBody()); // instead of above code, we're passing/calling the body from another class.

        Response updateCreatedEmployeeResponse = updateCreatedEmployeeRequest.when().put("/updateEmployee.php");
        updateCreatedEmployeeResponse.prettyPrint();

        // do some validation
//        updateCreatedEmployeeResponse.then().assertThat().body("Message", equalTo("Employee record Updated"));
//        String empID = updateCreatedEmployeeResponse.body().jsonPath().getString("Employee.emp_firstname");
//        Assert.assertTrue(empID.contentEquals(employeeID));
    }

}
