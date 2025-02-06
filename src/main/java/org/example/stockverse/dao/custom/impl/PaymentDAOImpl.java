package org.example.stockverse.dao.custom.impl;

import org.example.stockverse.dao.SQLUtil;
import org.example.stockverse.dao.custom.PaymentDAO;
import org.example.stockverse.dto.PaymentDTO;
import org.example.stockverse.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment VALUES (?,?,?,?,?)", DTO.getPaymentId(), DTO.getAmount(), DTO.getContact(),DTO.getPaymentDate(),DTO.getOrderId());
    }

    @Override
    public boolean update(Payment DTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET Amount=?, Contact=? , Payment_Date=?,Order_Id=? WHERE Payment_Id=?", DTO.getAmount(), DTO.getContact(), DTO.getPaymentDate(),DTO.getOrderId(), DTO.getPaymentId());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Payment_Id FROM payment ORDER BY Payment_Id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("Payment_Id");
        }
        return null;
    }

    @Override
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");

        ArrayList<Payment> payments = new ArrayList<>();

        while (rst.next()) {
            Payment payment = new Payment(
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getInt(3),
                    rst.getDate(4).toLocalDate(),
                    rst.getString(5)
            );
            payments.add(payment);
        }
        return payments;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Payment_Id FROM payment");

        ArrayList<String> paymnetIds = new ArrayList<>();

        while (rst.next()) {
            paymnetIds.add(rst.getString("Payment_Id"));
        }
        return paymnetIds;
    }

    @Override
    public Payment findById(String selectedId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment WHERE Payment_Id=?", selectedId);

        if (rst.next()) {
            return new Payment(
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getInt(3),
                    rst.getDate(4).toLocalDate(),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public PaymentDTO getPaymentByOrderId(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment WHERE Order_Id=?", orderId);
        if (rst.next()) {
            return new PaymentDTO(
                    rst.getString("Payment_Id"),
                    rst.getDouble("Amount"),
                    rst.getInt("Contact"),
                    rst.getDate("Payment_Date").toLocalDate(),
                    rst.getString("Order_Id")
            );
        }
        return null;
    }
}
