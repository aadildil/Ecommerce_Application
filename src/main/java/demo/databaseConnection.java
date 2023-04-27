package com.example.demo;
import java.sql.*;

public class databaseConnection {
    String dbURL="jdbc:mysql://localhost:3306/ecomm";

    String userName="root";
    String password="69";


     private Statement getStatement() {
        try
        {


            Connection conn= DriverManager.getConnection(dbURL,userName,password);
            return conn.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();

         }
        return null;
    }
    public ResultSet getQueryTable(String query)
    {
        Statement statement=getStatement();
        try {
            return statement.executeQuery(query);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public boolean orderUpdate(String query)
    {
        Statement statement=getStatement();
        try {
            statement.executeUpdate(query);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String query="select * from products";
        databaseConnection dbConn=new databaseConnection();
        ResultSet rs= dbConn.getQueryTable(query);
        if(rs!=null)
            System.out.println("connected to database");


    }
}
