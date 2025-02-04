package org.example.stockverse.bo.custom.impl;

import org.example.stockverse.bo.custom.EmployeeBO;
import org.example.stockverse.dao.DAOFactory;
import org.example.stockverse.dao.custom.EmployeeDAO;
import org.example.stockverse.dto.EmployeeDTO;
import org.example.stockverse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        String nextId = employeeDAO.getNextId();
        if (nextId != null) {
            int id = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("E%03d", id);
        } else {
            return "E001";
        }
    }
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException,ClassNotFoundException{
        Employee employee = new Employee(employeeDTO.getEmp_Id(), employeeDTO.getName(), employeeDTO.getContact(),employeeDTO.getUser_Id());
        return employeeDAO.save(employee);
    }

    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException,ClassNotFoundException{
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmp_Id(employee.getEmp_Id());
            employeeDTO.setName(employee.getName());
            employeeDTO.setContact(employee.getContact());
            employeeDTO.setUser_Id(employee.getUser_Id());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException,ClassNotFoundException{
        Employee employee = new Employee(employeeDTO.getEmp_Id(), employeeDTO.getName(), employeeDTO.getContact(),employeeDTO.getUser_Id());
        return employeeDAO.update(employee);
    }

    public boolean deleteEmployee(String Emp_Id) throws SQLException,ClassNotFoundException{
        employeeDAO.delete(Emp_Id);
        return false;       }

    public ArrayList<String> getAllEmployeeIds() throws SQLException,ClassNotFoundException{
        return employeeDAO.getAllIds();
    }

    public Employee findById(String selectedEmpId) throws SQLException,ClassNotFoundException{
        return employeeDAO.findById(selectedEmpId);
    }
}
