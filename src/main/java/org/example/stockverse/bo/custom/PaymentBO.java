package org.example.stockverse.bo.custom;

import org.example.stockverse.bo.SuperBO;
import org.example.stockverse.dto.PaymentDTO;
import org.example.stockverse.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public String getNextPaymentId() throws SQLException, ClassNotFoundException;
    public boolean savePayment(PaymentDTO PaymentDTO) throws SQLException, ClassNotFoundException;
    public List<Payment> getAllPayments() throws SQLException, ClassNotFoundException;
    public boolean updatePayment(PaymentDTO PaymentDTO) throws SQLException, ClassNotFoundException;
    public boolean deletePayment(String paymentId) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllPaymentIds() throws SQLException, ClassNotFoundException;
    public Payment findById(String selectedCusId) throws SQLException, ClassNotFoundException;
    PaymentDTO getPaymentByOrderId(String orderId) throws SQLException, ClassNotFoundException;
}
