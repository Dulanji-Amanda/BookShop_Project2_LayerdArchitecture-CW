package org.example.stockverse.bo.custom.impl;

import org.example.stockverse.bo.custom.CustomerBO;
import org.example.stockverse.dao.DAOFactory;
import org.example.stockverse.dao.custom.CustomerDAO;
import org.example.stockverse.dto.CustomerDTO;
import org.example.stockverse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        String nextId = customerDAO.getNextId();
        if (nextId != null) {
            int id = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("C%03d", id);
        } else {
            return "C001";
        }
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(customerDTO.getCust_Id(), customerDTO.getCust_Name(), customerDTO.getContact(),customerDTO.getAddress(),customerDTO.getUser_Id());
        return customerDAO.save(customer);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = (ArrayList<Customer>) customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCust_Id(customer.getCust_Id());
            customerDTO.setCust_Name(customer.getCust_Name());
            customerDTO.setContact(customer.getContact());
            customerDTO.setAddress(customer.getAddress());
            customerDTO.setUser_Id(customer.getUser_Id());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(customerDTO.getCust_Id(), customerDTO.getCust_Name(), customerDTO.getContact(),customerDTO.getAddress(),customerDTO.getUser_Id());
        return customerDAO.update(customer);     }

    @Override
    public boolean deleteCustomer(String Cust_Id) throws SQLException, ClassNotFoundException {
        customerDAO.delete(Cust_Id);
        return false;    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllIds();
    }

    @Override
    public Customer findById(String selectedCusId) throws SQLException, ClassNotFoundException {
        return customerDAO.findById(selectedCusId);
    }
}
