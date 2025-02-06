package org.example.stockverse.bo;
import org.example.stockverse.bo.custom.impl.*;


public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }

    public enum BOTypes {
        USER,CUSTOMER,ITEM,EMPLOYEE,SUPPLIER,STOCK,ORDER;
    }
    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case USER:
                return new UserBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case STOCK:
                return new StockBOImpl();
           case ORDER:
                 return new OrderBOImpl();
            default:
                return null;

        }
    }
}