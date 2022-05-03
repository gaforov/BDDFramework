package jdbc;

import org.junit.Test;

import java.sql.*;

public class MetaData {
    String dbUsername = "syntax_hrm";
    String dbPassword = "syntaxhrm123";
    String dbURL ="jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";

    // DatabaseMetaData ==> info about database such as SQL version, example: connection.getMetaData()
    // ResultSetMetaData ==> info about result/query                 example: resultSet.getMetaData()

    @Test
    public void getMetaData() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        DatabaseMetaData data = connection.getMetaData();
        String productName = data.getDatabaseProductName();
        String productVersion = data.getDatabaseProductVersion();
        System.out.println("productName = " + productName);
        System.out.println("productVersion = " + productVersion);


        // Select a row in database
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("""
                        SELECT * FROM hs_hr_employees
                        WHERE emp_number = '30088A';
                        """);

        // find out how many columns this row has:
        ResultSetMetaData metaData = resultSet.getMetaData();

        int columnCount = metaData.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        String columnName = metaData.getColumnName(3);
        System.out.println("columnName = " + columnName);

        System.out.println("pull all column names of given table");
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(metaData.getColumnName(i));
        }

        // pull all column names via loops

    }

}
