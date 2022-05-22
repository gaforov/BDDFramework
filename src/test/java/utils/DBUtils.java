package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static List<Map<String, String>> mapList;


    /**
     * Method will create a connection to Database
     */
    public static void createConnection() {
        try {
            connection = DriverManager.getConnection(ConfigsUtility.getProperty("dbUrl"),
                                                     ConfigsUtility.getProperty("dbUsername"),
                                                     ConfigsUtility.getProperty("dbPassword"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, String>> storeDataFromDB(String sqlQuery) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            ResultSetMetaData metaData = resultSet.getMetaData();
            mapList = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, String> map = new LinkedHashMap<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    map.put(metaData.getColumnName(i), resultSet.getString(i));
                }
                mapList.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapList;
    }

    /**
     * This method will close the connection with Database
     */
    public static void closeResources() {
        try {
            if (resultSet != null) {
            resultSet.close();
            }
            if (statement != null) {
            statement.close();
            }
            if (connection != null) {
            connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
