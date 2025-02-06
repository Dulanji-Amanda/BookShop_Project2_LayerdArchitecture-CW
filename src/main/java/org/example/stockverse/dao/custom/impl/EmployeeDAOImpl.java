package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.EmployeeDAO;

import org.example.stockverse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES (?,?,?,?)", DTO.getEmp_Id(), DTO.getName(), DTO.getContact(),DTO.getUser_Id());

    }

    @Override
    public boolean update(Employee DTO) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("UPDATE employee SET Name=?, Contact=? , User_Id=? WHERE Emp_Id=?", DTO.getName(), DTO.getContact(),DTO.getUser_Id(), DTO.getEmp_Id());
    }

    public String getNextId() throws SQLException,ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT Emp_Id FROM employee ORDER BY Emp_Id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("Emp_Id");
        }
        return null;
    }
    public ArrayList<Employee> getAll() throws SQLException,ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");

        ArrayList<Employee> employeeDTOS = new ArrayList<>();

        while (rst.next()) {
            Employee employee = new Employee(
                    rst.getString(1),  // emp ID
                    rst.getString(2),  // Name
                    rst.getInt(3),  // Contact
                    rst.getString(4)
            );
            employeeDTOS.add(employee);
        }
        return employeeDTOS;
    }


    public boolean delete(String Emp_Id) throws SQLException,ClassNotFoundException{
        return SQLUtil.execute("DELETE FROM employee WHERE Emp_Id=?", Emp_Id);

    }

    public ArrayList<String> getAllIds() throws SQLException,ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT Emp_Id FROM employee");

        ArrayList<String> employeeIds = new ArrayList<>();

        while (rst.next()) {
            employeeIds.add(rst.getString("Emp_Id"));
        }
        return employeeIds;
    }

    public Employee findById(String selectedEmpId) throws SQLException,ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE Emp_Id=?", selectedEmpId);

        ArrayList<Employee> employees = new ArrayList<>();
        while (rst.next()) {
            Employee employee = new Employee(
                        rst.getString(" Emp_Id"),  // emp ID
                    rst.getString("Name"),  // Name
                    rst.getInt("Contact") , // Contact
                    rst.getString("User_Id")
            );
            employees.add(employee);
        }
        return employees.get(0);
    }
}
