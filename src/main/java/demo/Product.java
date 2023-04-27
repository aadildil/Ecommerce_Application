package com.example.demo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public int getId()
    {
        return id.get();
    }

    public String getName()
    {
        return name.get();
    }

    public double getPrice()
    {
        return price.get();
    }

    public Product(int i,String n,double p)
    {
        this.id=new SimpleIntegerProperty(i);
        this.name=new SimpleStringProperty(n);
        this.price=new SimpleDoubleProperty(p);

    }

    public static ObservableList<Product> getAllProducts(String t)
    {

        String allProducts="select * from products";
        if(!t.equals(""))
            allProducts="select * from products where name like '%"+t+"%'";
        return getProducts(allProducts);

    }
    public static ObservableList<Product> getProducts(String query)
    {
        databaseConnection dbConn=new databaseConnection();
        ResultSet rs=dbConn.getQueryTable(query);
        ObservableList<Product> result= FXCollections.observableArrayList();
        try
        {
            if(rs!=null)
            {
                while(rs.next())
                {
                    result.add(new Product(rs.getInt("pid")
                            ,rs.getString("name")
                            ,rs.getDouble("price")));
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;

    }
}
