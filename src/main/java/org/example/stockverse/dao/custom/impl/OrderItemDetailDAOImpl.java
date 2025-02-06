package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.OrderItemDetailDAO;
import org.example.stockverse.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDetailDAOImpl implements OrderItemDetailDAO {
    @Override
    public boolean save(OrderDetail DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO order_item_detail (Order_Id, Item_Id, Date, Amount) VALUES (?,?,?,?)",
                DTO.getOrderId(), DTO.getItemId(), DTO.getDate(), DTO.getAmount());
    }

    @Override
    public boolean update(OrderDetail DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE order_item_detail SET Date=?, Amount=? WHERE Order_Id=? AND Item_Id=?",
                DTO.getDate(), DTO.getAmount(), DTO.getOrderId(), DTO.getItemId());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Order_Id FROM order_item_detail ORDER BY Order_Id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("Order_Id");
        }
        return null;
    }

    @Override
    public List<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM order_item_detail");
        List<OrderDetail> orderDetails = new ArrayList<>();
        while (rst.next()) {
            OrderDetail orderDetail = new OrderDetail(
                    rst.getString("Order_Id"),
                    rst.getString("Item_Id"),
                    rst.getDate("Date").toLocalDate(),
                    rst.getDouble("Amount")
            );
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Order_Id FROM order_item_detail");
        ArrayList<String> orderItemDetailIds = new ArrayList<>();
        while (rst.next()) {
            orderItemDetailIds.add(rst.getString("Order_Id"));
        }
        return orderItemDetailIds;
    }

    @Override
    public OrderDetail findById(String selectedId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM order_item_detail WHERE Order_Id=?", selectedId);
        if (rst.next()) {
            return new OrderDetail(
                    rst.getString("Order_Id"),
                    rst.getString("Item_Id"),
                    rst.getDate("Date").toLocalDate(),
                    rst.getDouble("Amount")
            );
        }
        return null;
    }

    @Override
    public boolean delete(String orderItemDetailId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM order_item_detail WHERE Order_Id=?", orderItemDetailId);
    }
}