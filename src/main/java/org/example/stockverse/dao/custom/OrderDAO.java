package org.example.stockverse.dao.custom;

import org.example.stockverse.dao.CrudDAO;
import org.example.stockverse.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    public boolean delete(String selectedId) throws SQLException, ClassNotFoundException ;

}
