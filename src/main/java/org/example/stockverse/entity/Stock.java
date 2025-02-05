package org.example.stockverse.entity;

import java.io.Serializable;

public class Stock implements Serializable {
        private String Stock_Id;
        private String Name;
        private String User_Id;

        public Stock(String Stock_Id, String Name,String User_Id) {
            this.Stock_Id = Stock_Id;
            this.Name =Name;
            this.User_Id =User_Id;
        }
        public String getStock_Id() {
            return Stock_Id;
        }
        public void setStock_Id(String Stock_Id) {
            this.Stock_Id = Stock_Id;
        }
        public String getName() {
            return Name;
        }
        public void setName(String Name) {
            this.Name = Name;
        }
        public String getUser_Id() {
            return User_Id;
        }
        public void setUser_Id(String User_Id) {
            this.User_Id = User_Id;
        }

        @Override
        public String toString() {
            return "StockDTO{" +
                    "Stock_Id='" + Stock_Id + '\''+
                    ",Name='" + Name +'\'' +
                    ", User_Id='" + User_Id + '\'' +
                    '}' ;
        }
    }

