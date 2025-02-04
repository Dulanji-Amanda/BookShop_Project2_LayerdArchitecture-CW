package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.ItemDAO;
import org.example.stockverse.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    public String getNextId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT Item_Id FROM item ORDER BY Item_Id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("Item_Id");
        }
        return null;    }

    public boolean save(Item itemDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item VALUES (?,?,?,?)", itemDTO.getItem_Id(), itemDTO.getItem_Name(), itemDTO.getQty(),itemDTO.getPrice());
    }

    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item");

        ArrayList<Item> itemDTOS = new ArrayList<>();

        while (rst.next()) {
            Item item = new Item(
                    rst.getString(1),  // item ID
                    rst.getString(2),  // Name
                    rst.getInt(3),  // qty
                    rst.getDouble(4)
            );
            itemDTOS.add(item);
        }
        return itemDTOS;     }


    public boolean update(Item itemDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET Item_Name=?, Qty=? , Price=? WHERE Item_Id=?", itemDTO.getItem_Name(), itemDTO.getQty(), itemDTO.getPrice(), itemDTO.getItem_Id());
    }


    public boolean delete(String item_id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM item WHERE Item_Id=?", item_id );
    }


    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Item_Id FROM item");

        ArrayList<String> itemIds = new ArrayList<>();

        while (rst.next()) {
            itemIds.add(rst.getString("Item_Id"));
        }
        return itemIds;    }

    public Item findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE Item_Id=?", selectedItemId);

        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            Item item = new Item(
                    rst.getString("Item_Id"),  // item ID
                    rst.getString("Item_Name"),  // Name
                    rst.getInt("Qty") , // qty
                    rst.getDouble("Price")
            );
            items.add(item);
        }
        return items.get(0);
    }


    public void updateItemMinus(String itemId, int qty) throws SQLException{

    }
    //public boolean saveOrderWithItems(OrderDTO orderDTO, ArrayList<ItemDTO> items, double totalPrice, String text) throws SQLException;

}
