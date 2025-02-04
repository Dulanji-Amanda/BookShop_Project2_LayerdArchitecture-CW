package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.SupplierDAO;
import org.example.stockverse.dto.SupplierDTO;
import org.example.stockverse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Sup_Id FROM supplier ORDER BY Sup_Id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("Sup_Id");
        }
        return null;
    }

    public boolean save(Supplier DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES (?,?,?,?)", DTO.getSup_Id(), DTO.getName(), DTO.getContact(),DTO.getUser_Id());

    }

    public ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");

        ArrayList<Supplier> supplierDTOS = new ArrayList<>();

        while (rst.next()) {
            Supplier supplier = new Supplier(
                    rst.getString(1),  // sup ID
                    rst.getString(2),  // Name
                    rst.getInt(3),  // Contact
                    rst.getString(4)
            );
            supplierDTOS.add(supplier);
        }
        return supplierDTOS;

    }

    public boolean update(Supplier DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET Name=?, Contact=? , User_Id=? WHERE Sup_Id=?", DTO.getName(), DTO.getContact(),DTO.getUser_Id(), DTO.getSup_Id());

    }

    public boolean delete(String Sup_Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE Sup_Id=?", Sup_Id);


    }
    public ArrayList<String> getAllIds() throws SQLException,ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT Sup_Id FROM supplier");

        ArrayList<String> supplierIds = new ArrayList<>();

        while (rst.next()) {
            supplierIds.add(rst.getString("Sup_Id"));
        }
        return supplierIds;


    }


    public SupplierDTO findById(String selectedSupId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier WHERE Sup_Id=?", selectedSupId);

        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (rst.next()) {
            Supplier supplier = new Supplier(
                    rst.getString(" Sup_Id"),  // sup ID
                    rst.getString("Name"),  // Name
                    rst.getInt("Contact") , // Contact
                    rst.getString("User_Id")
            );
            suppliers.add(supplier);
        }
        return suppliers.get(0);

    }
}
