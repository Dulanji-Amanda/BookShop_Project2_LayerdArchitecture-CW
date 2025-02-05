package org.example.stockverse.bo.custom.impl;

import org.example.stockverse.bo.custom.SupplierBO;
import org.example.stockverse.dao.DAOFactory;
import org.example.stockverse.dao.custom.SupplierDAO;
import org.example.stockverse.dto.SupplierDTO;
import org.example.stockverse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
        String nextId = supplierDAO.getNextId();
        if (nextId != null) {
            int id = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("S%03d", id);
        } else {
            return "S001";
        }
    }

    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier(supplierDTO.getSup_Id(), supplierDTO.getName(), supplierDTO.getContact(),supplierDTO.getUser_Id());
        return supplierDAO.save(supplier);
    }

    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = (ArrayList<Supplier>) supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            SupplierDTO supplierDTO = new SupplierDTO();
            supplierDTO.setSup_Id(supplier.getSup_Id());
            supplierDTO.setName(supplier.getName());
            supplierDTO.setContact(supplier.getContact());
            supplierDTO.setUser_Id(supplier.getUser_Id());
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier(supplierDTO.getSup_Id(), supplierDTO.getName(), supplierDTO.getContact(),supplierDTO.getUser_Id());
        return supplierDAO.update(supplier);
    }
    public ArrayList<String> getAllSupplierIds() throws SQLException,ClassNotFoundException{

        return supplierDAO.getAllIds();

    }
    public boolean deleteSupplier(String Sup_Id) throws SQLException, ClassNotFoundException {
        supplierDAO.delete(Sup_Id);
        return false;
    }


    public Supplier findById(String selectedSupId) throws SQLException, ClassNotFoundException {
        return supplierDAO.findById(selectedSupId);
    }
}
