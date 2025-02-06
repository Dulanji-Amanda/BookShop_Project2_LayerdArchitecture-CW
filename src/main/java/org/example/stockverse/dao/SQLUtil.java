package org.example.stockverse.dao;

import org.example.stockverse.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }

        if (sql.startsWith("SELECT")) {
            return (T) preparedStatement.executeQuery();
        } else {
            return (T) ((Boolean) (preparedStatement.executeUpdate() > 0));
        }
    }

    public static void setAutoCommit(boolean autoCommit) throws SQLException, ClassNotFoundException {
        DBConnection.getDbConnection().getConnection().setAutoCommit(autoCommit);
    }

    public static void commit() throws SQLException, ClassNotFoundException {
        DBConnection.getDbConnection().getConnection().commit();
    }

    public static void rollback() throws SQLException, ClassNotFoundException {
        DBConnection.getDbConnection().getConnection().rollback();
    }
}