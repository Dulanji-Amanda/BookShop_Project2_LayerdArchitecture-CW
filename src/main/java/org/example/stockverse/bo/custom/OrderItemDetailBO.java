package org.example.stockverse.bo.custom;

import org.example.stockverse.bo.SuperBO;
import org.example.stockverse.dto.OrderDetailDTO;
import org.example.stockverse.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderItemDetailBO extends SuperBO {
    String getNextOrderItemDetailId() throws SQLException, ClassNotFoundException;
    boolean saveOrderItemDetail(OrderDetailDTO OrderDetailDTO) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetailDTO> getAllOrderItemDetails() throws SQLException, ClassNotFoundException;
    boolean updateOrderItemDetail(OrderDetailDTO OrderDetailDTO) throws SQLException, ClassNotFoundException;
    boolean deleteOrderItemDetail(String orderItemDetailId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllOrderItemDetailIds() throws SQLException, ClassNotFoundException;
    OrderDetail findById(String selectedOrderItemDetailId) throws SQLException, ClassNotFoundException;
}