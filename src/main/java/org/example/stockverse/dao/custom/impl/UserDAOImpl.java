package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.UserDAO;
import org.example.stockverse.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user VALUES (user_Id,firstName,lastName,username,email,password)",DTO.getUserId(),DTO.getFirstName(),DTO.getLastName(),DTO.getUsername(),DTO.getEmail(),DTO.getPassword());
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        String query = "UPDATE user SET firstName=?, lastName=?, username=?, email=?, password=? WHERE user_Id=?";
        return SQLUtil.execute(query, user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getUserId());
    }

    @Override
    public boolean isEmailExists(String email) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT email FROM user WHERE email = ?",email);
        return rst.next();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT userId FROM user ORDER BY userId DESC LIMIT 1;");
        if (rst.next()){
            return rst.getString(1);
        }
        return null;    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM user");

        ArrayList<User> userList = new ArrayList<>();

        while (rst.next()) {
            User user = new User(
                    rst.getString("User_Id"),
                    rst.getString("firstName"),
                    rst.getString("lastName"),
                    rst.getString("username"),
                    rst.getString("email"),
                    rst.getString("password")
            );
            userList.add(user);
        }
        return userList;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT User_Id FROM user");

        ArrayList<String> userIds = new ArrayList<>();

        while (rst.next()) {
            userIds.add(rst.getString("User_Id"));
        }
        return userIds;
    }

    @Override
    public User findById(String selectedId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM user WHERE user_Id=?", selectedId);
        if (rst.next()) {
            return new User(
                    rst.getString("user_Id"),
                    rst.getString("firstName"),
                    rst.getString("lastName"),
                    rst.getString("username"),
                    rst.getString("email"),
                    rst.getString("password")
            );
        }
        return null;

    }
}
