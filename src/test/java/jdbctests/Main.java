package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@54.236.249.191:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //sample query string to call
        //String query1 = "select first_name, last_name, salary from employees where salary>15000";

        //create a connection to database
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create a statement
        Statement statement = connection.createStatement();

        // run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        /*
        //move pointer to first row
        resultSet.next();

        //getting information with column name
        System.out.println(resultSet.getString("region_name"));
        //getting information with column index number(starts from 1)
        System.out.println(resultSet.getString(2));

        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        //move pointer to second row
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        //move pointer to third row
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
         */

        while (resultSet.next()){
            System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2)+ " - " + resultSet.getString(3)+ " - " + resultSet.getString(4));
        }
        //close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}
