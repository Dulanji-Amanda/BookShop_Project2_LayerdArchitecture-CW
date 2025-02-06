package org.example.stockverse.bo.custom.impl;

import org.example.stockverse.bo.custom.PaymentBO;
import org.example.stockverse.dao.DAOFactory;
import org.example.stockverse.dao.custom.PaymentDAO;
import org.example.stockverse.dto.PaymentDTO;
import org.example.stockverse.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public String getNextPaymentId() throws SQLException, ClassNotFoundException {
        String nextId = paymentDAO.getNextId();
        if (nextId != null) {
            int id = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("P%03d", id);
        } else {
            return "P001";
        }
    }

    @Override
    public boolean savePayment(PaymentDTO PaymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(PaymentDTO.getPaymentId(), PaymentDTO.getAmount(), PaymentDTO.getContact(), PaymentDTO.getPaymentDate(), PaymentDTO.getOrderId()));
    }

    @Override
    public List<Payment> getAllPayments() throws SQLException, ClassNotFoundException {
        return paymentDAO.getAll();
    }

    @Override
    public boolean updatePayment(PaymentDTO PaymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(PaymentDTO.getPaymentId(), PaymentDTO.getAmount(), PaymentDTO.getContact(), PaymentDTO.getPaymentDate(), PaymentDTO.getOrderId()));
    }

    @Override
    public boolean deletePayment(String paymentId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> getAllPaymentIds() throws SQLException, ClassNotFoundException {
        return paymentDAO.getAllIds();
    }

    @Override
    public Payment findById(String selectedCusId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public PaymentDTO getPaymentByOrderId(String orderId) throws SQLException, ClassNotFoundException {
        return paymentDAO.getPaymentByOrderId(orderId);
    }
}
