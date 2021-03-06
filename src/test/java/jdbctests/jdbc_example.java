package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {

    String dbUrl = "jdbc:oracle:thin:@54.236.249.191:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        //create a connection to database
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create a statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //how to get how many row we have foe the query
        // go to last row
        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        resultSet.beforeFirst();
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        System.out.println("******REGIONS******");
        resultSet= statement.executeQuery("select * from regions");
        resultSet.beforeFirst();
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void metaData() throws SQLException {
        //create a connection to database
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create a statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();
        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //How many columns we have?
        int colCount = rsMetadata.getColumnCount();
        System.out.println("colCount = " + colCount);

        //get column names
        String colName = rsMetadata.getColumnName(2);
        System.out.println(colName);
        System.out.println(rsMetadata.getColumnName(1));

        System.out.println("*****GET ALL COLUMN NAMES DYNAMICALLY*****");
        //print all the column names dynamically
       for (int i=1;i<=colCount;i++){
           System.out.println(rsMetadata.getColumnName(i));
       }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
