package org.example.stockverse.entity;

import java.io.Serializable;

public class Item implements Serializable {
    private String Item_Id;
    private String Item_Name;
    private Integer Qty;
    private Double Price;

    public Item() {
    }

    public Item(String Item_Id, String Item_Name, Integer Qty,Double Price) {
        this.Item_Id = Item_Id;
        this.Item_Name = Item_Name;
        this.Qty = Qty;
        this.Price = Price;
    }

    public String getItem_Id() {
        return Item_Id;
    }
    public void setItem_Id(String Item_Id) {
        this.Item_Id = Item_Id;
    }
    public String getItem_Name() {
        return Item_Name;
    }
    public void setItem_Name(String Item_Name) {
        this.Item_Name = Item_Name;
    }
    public Integer getQty() {
        return Qty;
    }
    public void setQty(Integer Qty) {
        this.Qty = Qty;
    }
    public Double getPrice() {
        return Price;
    }
    public void setPrice(Double Price) {
        this.Price = Price;
    }
    @Override
    public String toString() {
        return "ItemDTO{" +
                "Item_Id='" + Item_Id + '\''+
                ", Item_Name='" + Item_Name +'\'' +
                ", Qty='" + Qty + '\'' +
                ",Price='" + Price + '\'' +

                '}' ;
    }
}
