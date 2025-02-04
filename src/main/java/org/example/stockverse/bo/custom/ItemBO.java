package org.example.stockverse.bo.custom;

import org.example.stockverse.bo.SuperBO;
import org.example.stockverse.dto.ItemDTO;
import org.example.stockverse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public String getNextItemId() throws SQLException, ClassNotFoundException;

    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(String item_id) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;

    public Item findById(String selectedItemId) throws SQLException, ClassNotFoundException;

    public void updateItemMinus(String itemId, int qty) throws SQLException, ClassNotFoundException;

    //public boolean saveOrderWithItems(OrderDTO orderDTO, ArrayList<ItemDTO> items, double totalPrice, String text) throws SQLException;
}

