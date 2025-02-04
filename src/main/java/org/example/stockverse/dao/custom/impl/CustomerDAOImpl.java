package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.CustomerDAO;
import org.example.stockverse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?)", DTO.getCust_Id(), DTO.getCust_Name(), DTO.getContact(),DTO.getAddress(),DTO.getUser_Id());
    }

    @Override
    public boolean update(Customer DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET Cust_Name=?, Contact=? , Address=?,User_Id=? WHERE Cust_Id=?", DTO.getCust_Name(), DTO.getContact(), DTO.getAddress(),DTO.getUser_Id(), DTO.getCust_Id());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Cust_Id FROM customer ORDER BY Cust_Id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("Cust_Id");
        }
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            Customer customer = new Customer(
                    rst.getString(1),  // customer ID
                    rst.getString(2),  // Name
                    rst.getInt(3),  // Contact
                    rst.getString(4),
                    rst.getString(5)
            );
            customerDTOS.add(customer);
        }
        return customerDTOS;    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Cust_Id FROM customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString("Cust_Id"));
        }
        return customerIds;
    }

    public Customer findById(String selectedCusId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE Cust_Id=?", selectedCusId);

        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            Customer customer = new Customer(
                    rst.getString("Cust_Id"),  // customer ID
                    rst.getString("Name"),  // Name
                    rst.getInt("Contact") , // Contact
                    rst.getString("Address"),
                    rst.getString("User_Id")
            );
            customers.add(customer);
        }
        return customers.get(0);    }

    @Override
    public boolean delete(String selectedId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE Cust_Id=?", selectedId);
    }
}
