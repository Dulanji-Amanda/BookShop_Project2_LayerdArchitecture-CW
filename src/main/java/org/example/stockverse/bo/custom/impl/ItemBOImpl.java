package org.example.stockverse.bo.custom.impl;

import org.example.stockverse.bo.custom.ItemBO;
import org.example.stockverse.dao.DAOFactory;
import org.example.stockverse.dao.custom.ItemDAO;
import org.example.stockverse.dto.ItemDTO;
import org.example.stockverse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    public String getNextItemId() throws SQLException, ClassNotFoundException {
        String nextId = itemDAO.getNextId();
        if (nextId != null) {
            int id = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("I%03d", id);
        } else {
            return "I001";
        }
    }

    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Item item = new Item(itemDTO.getItem_Id(), itemDTO.getItem_Name(), itemDTO.getQty(),itemDTO.getPrice());
        return itemDAO.save(item);    }

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = (ArrayList<Item>) itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItem_Id(item.getItem_Id());
            itemDTO.setItem_Name(item.getItem_Name());
            itemDTO.setQty(item.getQty());
            itemDTO.setPrice(item.getPrice());
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;       }


    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Item item = new Item(itemDTO.getItem_Id(), itemDTO.getItem_Name(), itemDTO.getQty(),itemDTO.getPrice());
        return itemDAO.update(item);     }


    public boolean deleteItem(String Item_id) throws SQLException, ClassNotFoundException {
        itemDAO.delete(Item_id);
        return false;     }


    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllIds();
    }

    public Item findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        return itemDAO.findById(selectedItemId);
    }


    public void updateItemMinus(String itemId, int qty) throws SQLException{
    }
    //public boolean saveOrderWithItems(OrderDTO orderDTO, ArrayList<ItemDTO> items, double totalPrice, String text) throws SQLException;


}
