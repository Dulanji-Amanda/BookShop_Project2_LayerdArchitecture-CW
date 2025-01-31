package org.example.stockverse.dao;

import org.example.stockverse.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql,Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }

        if(sql.startsWith("SELECT")){
            return (T) preparedStatement.executeQuery();
        }else{
            return (T) ((Boolean) (preparedStatement.executeUpdate()>0));
        }
    }
}