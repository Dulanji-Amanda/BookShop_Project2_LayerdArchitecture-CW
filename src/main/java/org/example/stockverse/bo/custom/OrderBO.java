package org.example.stockverse.bo.custom;

import org.example.stockverse.bo.SuperBO;
import org.example.stockverse.dto.OrderDTO;
import org.example.stockverse.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    public String getNextOrderId() throws SQLException,ClassNotFoundException;

    public boolean saveOrder(OrderDTO orderDTO) throws SQLException,ClassNotFoundException;

    public ArrayList<OrderDTO> getAllOrders() throws SQLException,ClassNotFoundException;

    public boolean updateOrder(OrderDTO orderDTO) throws SQLException,ClassNotFoundException;


    public boolean deleteOrder(String order_id) throws SQLException,ClassNotFoundException;
    public ArrayList<String> getAllOrderIds() throws SQLException,ClassNotFoundException;

    public Order findById(String orderId) throws SQLException,ClassNotFoundException;
}
