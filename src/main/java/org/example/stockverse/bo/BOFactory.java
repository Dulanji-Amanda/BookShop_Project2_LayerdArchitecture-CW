package org.example.stockverse.bo;
import org.example.stockverse.bo.custom.impl.*;


public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }

    public enum BOTypes {
        USER,CUSTOMER;
    }
    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case USER:
                return new UserBOImpl();
                case CUSTOMER:
                return new CustomerBOImpl();

            default:
                return null;

        }
    }
}