package jdbc;

import org.junit.Test;

import java.sql.*;

public class _01_JDBC {
    String dbUsername = "syntax_hrm";
    String dbPassword = "syntaxhrm123";
    // jdbc:driver-type://hostname:port/name of the database
    String dbURL ="jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";


    @Test
    public void testDb() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        System.out.println("DB connection is successful\n");
        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("SELECT * FROM ohrm_nationality");

        query.next(); // doesn't work without next. If we don't use while() it will print only first item, then stops. (No looping).
        String name = query.getString("name");
        System.out.println(name);

        System.out.println("--- Retrieve nationality from the DB ---");
        while (query.next()) {
            String nationality = query.getString("name");
            System.out.println(nationality);
        }

        // Exercise 1: Write another query to retrieve job titles from the database.
//        query = statement.executeQuery("SELECT * FROM ohrm_job_title;");
//        System.out.println("\n\n\n--- Retrieve job titles from the DB ---\n");
//        while ((query.next())) {
//            String jobTitle = query.getString("job_title");
//            System.out.println(jobTitle);
//        }

        // Exercise 2: Retrieve all job titles and store them in Arraylist then print it on the console.
//        query = statement.executeQuery("SELECT * FROM ohrm_job_title;");
//        List<String> jobTitles = new ArrayList<>();
//        while (query.next()) {
//            jobTitles.add(query.getString("job_title"));
//        }

//        System.out.println(jobTitles); // 1st way, direct print
//        jobTitles.forEach(jobTitle -> System.out.println(jobTitle)); // 2nd way printing, arrow operator and forEach
//        jobTitles.forEach(System.out::println); // better format // 3rd way, the best way, lambda expression forEach loop
//        for (String jotTitle : jobTitles) {  // 4th way using foreach loop
//            System.out.println(jotTitle);
//        }

        // Bonus: place this above code inside its own function/method using @Test annotation, just like line 18.
        // For example: public void retrieveAllJobTitles(){};


        query.close();
        statement.close();
        connection.close();

    }

}
