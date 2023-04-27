package com.example.demo;

import javafx.collections.ObservableList;

public  class order {

    public static boolean placeOrder(Customer customer,Product product)
    {
        try {
            String query="insert into orders(customerId,productId,quantity,status) values ("+customer.getcId()+","+product.getId()+",1,'ordered')";
            databaseConnection dbConn=new databaseConnection();

            return dbConn.orderUpdate(query);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static int placeBulkOrder(ObservableList<Product> productsList,Customer customer)
    {
        int count=0;
        for(Product product:productsList)
        {
            if(placeOrder(customer,product))
                count++;
        }
        return count;


    }
}
