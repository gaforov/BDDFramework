package jdbc;

import org.junit.Test;

import java.sql.*;

public class _02_MetaData {
    String dbUsername = "syntax_hrm";
    String dbPassword = "syntaxhrm123";
    String dbURL ="jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";

    // DatabaseMetaData ==> info about database such as SQL version, example: connection.getMetaData()
    // ResultSetMetaData ==> info about result/query                 example: resultSet.getMetaData()

    @Test
    public void getDbMetaData() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String productName = dbMetaData.getDatabaseProductName();
        String productVersion = dbMetaData.getDatabaseProductVersion();
        String driverName = dbMetaData.getDriverName();
        String dbUrl = dbMetaData.getURL();
        System.out.println("productName = " + productName);
        System.out.println("productVersion = " + productVersion);
        System.out.println("driverName = " + driverName);
        System.out.println("dbUrl = " + dbUrl);
    }

    @Test
    public void getRsMetaData() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ohrm_job_title");
        // find out how many columns this row has:
        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        int columnCount = rsMetaData.getColumnCount();
        System.out.println("columnCount = " + columnCount);
        // Name of 3rd column
        String columnName = rsMetaData.getColumnName(3);
        System.out.println("columnName (3rd col) = " + columnName);
        // Find out what datatype each column does accept.
        String columnTypeName = rsMetaData.getColumnTypeName(1); // first column
        System.out.println("columnTypeName (1st col) = " + columnTypeName);

        System.out.println("\n--- pull all column names of given table ---");
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(rsMetaData.getColumnName(i));
        }

        System.out.println("\n--- pull all column data-types of given table ---");
        System.out.println("--------------");
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(rsMetaData.getColumnTypeName(i));
        }

    }

}
