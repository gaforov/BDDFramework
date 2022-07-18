package api.steps.practice;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/**
 * Rest assured static packages
 */
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
/**
 * importing JUnit packages
 */
import org.junit.Assert;
import org.junit.Test;
//please import
import org.junit.*;
import org.junit.runners.MethodSorters;
/**
 * We may use below - please comment out for now
 */
//import org.apache.hc.core5.http.ContentType;

//import com.hrms.API.utils.PayloadConstants;

/**
 * This @FixMethodOrder(MethodSorters.Name_Ascending) will execute @Test
 * annotation in ascending alphabetical order
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PracticeExamples {
    /**
     * Rest Assured
     *
     * given - prepare your request
     * when - you are making the call to the endpoint
     * then - validating
     *
     * @param args
     */

    static String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    static String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTQ1ODkwMjgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NDYzMjIyOCwidXNlcklkIjoiMzcxNCJ9.684-imGPWjPLcT--IO4iqsev2c6jS_lDh1zg6FsnMBo";
    public static String employeeID;

    public void sampleTestNotes() {
        /**
         * BaseURI for all calls
         */

        RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";

        /**
         * JWT required for all calls - expires every 12 hours
         */
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTUxNjg1NjAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTIxMTc2MCwidXNlcklkIjoiNjQ1In0.huJh3_deVDC49u1tP55dxMic1zfMxOEohdurkOUSzlk";

        /**
         * Preparing /getOneEmployee.php request
         */
        RequestSpecification getOneEmployeeRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token).queryParam("employee_id", "16490A"); // .log().all();

        /**
         * Storing response
         */
        Response getOneEmployeeResponse = getOneEmployeeRequest.when().get("/getOneEmployee.php");

        /**
         * Two ways to print response prettyPrint() method converts JSON object into
         * String and prints - no need to SOP
         */
        getOneEmployeeResponse.prettyPrint();
        // String response = getOneEmployeeResponse.body().asString();
        // System.out.println(response);

        /**
         * Verifying response status code is 200
         */
        getOneEmployeeResponse.then().assertThat().statusCode(200);

    }

    @Test
    public void aPOSTcreateEmployee() {

        /**
         * Preparing request for /createEmployee.php
         */
        RequestSpecification createEmployeeRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token)
                .body("{\n" + "  \"emp_firstname\": \"syntaxFirstName\",\n"
                        + "  \"emp_lastname\": \"syntaxLastName\",\n" + "  \"emp_middle_name\": \"syntaxMiddleName\",\n"
                        + "  \"emp_gender\": \"F\",\n" + "  \"emp_birthday\": \"2000-07-11\",\n"
                        + "  \"emp_status\": \"Employee\",\n" + "  \"emp_job_title\": \"Cloud Architect\"\n" + "}"); // .log().all();

        /**
         * Storing response into createEmployeeResponse
         */
        Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");

        /**
         * Printing response using prettyPrint() method
         */
        createEmployeeResponse.prettyPrint();

        /**
         * jsonPath() to view response body which lets us get the employee ID We store
         * employee ID as a global variable so that we may we use it with our other
         * calls
         *
         */

        employeeID = createEmployeeResponse.body().jsonPath().getString("Employee.employee_id");  //remove [0]

        /** optional to print employee ID */
        System.out.println(employeeID);

        /**
         * Verifying response status code is 201
         */
        createEmployeeResponse.then().assertThat().statusCode(201);

        /**
         * Verifying message using equalTo() method - manually import static package
         * import static org.hamcrest.Matchers.*;
         */
        createEmployeeResponse.then().assertThat().body("Message", equalTo("Employee Created"));  // change from 'Entry Created' to 'Employee Created'

        /**
         * Verifying created first name
         */
        createEmployeeResponse.then().assertThat().body("Employee.emp_firstname", equalTo("syntaxFirstName"));

        /**
         * Verifying server using then().header()
         */
        createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

        /**
         * Verifying Content-Type using assertThat().header()
         */
        createEmployeeResponse.then().assertThat().header("Content-Type", "application/json");

    }

    @Test
    public void bGETcreatedEmployee() {

        /**
         * Preparing request for /getOneEmployee.php Using log().all() to see all
         * information being sent with request
         */
        RequestSpecification getCreatedEmployeeRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token).queryParam("employee_id", employeeID); //.log().all();

        /**
         * Making call to retrieve created employee
         */
        Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().get("/getOneEmployee.php");

        /**
         * Printing response using prettyPrint()
         */
        String response = getCreatedEmployeeResponse.prettyPrint();

        /**
         * Storing response employeeID into empID which will be used for verification
         * against stored global employee ID
         */
        String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee.employee_id");

        /**
         * matching exact employeeID's
         */
        boolean verifyingEmpoyeeIDsMatch = empID.contentEquals(employeeID);
        System.out.println("Employee ID's match: " + verifyingEmpoyeeIDsMatch);

        /**
         * Asserting employee ID's match
         */
        Assert.assertTrue(verifyingEmpoyeeIDsMatch);

        /**
         * Verifying status code is 200
         */
        getCreatedEmployeeResponse.then().assertThat().statusCode(200);

        /**
         * Using JsonPath class to retrieve values from response as a String
         */

        JsonPath js = new JsonPath(response);

        String emplID = js.getString("employee[0].employee_id");
        String firstName = js.getString("employee[0].emp_firstname");
        String middleName = js.getString("employee[0].emp_middle_name");
        String lastName = js.getString("employee[0].emp_lastname");
        String emp_bday = js.getString("employee[0].emp_birthday");
        String gender = js.getString("employee[0].emp_gender");
        String jobTitle = js.getString("employee[0].emp_job_title");
        String empStatus = js.getString("employee[0].emp_status");

        System.out.println(emplID);
        System.out.println(firstName);

        /** Verifying employee ID's match */
        Assert.assertTrue(emplID.contentEquals(employeeID));
        // Second way of asserting
        Assert.assertEquals(employeeID, emplID);

        /** Verifying expected first name matches actual first name */
        Assert.assertTrue(firstName.contentEquals("syntaxFirstName"));

        /** Verifying expected middle name matches actual middle name */
        Assert.assertTrue(middleName.contentEquals("syntaxMiddleName"));

        /** Verifying expected last name matches actual last name */
        Assert.assertTrue(lastName.contentEquals("syntaxLastName"));

        /** Verifying expected birth date matches actual birth date */
        Assert.assertTrue(emp_bday.contentEquals("2000-07-11"));

        /** Verifying expected gender matches actual gender */
        Assert.assertTrue(gender.contentEquals("Female"));

        /** Verifying expected job title matches expected job title */
        Assert.assertTrue(jobTitle.contentEquals("Cloud Architect"));

        /** Verifying expected employee status matches actual employee status */
        Assert.assertTrue(empStatus.contentEquals("Employee"));

    }

    @Test
    public void cGETallEmployees() {

        /**
         * Preparing /getAllEmployees.php request
         */
        RequestSpecification getAllEmployeesRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token);

        /**
         * Storing response into getAllEmployeesResponse
         */
        Response getAllEmployeesResponse = getAllEmployeesRequest.when().get("/getAllEmployees.php");

        /**
         * Printing all employees
         */
        // getAllEmployeesResponse.prettyPrint();

        String allEmployees = getAllEmployeesResponse.body().asString();

        /**
         * The below will pass but incorrect
         */
        // allEmployees.contains(employeeID);
        /**
         * ----------- DO RESEARCH ------------
         */
        // allEmployees.matches(employeeID);

        JsonPath js = new JsonPath(allEmployees);

        /**
         * Retrieving size of Employees list
         */
        int sizeOfList = js.getInt("Employees.size()");

        System.out.println(sizeOfList);

        /**
         * Print out all employee ID's
         */

