package org.example.stockverse.dao.custom;

import org.example.stockverse.dao.CrudDAO;
import org.example.stockverse.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderItemDetailDAO extends CrudDAO<OrderDetail> {
    boolean delete(String orderItemDetailId) throws SQLException, ClassNotFoundException;
}
