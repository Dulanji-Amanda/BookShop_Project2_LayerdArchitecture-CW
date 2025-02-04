package org.example.stockverse.dao.custom;

import org.example.stockverse.dao.CrudDAO;
import org.example.stockverse.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    public boolean delete(String selectedId) throws SQLException, ClassNotFoundException ;

}
