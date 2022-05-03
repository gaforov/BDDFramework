package jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.Iterator;

public class JDBC {
    String dbUsername = "syntax_hrm";
    String dbPassword = "syntaxhrm123";
    // jdbc:driver-type://hostname:port/name of the database
    String dbURL ="jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";


    @Test
    public void testDb() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        System.out.println("DB connection is successful");
        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("SELECT * FROM ohrm_nationality");

//        query.next(); // doesn't work without next
//        String name = query.getString("name");
//        System.out.println(name);

        System.out.println("-------------");
        while (query.next()) {
            String nationality = query.getString("name");
            System.out.println(nationality);
        }

        query.close();
        statement.close();
        connection.close();

    }

}
