package com.example.demo;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login {

    //password  encryption
    private static byte[] getSha(String input)
    {
        try
        {
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }
    private static String getEncryptedPassword(String password )
    {
        try
        {
            BigInteger num=new BigInteger(1,getSha(password));
            StringBuilder hexString=new StringBuilder(num.toString(16));
            return hexString.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    //customer login matching using email and password
    public static Customer customerLogin(String userEmail,String password)
    {
        String encryptedPassword=getEncryptedPassword(password);
        String query="select * from customers  where email='"+userEmail+"' and password='"+encryptedPassword+"'";
        databaseConnection dbConn=new databaseConnection();
        try
        {
            ResultSet rs=dbConn.getQueryTable(query);
            if(rs!=null&&rs.next())
            {
                return new Customer(rs.getInt("cId"), rs.getString("name"),rs.getString("email") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean customerSignup(String name ,String userEmail,String mobile,String address,String password)
    {

        try {
            String encryptedPassword=getEncryptedPassword(password);
            String query="insert into customers(name,email,mobile,address,password) values('" +
                    name+"','"+userEmail+"','"+mobile+"','"+address+"','"+encryptedPassword+"')";
            databaseConnection dbConn=new databaseConnection();
            return dbConn.orderUpdate(query);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

   /*public static void main(String[] args) {
        System.out.println(customerLogin("alewx@gmail.com","abc"));
        System.out.println(getEncryptedPassword("abc"));
    }*/
}
