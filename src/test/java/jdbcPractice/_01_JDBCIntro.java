package jdbcPractice;

import org.junit.Test;

import java.sql.*;
import java.util.Objects;

public class _01_JDBCIntro {
    String dbUsername ="syntax_hrm";
    String dbPassword = "syntaxhrm123";
    // connector type : type of database//dbUrl:port/databaseName(endpoint)
    String dbUrl = "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";


    @Test
    public void testDb() throws SQLException {
        /* jdbc libraries to perform/run sql queries:
        1. DriverManger Class ---> Connection
        2. Connection Interface ---> Statement
        3. Statement Interface ---> ResultSet(query). Map = K, V = EntrySet,= ResultSet
        4. ResultSet Class/Interface
         */
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//        System.out.println("DB connection is successfull");
        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("SELECT * FROM ohrm_nationality;");

        query.next();
        String nationality = query.getString("name");
        System.out.println(nationality);

        System.out.println("---------------");
        // How to retrieve all nationalities from the DB and print to console.
        while (query.next()) {
            String nameOfNationalities = query.getString("name");
            System.out.println(nameOfNationalities);
//            if (nameOfNationalities.equals("Spanish")) // equals() check value, == checks location at memory
//                break;
        }

        // Exercise 1: Write another query to retrieve all job titles from the DB.
        System.out.println("---------------");
        ResultSet jobs = statement.executeQuery("SELECT * FROM ohrm_job_title");
        while (jobs.next()) {
            System.out.println(jobs.getString("job_title"));
        }

        // Exercise 2: Retrieve all job titles and store them in Arraylist then print it on the console.

    }


}
