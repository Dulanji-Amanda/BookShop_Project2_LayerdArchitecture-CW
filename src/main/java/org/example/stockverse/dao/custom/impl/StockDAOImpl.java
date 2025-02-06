package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.StockDAO;
import org.example.stockverse.entity.Employee;
import org.example.stockverse.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Stock_Id FROM stock ORDER BY Stock_Id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("Stock_Id");
        }
        return null;
    }

    public boolean save(Stock DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO stock VALUES (?,?,?)", DTO.getStock_Id(), DTO.getName(),DTO.getUser_Id());

    }

    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM stock");

        ArrayList<Stock> stockDTOS = new ArrayList<>();

        while (rst.next()) {
            Stock stock = new Stock(
                    rst.getString(1),  // st ID
                    rst.getString(2),  // Name
                    rst.getString(3)
            );
            stockDTOS.add(stock);
        }
        return stockDTOS;

    }

    public boolean update(Stock DTO ) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock SET Name=?, User_Id=? WHERE Stock_Id=?", DTO.getName(),DTO.getUser_Id(), DTO.getStock_Id());

    }

    public boolean delete(String Stock_Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM stock WHERE Stock_Id=?", Stock_Id);
    }
    public ArrayList<String> getAllIds() throws SQLException,ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Stock_Id FROM stock");

        ArrayList<String> stockIds = new ArrayList<>();

        while (rst.next()) {
            stockIds.add(rst.getString("Stock_Id"));
        }
        return stockIds;

    }
    public Stock findById(String selectedStockId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM stock WHERE Stock_Id=?", selectedStockId);

        ArrayList<Stock> stocks = new ArrayList<>();
        while (rst.next()) {
            Stock stock = new Stock(
                    rst.getString(" Stock_Id"),  // stock ID
                    rst.getString("Name"),  // Name
                    rst.getString("User_Id")
            );
            stocks.add(stock);
        }
        return stocks.get(0);
    }

}
