package jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.*;

public class GetAndStoreDataFromDB {
    String dbUsername = "syntax_hrm";
    String dbPassword = "syntaxhrm123";
    String dbURL ="jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";

    @Test
    public void getAndStoreData() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM hs_hr_employees WHERE emp_birthday IS NOT NULL");

        List<Map<String, String>> mapList = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("employee_id", resultSet.getObject("employee_id").toString());
            map.put("emp_firstname", resultSet.getObject("emp_firstname").toString());
            map.put("emp_lastname", resultSet.getObject("emp_lastname").toString());
            map.put("emp_birthday", resultSet.getObject("emp_birthday").toString());

            mapList.add(map);

            // if we want it in better pair format:
            for (String key : map.keySet()) {
                String val = map.get(key);
                System.out.println(key + " = " + val);
            }
            System.out.println("--------------------");
        }

        //System.out.println(mapList);
        //System.out.println(List.of(mapList)); // same as above code

        // This way we can print each row on new line:
//        for (Map<String, String> list : mapList) {
//            System.out.println(list);
//        }

        // lambda way
//        mapList.forEach(l -> System.out.println(l));


        // Java 8 and after can even do this in shorter way (BEST WAY)
        mapList.forEach(System.out::println);


    }
}
