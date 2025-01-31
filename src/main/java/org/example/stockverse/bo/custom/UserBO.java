package org.example.stockverse.bo.custom;


import org.example.stockverse.bo.SuperBO;
import org.example.stockverse.dto.UserDTO;
import org.example.stockverse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    public boolean saveUser(UserDTO DTO) throws SQLException, ClassNotFoundException ;
    public boolean updateUser(UserDTO DTO) throws SQLException, ClassNotFoundException;
    public boolean isEmailExists(String email) throws SQLException, ClassNotFoundException ;
    public String getNextUserId() throws SQLException, ClassNotFoundException ;
    public ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException ;
    public ArrayList<Object> getAllUserIds() throws SQLException, ClassNotFoundException ;
    public User findByUserId(String selectedId) throws SQLException, ClassNotFoundException ;
}