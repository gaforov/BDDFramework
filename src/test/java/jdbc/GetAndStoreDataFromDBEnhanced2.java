package jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetAndStoreDataFromDBEnhanced2 {
    String dbUsername = "syntax_hrm";
    String dbPassword = "syntaxhrm123";
    String dbURL ="jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";

    @Test
    public void getAndStoreDataEnhanced() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("""
                SELECT
                    emp_firstname,
                    emp_lastname,
                    emp_birthday,
                    employee_id
                FROM hs_hr_employees
                WHERE emp_birthday IS NOT NULL
                """);
        ResultSetMetaData metaData = resultSet.getMetaData();

        Map<String, String> map;
        while (resultSet.next()) {
            map = new LinkedHashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                map.put(metaData.getColumnName(i), resultSet.getString(i));
            }
                map.forEach((k,v) -> System.out.println(k + ": " + v));
                System.out.println("--------------");
        }

    }
}
