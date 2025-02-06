package org.example.stockverse.dao.custom;

import org.example.stockverse.dao.CrudDAO;
import org.example.stockverse.dto.PaymentDTO;
import org.example.stockverse.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {
    public PaymentDTO getPaymentByOrderId(String orderId) throws SQLException, ClassNotFoundException;
}
