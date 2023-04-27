package com.example.demo;

public class Customer {

    int cId;
    String name;
    String email;

    public Customer(int cId, String name, String email) {
        this.cId = cId;
        this.name = name;
        this.email = email;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
