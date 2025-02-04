package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.StockDAO;
import org.example.stockverse.dto.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Emp_Id FROM employee ORDER BY Emp_Id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("Emp_Id");
        }
        return null;
    }

    public boolean save(SupplierDTO supplierDTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO employee VALUES (?,?,?,?)", DTO.getEmp_Id(), DTO.getName(), DTO.getContact(),DTO.getUser_Id());

    }

    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException {

    }

    public boolean update(SupplierDTO supplierDTO) throws SQLException {
        return SQLUtil.execute("UPDATE employee SET Name=?, Contact=? , User_Id=? WHERE Emp_Id=?", DTO.getName(), DTO.getContact(),DTO.getUser_Id(), DTO.getEmp_Id());

    }

    public boolean delete(String Sup_Id) throws SQLException {

    }
    public ArrayList<String> getAllIds() throws SQLException,ClassNotFoundException; {

    }


    public SupplierDTO findById(String selectedSupId) throws SQLException {

    }
}
