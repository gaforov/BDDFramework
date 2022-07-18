package api.steps.practice;

public class _03_EmployeePayloads {

    public static String updateCreatedEmpBody() {
        String updateBody = """
                {
                          "employee_id": "HardcodedExamplesImproved.employeeID",
                          "emp_firstname": "John",
                          "emp_lastname": "Wick",
                          "emp_middle_name": "A.",
                          "emp_gender": "M",
                          "emp_birthday": "1990-04-13",
                          "emp_status": "Independent contractor - Updated",
                          "emp_job_title": "Bodyguard - Updated"
                        }
                """;
        return updateBody;
    }

}
