package org.example.stockverse.bo.custom.impl;

import org.example.stockverse.bo.custom.UserBO;
import org.example.stockverse.dao.DAOFactory;
import org.example.stockverse.dao.custom.UserDAO;
import org.example.stockverse.dto.UserDTO;
import org.example.stockverse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean saveUser(UserDTO DTO) throws SQLException, ClassNotFoundException {
        User user = new User(DTO.getUserId(), DTO.getFirstName(), DTO.getLastName(), DTO.getUsername(), DTO.getEmail(), DTO.getPassword());
        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDTO DTO) throws SQLException, ClassNotFoundException {
        User user = new User(DTO.getUserId(),DTO.getFirstName(),DTO.getLastName(),DTO.getUsername(),DTO.getEmail(),DTO.getPassword());
        //System.out.println("UserBOImpl: " + user);
        return userDAO.update(user);
    }

    @Override
    public boolean isEmailExists(String email) throws SQLException, ClassNotFoundException {
        return userDAO.isEmailExists(email);
    }

    @Override
    public String getNextUserId() throws SQLException, ClassNotFoundException {
        String lastUserId = userDAO.getNextId();
        if (lastUserId != null) {
            int id = Integer.parseInt(lastUserId.substring(1)) + 1;
            return String.format("U%03d", id);
        } else {
            return "U001";
        }
    }

    @Override
    public ArrayList<UserDTO> getAllUser() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = (ArrayList<User>) userDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException, ClassNotFoundException {
        return userDAO.getAllIds();
    }

    @Override
    public User findById(String selectedId) throws SQLException, ClassNotFoundException {
        return userDAO.findById(selectedId);
    }
}