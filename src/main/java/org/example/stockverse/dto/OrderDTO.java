package org.example.stockverse.dto;

import java.io.Serializable;

public class OrderDTO implements Serializable {
    private String Order_Id;
    private String  Description;
    private Integer Order_qty;
    private String Cust_Id;

    public OrderDTO() {
    }

    public OrderDTO(String Order_Id, String Description, Integer Order_qty,String Cust_Id) {
        this.Order_Id =Order_Id;
        this.Description =Description;
        this.Order_qty = Order_qty;
        this.Cust_Id =Cust_Id;
    }
    public String getOrder_Id() {
        return Order_Id;
    }
    public void setOrder_Id(String Order_Id) {
        this.Order_Id = Order_Id;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }
    public Integer getOrder_qty() {
        return Order_qty;
    }
    public void setOrder_qty(Integer Order_qty) {
        this.Order_qty = Order_qty;
    }
    public String getCust_Id() {
        return Cust_Id;
    }
    public void setCust_Id(String Cust_Id) {
        this.Cust_Id = Cust_Id;
    }
    @Override
    public String toString() {
            return "OrderDTO{" +
                "Order_Id='" + Order_Id + '\''+
                ",Description='" + Description +'\'' +
                ", Order_qty='" + Order_qty + '\'' +
                ", Cust_Id='" + Cust_Id + '\'' +
                '}' ;
    }
}

