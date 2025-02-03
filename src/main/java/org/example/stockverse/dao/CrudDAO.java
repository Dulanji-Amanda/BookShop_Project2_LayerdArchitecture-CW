package org.example.stockverse.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO {
    public boolean save(T DTO) throws SQLException, ClassNotFoundException ;

    public boolean update(T DTO) throws SQLException, ClassNotFoundException;

    public String getNextId() throws SQLException, ClassNotFoundException ;

    public List<T> getAll() throws SQLException, ClassNotFoundException ;

    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException ;

    public T findById(String selectedId) throws SQLException, ClassNotFoundException;
}