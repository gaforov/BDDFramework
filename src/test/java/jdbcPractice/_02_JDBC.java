package jdbcPractice;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _02_JDBC {
    String dbUsername ="syntax_hrm";
    String dbPassword = "syntaxhrm123";
    // connector type : type of database//dbUrl:port/databaseName(endpoint)
    String dbUrl = "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";

    // Exercise 2: Retrieve all job titles and store them in Arraylist then print it on the console.
        // How to add values to List and Map (Collections in Java)
        // List.add("Hello")
        // Map.put(2)
    @Test
    public void testJobTitles() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("SELECT * FROM ohrm_job_title");

        List<String> jobTitles = new ArrayList<>();
        while (query.next()) {
            jobTitles.add(query.getString("job_title"));
        }

//        System.out.println(jobTitles); // 1st way, direct print
//        jobTitles.forEach(x -> System.out.println(x)); // 2nd way, using arrow operator and forEach()
        jobTitles.forEach(System.out::println); // 3rd way, lambda expression, introduced in Java 8
//        for (String jobTitle : jobTitles){ // 4th way, using enhanced/foreach loop
//            System.out.println(jobTitle);
//        }
    }
}
