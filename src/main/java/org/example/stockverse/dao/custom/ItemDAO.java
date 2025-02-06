package org.example.stockverse.dao.custom;

import org.example.stockverse.dao.CrudDAO;
import org.example.stockverse.dto.ItemDTO;
import org.example.stockverse.dto.OrderDTO;
import org.example.stockverse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {
    public boolean delete(String selectedId) throws SQLException, ClassNotFoundException ;

    boolean saveOrderWithItems(OrderDTO orderDTO, ArrayList<ItemDTO> items, double totalPrice, String text) throws ClassNotFoundException, SQLException;
}
