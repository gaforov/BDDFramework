package api.steps.practice;

import static api.steps.practice._02_HardcodedExamplesImproved.employeeID;

public class _03_EmployeePayloads {

    public static String updateCreatedEmpBody() {
        return "{\n" +
                "  \"employee_id\": \"" + employeeID + "\",\n" +
                "  \"emp_firstname\": \"John\",\n" +
                "  \"emp_lastname\": \"Wick\",\n" +
                "  \"emp_middle_name\": \"A.\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1990-04-13\",\n" +
                "  \"emp_status\": \"Updated: Independent contractor\",\n" +
                "  \"emp_job_title\": \"Updated: Bodyguard\"\n" +
                "}";
    }

    public static String createEmployee() {
        return "{\n" +
                "  \"emp_firstname\": \"John\",\n" +
                "  \"emp_lastname\": \"Wick\",\n" +
                "  \"emp_middle_name\": \"R.\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1987-05-13\",\n" +
                "  \"emp_status\": \"Full-Time Employee\",\n" +
                "  \"emp_job_title\": \"SDET\"\n" +
                "}";
    }

    public static String patchPartiallyUpdate() {
        return "{\n" +
                "  \"employee_id\": \" "+ employeeID +" \",\n" +
                "  \"emp_job_title\": \"Partially Updated Bodyguard\"\n" +
                "}";
    }

}
