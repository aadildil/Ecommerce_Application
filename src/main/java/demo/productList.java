package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class productList {
    public static TableView<Product> productTable;

    public  static Pane getAllProducts(String t)
    {

        ObservableList<Product> productsList= Product.getAllProducts(t);
        return  createCartTable(productsList);
    }
    public static Pane createCartTable(ObservableList<Product> productsList)
    {
        TableColumn id=new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name=new TableColumn("Product");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price=new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable=new TableView<>();
        productTable.setItems(productsList);
        productTable.getColumns().addAll(id,name,price);
        productTable.setPlaceholder(new Label("No items in the cart"));
        Pane tablePane=new Pane();
        tablePane.getChildren().add(productTable);

        return  tablePane;
    }

    public static Pane showCart(ObservableList<Product> productsList)
    {
        return createCartTable(productsList);
    }
    public static Product getSelectedProduct()
    {

        return productTable.getSelectionModel().getSelectedItem();
    }
}
