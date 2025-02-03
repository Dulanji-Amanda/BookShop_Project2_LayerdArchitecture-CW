package org.example.stockverse.dao;

import org.example.stockverse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOType{
        USER,CUSTOMER
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case USER:
                return new UserDAOImpl();
                case CUSTOMER:
                    return new CustomerDAOImpl();

            default:
                return null;
        }
    }
}