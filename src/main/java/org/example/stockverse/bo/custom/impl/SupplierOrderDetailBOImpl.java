package org.example.stockverse.bo.custom.impl;

import org.example.stockverse.bo.custom.SupplierOrderDetailBO;
import org.example.stockverse.dao.DAOFactory;
import org.example.stockverse.dao.custom.SupplierOrderDetailDAO;
import org.example.stockverse.dto.SupplierOrderDetailDTO;
import org.example.stockverse.entity.SupplierOrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderDetailBOImpl implements SupplierOrderDetailBO {
    SupplierOrderDetailDAO supplierOrderDetailDAO = (SupplierOrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER_ORDER_DETAIL);

    @Override
    public String getNextSupplierOrderDetailId() throws SQLException, ClassNotFoundException {
        String nextId = supplierOrderDetailDAO.getNextId();
        if (nextId != null) {
            int id = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("S%03d", id);
        } else {
            return "S001";
        }
    }

    @Override
    public boolean saveSupplierOrderDetail(SupplierOrderDetailDTO supplierOrderDetailDTO) throws SQLException, ClassNotFoundException {
        SupplierOrderDetail supplierOrderDetail = new SupplierOrderDetail(
                supplierOrderDetailDTO.getDate(),
                supplierOrderDetailDTO.getStock_Id(),
                supplierOrderDetailDTO.getSup_Id()
        );
        return supplierOrderDetailDAO.save(supplierOrderDetail);
    }

    @Override
    public ArrayList<SupplierOrderDetailDTO> getAllSupplierOrderDetails() throws SQLException, ClassNotFoundException {
        List<SupplierOrderDetail> supplierOrderDetails = supplierOrderDetailDAO.getAll();
        ArrayList<SupplierOrderDetailDTO> supplierOrderDetailDTOS = new ArrayList<>();
        for (SupplierOrderDetail supplierOrderDetail : supplierOrderDetails) {
            SupplierOrderDetailDTO supplierOrderDetailDTO = new SupplierOrderDetailDTO(
                    supplierOrderDetail.getDate(),
                    supplierOrderDetail.getStock_Id(),
                    supplierOrderDetail.getSup_Id()
            );
            supplierOrderDetailDTOS.add(supplierOrderDetailDTO);
        }
        return supplierOrderDetailDTOS;
    }

    @Override
    public boolean updateSupplierOrderDetail(SupplierOrderDetailDTO supplierOrderDetailDTO) throws SQLException, ClassNotFoundException {
        SupplierOrderDetail supplierOrderDetail = new SupplierOrderDetail(
                supplierOrderDetailDTO.getDate(),
                supplierOrderDetailDTO.getStock_Id(),
                supplierOrderDetailDTO.getSup_Id()
        );
        return supplierOrderDetailDAO.update(supplierOrderDetail);
    }

    @Override
    public boolean deleteSupplierOrderDetail(String stockId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> getAllSupplierOrderDetailIds() throws SQLException, ClassNotFoundException {
        return supplierOrderDetailDAO.getAllIds();
    }

    @Override
    public SupplierOrderDetail findById(String stockId) throws SQLException, ClassNotFoundException {
        return supplierOrderDetailDAO.findById(stockId);
    }
}