package org.example.stockverse.bo.custom;

import org.example.stockverse.bo.SuperBO;
import org.example.stockverse.dto.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public String getNextSupplierId() throws SQLException,ClassNotFoundException;

    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException,ClassNotFoundException;

    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException ,ClassNotFoundException;

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException,ClassNotFoundException;

    public boolean deleteSupplier(String Sup_Id) throws SQLException,ClassNotFoundException ;

    public SupplierDTO findById(String selectedSupId) throws SQLException,ClassNotFoundException;

    public ArrayList<String> getAllSupplierIds() throws SQLException,ClassNotFoundException;
}
