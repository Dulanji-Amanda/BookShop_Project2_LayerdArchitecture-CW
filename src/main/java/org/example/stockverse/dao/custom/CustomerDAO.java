package org.example.stockverse.dao.custom;

import org.example.stockverse.dao.CrudDAO;
import org.example.stockverse.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    public boolean delete(String selectedId) throws SQLException, ClassNotFoundException ;

}
