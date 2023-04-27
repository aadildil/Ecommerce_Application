package com.example.demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.productList.productTable;

public class EComm extends Application {


    Pane bodyPane;
    private final int width=800,height=600,headerLine=50;

    float total=0;
    Label totalLabel;


    Button signOutButton=new Button("Sign out");
    Button signInButton=new Button("Sign in");
    Button deleteButton=new Button("Delete Item");
    Button buyNowButton;
    Button placeOrderButton;
    Button addToCartButton;
    boolean isBuyNow =false;
    Label customerLabel=new Label("Guest");

    ObservableList<Product> cartList= FXCollections.observableArrayList();


    Customer loggedInCustomer;



    private void addItemToCart(Product product)
    {
        if(!cartList.contains(product)) {
            cartList.add(product);
        }
    }

    private void showDialogue(String message)
    {
        Dialog<String> dialog=new Dialog<>();
        dialog.setTitle(" Order Status");

        ButtonType type=new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(message);
       // ButtonType nextOrder=new ButtonType("Shop Again",ButtonBar.ButtonData.OTHER);

        dialog.getDialogPane().getButtonTypes().add(type);

        dialog.showAndWait();
    }

    private GridPane loginPage()
    {
        Label userLabel=new Label("User name or email");
        TextField userName =new TextField();
        userName.setPromptText("Enter user name");

        Label passwordLabel=new Label("Password");
        PasswordField userPassword =new PasswordField();
        userPassword.setPromptText("Enter Password");

        Label messageLabel =new Label(" ");

        Button loginButton=new Button("Login");


        // for log in existing customer
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String User=userName.getText();
                String pass=userPassword.getText();
                loggedInCustomer=login.customerLogin(User,pass);
                //  if(login.customerLogin(User,pass)!=null) {
                if(loggedInCustomer!=null){
                    messageLabel.setText("Login Successful");
                    messageLabel.setTextFill(Color.GREEN);
                    customerLabel.setText("Welcome "+loggedInCustomer.getName());
                    customerLabel.setTextFill(Color.GREEN);
                    signOutButton.setVisible(true);
                    signInButton.setVisible(false);
                    bodyPane.getChildren().clear();
                    Pane list=productList.getAllProducts("");
                    list.setTranslateX(100);
                    bodyPane.getChildren().addAll(list,footerBar());
                    isBuyNow=true;

                }
                else {
                    messageLabel.setText("Login Failed!!!");
                    messageLabel.setTextFill(Color.RED);
                }
            }
        });

        Button createButton=new Button("Create new account");

        //for create new account-page
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signUpFunction());
            }
        });

        Label createLabel=new Label("New User?");
        //createLabel.setTextFill(Color.GREEN);

        createLabel.setTranslateX(55);


        GridPane loginPane=new GridPane();

        loginPane.add(userLabel,0,0);
        loginPane.add(passwordLabel,0,1);

        loginPane.add(userName,1,0);
        loginPane.add(userPassword,1,1);

        loginPane.add(loginButton,1,2);
        loginPane.add(messageLabel,2,2);
        loginPane.add(createLabel,0,3);
        loginPane.add(createButton,1,3);



        loginPane.setTranslateY(150);
        loginPane.setTranslateX(80);
        loginPane.setHgap(15);
        loginPane.setVgap(10);

        return loginPane;



    }

    private GridPane signUpFunction()
    {
        Label newUserLabel=new Label(" Name ");
        TextField newUserName =new TextField();
        newUserName.setPromptText("Enter name");

        Label emailLabel=new Label("Email");
        TextField userEmail =new TextField();
        userEmail.setPromptText("Enter email");

        Label mobileLabel=new Label("Mobile number");
        TextField userMobile =new TextField();
        userMobile.setPromptText("10 digit mobile number");

        Label adressLabel=new Label("Address");
        TextField userAddress =new TextField();
        userAddress.setPromptText("Enter Address");


        Label newPasswordLabel=new Label("Password");
        PasswordField newUserPassword =new PasswordField();
        newUserPassword.setPromptText("Enter Password");

        Label signStatus=new Label(" ");
        Button signUpButton=new Button("Create account");


        //for creating new account
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=newUserName.getText();
                String email=userEmail.getText();
                String mobile=userMobile.getText();
                String address=userAddress.getText();
                String pass=newUserPassword.getText();

                if(mobile.length()!=10)
                {
                    signStatus.setText("Enter 10 digit mobile number!!!");
                    signStatus.setTextFill(Color.RED);
                }
                else if(name.isEmpty())
                {
                    signStatus.setText("Enter name!!!");
                    signStatus.setTextFill(Color.RED);
                }
                else if(email.isEmpty())
                {
                    signStatus.setText("Enter Email");
                    signStatus.setTextFill(Color.RED);
                }
                else if(address.isEmpty())
                {
                    signStatus.setText("Enter address!!!");
                    signStatus.setTextFill(Color.RED);
                }
                else if(pass.isEmpty())
                {
                    signStatus.setText("Enter password!!!");
                    signStatus.setTextFill(Color.RED);
                }
                else if(login.customerSignup(name,email,mobile,address,pass)) {
                    loggedInCustomer = login.customerLogin(email, pass);
                    signStatus.setText("created account");
                    signStatus.setTextFill(Color.GREEN);
                    customerLabel.setText("Welcome "+loggedInCustomer.getName());
                    signOutButton.setVisible(true);
                    signInButton.setVisible(false);

                }
                else
                {
                    signStatus.setText("Signup Failed!!!");
                    signStatus.setTextFill(Color.RED);
                }
            }
        });



        GridPane signUpPane=new GridPane();

        signUpPane.add(newUserLabel,0,0);
        signUpPane.add(newUserName,1,0);

        signUpPane.add(emailLabel,0,1);
        signUpPane.add(userEmail,1,1);

        signUpPane.add(mobileLabel,0,2);
        signUpPane.add(userMobile,1,2);

        signUpPane.add(adressLabel,0,3);
        signUpPane.add(userAddress,1,3);

        signUpPane.add(newPasswordLabel,0,4);
        signUpPane.add(newUserPassword,1,4);
        signUpPane.add(signUpButton,1,7);
        signUpPane.add(signStatus,2,7);
        signUpPane.setVgap(10);
        signUpPane.setTranslateX(100);
        signUpPane.setTranslateY(40);



        return signUpPane;
    }
    private GridPane headerBar()
    {
        GridPane header=new GridPane();

        header.setHgap(10);






        Button searchButton=new Button("Search");
        TextField search=new TextField();
        Button cartButton=new Button("Goto cart");
        Button orders=new Button("Ordered items");


        header.add(search,0,0);
        header.add(searchButton,1,0);

        header.add(signOutButton,2,0);
        signOutButton.setVisible(false);
        header.add(signInButton,2,0);
        signInButton.setVisible(false);
        header.add(orders,4,0);
        customerLabel.setTranslateX(200);
        header.add(customerLabel,3,0);
        header.add(cartButton,4,0);
        cartButton.setTranslateX(230);

        header.setTranslateY(10);
        header.setTranslateX(100);



        // for viewing products
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();

               String t=search.getText();
                Pane list=productList.getAllProducts(t);
                list.setTranslateX(100);
                bodyPane.getChildren().addAll(list,footerBar());
                isBuyNow =true;
                if(loggedInCustomer==null) {
                    signInButton.setVisible(true);
                    signOutButton.setVisible(false);
                }
                else
                {
                    signInButton.setVisible(false);
                    signOutButton.setVisible(true);
                }
            }
        });

        // for signing out
        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                customerLabel.setText("Guest");
                customerLabel.setTextFill(Color.BLACK);
                loggedInCustomer=null;
                signInButton.setVisible(true);
                signOutButton.setVisible(false);
                bodyPane.getChildren().clear();
                total=0;
            }
        });

        // signing in page
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                signOutButton.setVisible(false);

            }
        });


        // Goto cart button
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                Pane list=productList.showCart(cartList);
                list.setTranslateX(100);
                bodyPane.getChildren().addAll(list,footerBar());
                placeOrderButton.setVisible(true);
                totalLabel.setVisible(true);
                buyNowButton.setVisible(false);
                addToCartButton.setVisible(false);
                deleteButton.setVisible(true);

            }
        });

        // to view orders
        orders.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedInCustomer!=null) {
                    bodyPane.getChildren().clear();

                    Pane orderList = OrderedItems.createOrderTable(loggedInCustomer);
                    bodyPane.getChildren().clear();
                    orderList.setTranslateX(100);
                    bodyPane.getChildren().add(orderList);
                }
                else
                {
                    showDialogue("Log in to see orders");
                }
            }
        });

        return header;
    }
    private GridPane footerBar()
    {
        buyNowButton=new Button("Buy now");
        addToCartButton=new Button("Add to cart");
        placeOrderButton=new Button("Place order");
        totalLabel=new Label("Total amount : "+String.valueOf(total));

        GridPane footer=new GridPane();
        footer.setTranslateY(height-150);
        footer.add(buyNowButton,0,0);
        footer.add(addToCartButton,1,0);
        footer.add(placeOrderButton,2,0);
        footer.add(deleteButton,2,0);
        deleteButton.setVisible(false);
        footer.add(totalLabel,3,1);
        placeOrderButton.setVisible(false);
        totalLabel.setVisible(false);
        buyNowButton.setTranslateX(100);
        addToCartButton.setTranslateX(100);
        placeOrderButton.setTranslateX(100);
        //totalLabel.setTranslateX(50);
        footer.setHgap(10);
        footer.setVgap(10);

        // for deleting items from the cart
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product p= productTable.getSelectionModel().getSelectedItem();
                if(p!=null) {
                    productTable.getItems().remove(p);
                    total-=p.getPrice();
                    totalLabel.setText("Total amount : "+String.valueOf(total));
                }
            }
        });

        // for adding items into the cart
        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedInCustomer!=null) {
                    Product product = productList.getSelectedProduct();
                    addItemToCart(product);
                    total+=(float)product.getPrice();
                }
                else
                {
                    showDialogue("Please log in to continue");
                }
            }
        });

        // for instant buy
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if (product!=null) {

                    boolean orderStatus = false;

                    if ( loggedInCustomer != null) {

                        orderStatus = order.placeOrder(loggedInCustomer, product);
                    }
                    else {
                        showDialogue("log in to continue");
                    }

                    if (orderStatus) {
                        showDialogue("Order Successful");
                    }


                }
                else
                {
                    showDialogue("Choose product");
                }
            }
        });

        // for buying cart products
        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                int orderCount=0;
                if(cartList.isEmpty()) {
                    showDialogue("Cart is empty");
                } else if (loggedInCustomer==null) {
                    showDialogue("Please log in to place order");
                }
                else {
                    orderCount=order.placeBulkOrder(cartList,loggedInCustomer);
                }
                if(orderCount>0) {
                    showDialogue("Successfully placed "+orderCount+" orders");
                    totalLabel.setText("order placed\norder amount:"+String.valueOf(total));
                    totalLabel.setTextFill(Color.GREEN);
                    cartList=null;
                    bodyPane.getChildren().clear();
                    total=0;
                   /* Pane list=productList.showCart(cartList);
                    list.setTranslateX(100);
                    bodyPane.getChildren().addAll(list,footerBar());*/
                }



            }
        });

        return footer;



    }






    public Pane createContent()
    {

        //mane scene
        Pane root=new Pane();
        root.setPrefSize(width,height+2*headerLine);

        bodyPane=new Pane();
        bodyPane.setPrefSize(width,height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.setTranslateX(10);



      bodyPane.getChildren().add(loginPage());


        root.getChildren().addAll(headerBar(),bodyPane);

        return root;

    }
    @Override
    public void start(Stage stage) throws IOException {


        Scene scene=new Scene(createContent());
        stage.setTitle("Vkart");
        Image icon=new Image("C:\\Users\\shabaka computers\\ecl\\demo\\src\\icon.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {

        launch();
    }
}