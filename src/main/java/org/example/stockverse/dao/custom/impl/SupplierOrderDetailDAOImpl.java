package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.SupplierOrderDetailDAO;
import org.example.stockverse.entity.SupplierOrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderDetailDAOImpl implements SupplierOrderDetailDAO {
    @Override
    public boolean save(SupplierOrderDetail DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier_stock_detail (Date, Stock_Id, Sup_Id) VALUES (?, ?, ?)",
                DTO.getDate(), DTO.getStock_Id(), DTO.getSup_Id());
    }

    @Override
    public boolean update(SupplierOrderDetail DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier_stock_detail SET Date=?, Stock_Id=?, Sup_Id=? WHERE Stock_Id=? AND Sup_Id=?",
                DTO.getDate(), DTO.getStock_Id(), DTO.getSup_Id(), DTO.getStock_Id(), DTO.getSup_Id());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Stock_Id FROM supplier_stock_detail ORDER BY Stock_Id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("Stock_Id");
        }
        return null;
    }

    @Override
    public List<SupplierOrderDetail> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier_stock_detail");
        List<SupplierOrderDetail> supplierOrderDetails = new ArrayList<>();

        while (rst.next()) {
            SupplierOrderDetail supplierOrderDetail = new SupplierOrderDetail(
                    rst.getDate("Date").toLocalDate(),
                    rst.getString("Stock_Id"),
                    rst.getString("Sup_Id")
            );
            supplierOrderDetails.add(supplierOrderDetail);
        }
        return supplierOrderDetails;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Stock_Id FROM supplier_stock_detail");
        ArrayList<String> stockIds = new ArrayList<>();

        while (rst.next()) {
            stockIds.add(rst.getString("Stock_Id"));
        }
        return stockIds;
    }

    @Override
    public SupplierOrderDetail findById(String selectedId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier_stock_detail WHERE Stock_Id=?", selectedId);

        if (rst.next()) {
            return new SupplierOrderDetail(
                    rst.getDate("Date").toLocalDate(),
                    rst.getString("Stock_Id"),
                    rst.getString("Sup_Id")
            );
        }
        return null;
    }

    @Override
    public boolean delete(String stockId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier_stock_detail WHERE Stock_Id=?", stockId);
    }
}