package org.example.stockverse.bo.custom;

import org.example.stockverse.bo.SuperBO;
import org.example.stockverse.dto.SupplierOrderDetailDTO;
import org.example.stockverse.entity.SupplierOrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDetailBO extends SuperBO {
    String getNextSupplierOrderDetailId() throws SQLException, ClassNotFoundException;
    boolean saveSupplierOrderDetail(SupplierOrderDetailDTO supplierOrderDetailDTO) throws SQLException, ClassNotFoundException;
    ArrayList<SupplierOrderDetailDTO> getAllSupplierOrderDetails() throws SQLException, ClassNotFoundException;
    boolean updateSupplierOrderDetail(SupplierOrderDetailDTO supplierOrderDetailDTO) throws SQLException, ClassNotFoundException;
    boolean deleteSupplierOrderDetail(String stockId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSupplierOrderDetailIds() throws SQLException, ClassNotFoundException;
    SupplierOrderDetail findById(String stockId) throws SQLException, ClassNotFoundException;
}