package jdbcPractice;

import org.junit.Test;

import java.sql.*;

public class _03_MetaData {
    /* dbMetaData vs rsMetaData
        1. DatabaseMetaData
          - Returns information about database such as MySQL version (v 5.7), Driver version, DB URL, etc.
          - Returns DB technicality, anything related to DB
          - Information about Database currently in use
        2. ResultSetMetaData
          - This also returns information, but not about DB, but about database-table such as number of columns,
          - names of the columns, header/meta information about tables. column, column names, total no of cols.
          - returns column data-types such as if column is String or Integer.
     */
    String dbUsername ="syntax_hrm";
    String dbPassword = "syntaxhrm123";
    // connector type : type of database//dbUrl:port/databaseName(endpoint)
    String dbUrl = "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";

    @Test
    public void getDdMetaData() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        DatabaseMetaData dbMetaData = connection.getMetaData();

        String databaseProductName = dbMetaData.getDatabaseProductName();
        System.out.println("databaseProductName = " + databaseProductName);
        String version = dbMetaData.getDatabaseProductVersion();
        System.out.println("DB version = " + version);
        String driverName = dbMetaData.getDriverName();
        String url = dbMetaData.getURL();
        System.out.println("driverName = " + driverName);
        System.out.println("url = " + url);
    }

    @Test
    public void getRsMetaData() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("SELECT * FROM ohrm_job_title;");

        // find out how many columns this row has:
        ResultSetMetaData rsMetaData = query.getMetaData();
        int totalColumns = rsMetaData.getColumnCount();
        System.out.println("totalColumns = " + totalColumns);

        // Retrieve name of the 3rd column from the existing table/query:
        String columnName = rsMetaData.getColumnName(3);
        System.out.println("columnName = " + columnName);
    }
}