//		for (int i = 0; i < sizeOfList; i++) {
//
        /**
         * Printing all employee IDs
         */
//			String allEmployeeIDs = js.getString("Employees[" + i + "].employee_id");
//			// System.out.println(allEmployeeIDs);
//
        /**
         * If statement inside for loop to find stored employee ID and break the loop
         * when found
         */
//
//			if (allEmployeeIDs.contentEquals(employeeID)) {
//
//				System.out.println("Employee ID: " + employeeID + " is present in body");
//				String employeeFirstName = js.getString("Employees[" + i + "].emp_firstname");
//				System.out.println(employeeFirstName);
//
//
//				break;
//
//			}
//
//		}

    }

    @Test
    public void dPUTupdateCreatedEmployee() {

        /**
         * Preparing request to update created employee
         * calling static method updateCtreatedEmpBody() from HardcodedConstants class
         */

        RequestSpecification updateCreatedEmployeeRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token).body(_03_EmployeePayloads.updateCreatedEmpBody());    // <=== changed here with my code HardcodedConstants

        /**
         * Storing response into updateCreatedEmployeeResponse
         */
        Response updateCreatedEmployeeResponse = updateCreatedEmployeeRequest.when().put("/updateEmployee.php");

        /**
         * Storing response into a String
         */
        //	String response = updateCreatedEmployeeResponse.prettyPrint();

        /**
         * Asserting using hamcrest matchers equalTo() method to verify employee is updated
         */
        updateCreatedEmployeeResponse.then().assertThat().body("Message", equalTo("Employee record Updated")); // Change 'Entry Updated' to 'Employee record Updated'

        /*
         * Retrieving response body employee ID using jsontPath()
         */
        String empID = updateCreatedEmployeeResponse.body().jsonPath().getString("employee.employee_id");

        /**
         * Asserting that response body employee ID matches globally stored employee ID
         */
        Assert.assertTrue(empID.contentEquals(employeeID));

    }

    @Test
    public void eGETUpdatedEmployee() {

        /**
         * Preparing request to get updated employee
         */
        RequestSpecification getUpdatedEmployeeRequest = given().header("Content-Type","application/json").header("Authorization", token).queryParam("employee_id", employeeID);

        /**
         * Storing response into getUpdatedEmployeeResponse
         */
        Response getUpdatedEmployeeResponse = getUpdatedEmployeeRequest.when().get("/getOneEmployee.php");

        /**
         * Printing response
         */
        //getUpdatedEmployeeResponse.prettyPrint();

        /**
         * Asserting expected first name
         */
        getUpdatedEmployeeResponse.then().assertThat().body("employee[0].emp_firstname", equalTo("syntaxUpdatedFirstName"));

        /**
         * Verifying response employee ID is equal to globally stored employee ID
         */
        getUpdatedEmployeeResponse.then().assertThat().body("employee[0].employee_id", equalTo(employeeID));

    }

    @Test
    public void fPATCHpartiallyUpdateEmployee() {

        RequestSpecification partiallyUpdatingEmployeeRequest = given().header("Content-Type","application/json").header("Authorization", token).body("{\n" +
                "  \"employee_id\": \""+ employeeID +"\",\n" +
                "  \"emp_firstname\": \"syntaxPartiallyUpdatedFirstName\"\n" +
                "}");

        Response partiallyUpdatingEmployeeResponse = partiallyUpdatingEmployeeRequest.when().patch("/updatePartialEmplyeesDetails.php");

        //partiallyUpdatingEmployeeResponse.prettyPrint();

        partiallyUpdatingEmployeeResponse.then().assertThat().statusCode(201);

        partiallyUpdatingEmployeeResponse.then().assertThat().body("Message", equalTo("Entry updated"));

    }

    @Test
    public void gDELETEemployee() {

        RequestSpecification deleteEmployeeRequest = given().header("Content-Type","application/json").header("Authorization", token).queryParam("employee_id", employeeID);
        Response deleteEmployeeResponse = deleteEmployeeRequest.when().delete("/deleteEmployee.php");
        deleteEmployeeResponse.prettyPrint();
        deleteEmployeeResponse.then().assertThat().body("message", equalTo("Entry deleted"));

    }
}
