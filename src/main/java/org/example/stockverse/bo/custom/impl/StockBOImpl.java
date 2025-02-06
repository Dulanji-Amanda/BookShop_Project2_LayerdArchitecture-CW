package org.example.stockverse.bo.custom.impl;

import org.example.stockverse.bo.custom.StockBO;
import org.example.stockverse.dao.DAOFactory;
import org.example.stockverse.dao.custom.StockDAO;
import org.example.stockverse.dto.ItemDTO;
import org.example.stockverse.dto.StockDTO;
import org.example.stockverse.entity.Item;
import org.example.stockverse.entity.Stock;
import org.example.stockverse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STOCK);

    @Override
    public String getNextStockId() throws SQLException, ClassNotFoundException {
        String nextId = stockDAO.getNextId();
        if (nextId != null) {
            int id = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("T%03d", id);
        } else {
            return "T001";
        }
    }

    @Override
    public boolean saveStock(StockDTO stockDTO, Item itemsDTO, Supplier suppliersDTO, String text) throws SQLException, ClassNotFoundException {
        Stock stock = new Stock(stockDTO.getStock_Id(), stockDTO.getName(),stockDTO.getUser_Id());
        return stockDAO.save(stock);     }

    @Override
    public ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> stocks = (ArrayList<Stock>) stockDAO.getAll();
        ArrayList<StockDTO> stockDTOS = new ArrayList<>();
        for (Stock stock : stocks) {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setStock_Id(stock.getStock_Id());
            stockDTO.setName(stock.getName());
            stockDTO.setUser_Id(stock.getUser_Id());
            stockDTOS.add(stockDTO);
        }
        return stockDTOS;
    }

    @Override
    public boolean updateStock(StockDTO stockDTO, Item itemsDTO, Supplier suppliersDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteStock(String stockId) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(stockId);
    }

    @Override
    public ArrayList<String> getAllStockIds() throws SQLException, ClassNotFoundException {
        return stockDAO.getAllIds();
    }

    @Override
    public Stock findById(String selectedStockId) throws SQLException, ClassNotFoundException {
        return stockDAO.findById(selectedStockId);
    }
}
