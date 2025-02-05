package org.example.stockverse.dao.custom;

import org.example.stockverse.dao.CrudDAO;
import org.example.stockverse.entity.Stock;

import java.sql.SQLException;

public interface StockDAO extends CrudDAO<Stock> {
    public boolean delete(String Stock_Id) throws SQLException, ClassNotFoundException;

    }
