package org.example.stockverse.dao.custom;

import org.example.stockverse.dao.CrudDAO;
import org.example.stockverse.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    public boolean isEmailExists(String email) throws SQLException, ClassNotFoundException ;

}