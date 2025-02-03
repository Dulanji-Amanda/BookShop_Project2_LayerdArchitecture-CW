package org.example.stockverse.entity;


import java.io.Serializable;

public class Customer implements Serializable {
    private String Cust_Id;
    private String Cust_Name;
    private Integer Contact;
    private String Address;
    private String User_Id;

    public Customer() {
    }

    public Customer(String Cust_Id, String Cust_Name, Integer Contact, String Address,String User_Id) {
        this.Cust_Id = Cust_Id;
        this.Cust_Name = Cust_Name;
        this.Contact = Contact;
        this.Address = Address;
        this.User_Id =User_Id;
    }

    public String getCust_Id() {
        return Cust_Id;
    }
    public void setCust_Id(String Cust_Id) {
        this.Cust_Id = Cust_Id;
    }
    public String getCust_Name() {
        return Cust_Name;
    }
    public void setCust_Name(String Cust_Name) {
        this.Cust_Name = Cust_Name;
    }
    public Integer getContact() {
        return Contact;
    }
    public void setContact(Integer Contact) {
        this.Contact = Contact;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getUser_Id() {
        return User_Id;
    }
    public void setUser_Id(String User_Id) {
        this.User_Id = User_Id;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "Cust_Id='" + Cust_Id + '\''+
                ", Cust_Name='" + Cust_Name +'\'' +
                ", Contact='" + Contact + '\'' +
                ",Address='" + Address + '\'' +
                ", User_Id='" + User_Id + '\'' +
                '}' ;
    }
}
