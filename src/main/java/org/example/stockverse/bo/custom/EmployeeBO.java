package org.example.stockverse.bo.custom;

import org.example.stockverse.bo.SuperBO;
import org.example.stockverse.dto.EmployeeDTO;
import org.example.stockverse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public String getNextEmployeeId() throws SQLException,ClassNotFoundException;

    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException,ClassNotFoundException;

    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException,ClassNotFoundException ;

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException,ClassNotFoundException;

    public boolean deleteEmployee(String Emp_Id) throws SQLException,ClassNotFoundException;

    public ArrayList<String> getAllEmployeeIds() throws SQLException,ClassNotFoundException;

    public Employee findById(String selectedEmpId) throws SQLException,ClassNotFoundException;
}
