package org.example.stockverse.bo.custom;

import org.example.stockverse.bo.SuperBO;
import org.example.stockverse.dto.StockDTO;
import org.example.stockverse.entity.Item;
import org.example.stockverse.entity.Stock;
import org.example.stockverse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO extends SuperBO {
    public String getNextStockId() throws SQLException, ClassNotFoundException;

    public boolean saveStock(StockDTO stockDTO, Item item, Supplier supplier, String text) throws SQLException, ClassNotFoundException;

    public ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException;

    public boolean updateStock(StockDTO stockDTO, Item itemsDTO, Supplier suppliersDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteStock(String stockId) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllStockIds() throws SQLException, ClassNotFoundException;

    public Stock findById(String selectedCusId) throws SQLException, ClassNotFoundException;
}