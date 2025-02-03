package org.example.stockverse.bo.custom;

import org.example.stockverse.bo.SuperBO;
import org.example.stockverse.dto.CustomerDTO;
import org.example.stockverse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public String getNextCustomerId() throws SQLException, ClassNotFoundException;
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String Cust_Id) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    public Customer findById(String selectedCusId) throws SQLException, ClassNotFoundException;

}
