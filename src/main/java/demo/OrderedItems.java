package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.sql.ResultSet;

public class OrderedItems {

    int orderId;

    String productName;

    String price;

    String date;
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public OrderedItems(int orderId, String productName, String price, String date) {
        this.orderId = orderId;
        this.productName = productName;
        this.price = price;
        this.date = date;
    }

   public static Pane createOrderTable(Customer customer)
   {
       TableColumn id=new TableColumn("Id");
       id.setCellValueFactory(new PropertyValueFactory<>("orderId"));

       TableColumn name=new TableColumn("Name");
       name.setCellValueFactory(new PropertyValueFactory<>("productName"));

       TableColumn price=new TableColumn("Price");
       price.setCellValueFactory(new PropertyValueFactory<>("price"));

       TableColumn orderDate=new TableColumn("Order date");
       orderDate.setCellValueFactory(new PropertyValueFactory<>("date"));


       ObservableList<OrderedItems> orderedList=getOrderTable(customer);
       TableView<OrderedItems> orderTable =new TableView<>();
       orderTable.setItems(orderedList);
       orderTable.getColumns().addAll(id,name,price,orderDate);
       orderTable.setPlaceholder(new Label("No orders yet"));
       Pane tablePane=new Pane();
       tablePane.getChildren().add(orderTable);

       return tablePane;
   }


   public static ObservableList<OrderedItems>  getOrderTable(Customer customer)
   {
       String query="select orders.oid,products.name,products.price,orders.orderDate from orders  inner join  customers on orders.customerId=customers.cid inner join  products on orders.productId=products.pid where customers.cid="+customer.getcId();
       databaseConnection db=new databaseConnection();
       ResultSet rs=db.getQueryTable(query);
       ObservableList<OrderedItems> result= FXCollections.observableArrayList();
       try
       {
           if(rs!=null)
           {
               while(rs.next())
               {
                   result.add(new OrderedItems(rs.getInt("oid")
                           ,rs.getString("name")
                           ,rs.getString("price")
                           ,rs.getString("orderDate")));
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
