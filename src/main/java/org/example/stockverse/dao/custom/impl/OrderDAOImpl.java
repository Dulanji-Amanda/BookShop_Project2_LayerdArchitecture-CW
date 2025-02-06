package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.OrderDAO;
import org.example.stockverse.dto.OrderDTO;
import org.example.stockverse.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean save(Order DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES (?,?,?,?)", DTO.getOrder_Id(), DTO.getDescription(), DTO.getOrder_qty(),DTO.getCust_Id());
    }

    @Override
    public boolean update(Order DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE orders SET Description=?, Order_qty=? , Cust_Id=? WHERE Order_Id=?", DTO.getDescription(), DTO.getOrder_qty(),DTO.getCust_Id(), DTO.getOrder_Id());
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Order_Id FROM order ORDER BY Order_Id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("Order_Id");
        }
        return null;
    }

    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders");

        ArrayList<Order> ordersDTOS = new ArrayList<>();

        while (rst.next()) {
            Order order = new Order(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4)
            );
            ordersDTOS.add(order);
        }
        return ordersDTOS;
    }



    public boolean delete(String Order_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM orders WHERE Order_Id=?",Order_id);

    }

    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Order_Id FROM orders");

        ArrayList<String> orderIds = new ArrayList<>();

        while (rst.next()) {
            orderIds.add(rst.getString("Order_Id"));
        }
        return orderIds;
    }

    public Order findById(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders WHERE Order_Id=?", orderId);

        ArrayList<Order> orders = new ArrayList<>();
        while (rst.next()) {
            Order order = new Order(
                    rst.getString(" Order_Id"),
                    rst.getString("Description"),
                    rst.getInt("Order_qty") ,
                    rst.getString("Cust_Id")
            );
            orders.add(order);
        }
        return orders.get(0);
    }
}

