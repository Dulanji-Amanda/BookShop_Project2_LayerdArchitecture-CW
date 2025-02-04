package org.example.stockverse.dao.custom;

import org.example.stockverse.dao.CrudDAO;
import org.example.stockverse.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {
    public boolean delete(String selectedId) throws SQLException, ClassNotFoundException ;


}
