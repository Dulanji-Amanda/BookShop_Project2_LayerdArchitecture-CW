package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.bo.BOFactory;
import org.example.stockverse.bo.custom.CustomerBO;
import org.example.stockverse.bo.custom.PaymentBO;
import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.ItemDAO;
import org.example.stockverse.dto.ItemDTO;
import org.example.stockverse.dto.OrderDTO;
import org.example.stockverse.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    public static PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
    public static CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

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

    @Override
    public boolean saveOrderWithItems(OrderDTO orderDTO, ArrayList<ItemDTO> items, double totalPrice, String text) throws SQLException, ClassNotFoundException {
        boolean transactionSuccess = false;

        try {
            SQLUtil.setAutoCommit(false);

            boolean orderSaved = SQLUtil.execute(
                    "INSERT INTO orders (Order_Id, Description, Order_Qty, Cust_Id) VALUES (?, ?, ?, ?)",
                    orderDTO.getOrder_Id(),
                    text,
                    orderDTO.getOrder_qty(),
                    orderDTO.getCust_Id()
            );

            boolean itemUpdated = true;
            for (ItemDTO itemDTO : items) {
                itemUpdated = SQLUtil.execute(
                        "UPDATE item SET Qty = Qty - ? WHERE Item_Id = ?",
                        itemDTO.getQty(),
                        itemDTO.getItem_Id()
                );

                if (itemUpdated) {
                    itemUpdated = SQLUtil.execute(
                            "INSERT INTO order_item_detail (Order_Id, Item_Id, Date, Amount) VALUES (?, ?, ?, ?)",
                            orderDTO.getOrder_Id(),
                            itemDTO.getItem_Id(),
                            LocalDate.now(),
                            totalPrice
                    );
                }

                if (!itemUpdated) break;
            }

            boolean paymentSaved = SQLUtil.execute(
                    "INSERT INTO payment (Payment_Id, Amount, Contact, Payment_Date, Order_Id) VALUES (?, ?, ?, ?, ?)",
                    paymentBO.getNextPaymentId(),
                    totalPrice,
                    customerBO.findCustomerContactById(orderDTO.getCust_Id()),
                    LocalDate.now(),
                    orderDTO.getOrder_Id()
            );

            if (orderSaved && itemUpdated && paymentSaved) {
                SQLUtil.commit();
                transactionSuccess = true;
            } else {
                SQLUtil.rollback();
            }
        } catch (SQLException | ClassNotFoundException e) {
            SQLUtil.rollback();
            e.printStackTrace();
        } finally {
            SQLUtil.setAutoCommit(true);
        }
        return transactionSuccess;
    }

    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Item_Id FROM item");
        ArrayList<String> itemIds = new ArrayList<>();
        while (rst.next()) {
            itemIds.add(rst.getString(1));
        }
        return itemIds;
    }

    public Item findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE Item_Id=?", selectedItemId);
        if (rst.next()) {
            return new Item(
                    rst.getString("Item_Id"),
                    rst.getString("Item_Name"),
                    rst.getInt("Qty"),
                    rst.getDouble("Price")
            );
        }
        return null;
    }

    public void updateItemMinus(String itemId, int qty) throws SQLException{

    }
    //public boolean saveOrderWithItems(OrderDTO orderDTO, ArrayList<ItemDTO> items, double totalPrice, String text) throws SQLException;

}
