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
        USER,CUSTOMER,ITEM,EMPLOYEE,SUPPLIER,STOCK,ORDER,PAYMENT,SUPPLIER_ORDER_DETAIL,ORDER_DETAIL
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case USER:
            return new UserDAOImpl();
            case CUSTOMER:
            return new CustomerDAOImpl();
            case ITEM:
            return new ItemDAOImpl();
            case EMPLOYEE:
            return new EmployeeDAOImpl();
            case SUPPLIER:
            return new SupplierDAOImpl();
            case STOCK:
            return new StockDAOImpl();
            case ORDER:
            return new OrderDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SUPPLIER_ORDER_DETAIL:
                return new SupplierOrderDetailDAOImpl();
            case ORDER_DETAIL:
                return new OrderItemDetailDAOImpl();

            default:
                return null;
        }
    }
}