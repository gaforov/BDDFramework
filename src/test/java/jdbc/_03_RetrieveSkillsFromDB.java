package jdbc;

import org.junit.Test;

import java.sql.*;

public class _03_RetrieveSkillsFromDB {
    String dbUsername = "syntax_hrm";
    String dbPassword = "syntaxhrm123";
    String dbURL ="jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";

    @Test
    public void retrieveSkillsFromHrmDB() throws SQLException {
        // 1. Connection 2. Statement, 3. ResultSet (All are Interfaces in SQL package)
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println("DB name: " + databaseMetaData.getDatabaseProductName());

        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("SELECT * FROM ohrm_skill;");

        ResultSetMetaData resultSetMetaData = query.getMetaData();
        System.out.println("Column count: " + resultSetMetaData.getColumnCount());

        int colNum = 1;
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            System.out.println("Column " + colNum + " name = " + resultSetMetaData.getColumnName(i));
            colNum++;
        }

        while (query.next()) {
            int skillId = query.getInt(1);  // can be done using query.getObject("column name") as well.
            String skillName = query.getString(2);
            String description = query.getObject("description").toString();
            System.out.println(skillId + " = " + skillName + " : " + description);
        }

        query.close();
        statement.close();
        connection.close();
    }
}
