package org.example.stockverse.dao.custom;

import org.example.stockverse.dao.CrudDAO;
import org.example.stockverse.entity.SupplierOrderDetail;

import java.sql.SQLException;

public interface SupplierOrderDetailDAO extends CrudDAO<SupplierOrderDetail> {
    boolean delete(String stockId) throws SQLException, ClassNotFoundException;
}
