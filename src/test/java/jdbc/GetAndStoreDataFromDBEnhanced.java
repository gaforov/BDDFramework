package jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetAndStoreDataFromDBEnhanced {
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
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        List<Map<String, String>> mapList = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, String> map = new LinkedHashMap<>();
            for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                map.put(rsMetaData.getColumnName(i), resultSet.getString(i));
                // or maybe I can print it right here using lambda expression forEach loop
//                map.forEach((k,v) -> System.out.println(k + ": " + v));
//                System.out.println("--------------");
            }
            mapList.add(map);

            for (String key : map.keySet()) {
                String val = map.get(key);
                System.out.println(key + " : " + val);
            }
            System.out.println("---------------------");

//            for (Map<String, String> stringMap : mapList) {
//                stringMap.forEach((k,v) -> System.out.println(k + " : " + v));
//                System.out.println("---------------------");
//            }

        }

//        mapList.forEach(System.out::println);

    }
}
