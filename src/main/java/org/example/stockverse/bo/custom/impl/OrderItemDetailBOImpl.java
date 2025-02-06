package org.example.stockverse.bo.custom.impl;

import org.example.stockverse.bo.custom.OrderItemDetailBO;
import org.example.stockverse.dao.DAOFactory;
import org.example.stockverse.dao.custom.OrderItemDetailDAO;
import org.example.stockverse.dto.OrderDetailDTO;
import org.example.stockverse.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDetailBOImpl implements OrderItemDetailBO {
    OrderItemDetailDAO orderItemDetailDAO = (OrderItemDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_DETAIL);

    @Override
    public String getNextOrderItemDetailId() throws SQLException, ClassNotFoundException {
        String nextId = orderItemDetailDAO.getNextId();
        if (nextId != null) {
            int id = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("O%03d", id);
        } else {
            return "O001";
        }
    }

    @Override
    public boolean saveOrderItemDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        OrderDetail orderDetail = new OrderDetail(
                orderDetailDTO.getOrderId(),
                orderDetailDTO.getItemId(),
                orderDetailDTO.getDate(),
                orderDetailDTO.getAmount()
        );
        return orderItemDetailDAO.save(orderDetail);
    }

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderItemDetails() throws SQLException, ClassNotFoundException {
        List<OrderDetail> orderDetails = orderItemDetailDAO.getAll();
        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(
                    orderDetail.getOrderId(),
                    orderDetail.getItemId(),
                    orderDetail.getDate(),
                    orderDetail.getAmount()
            );
            orderDetailDTOS.add(orderDetailDTO);
        }
        return orderDetailDTOS;
    }

    @Override
    public boolean updateOrderItemDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        OrderDetail orderDetail = new OrderDetail(
                orderDetailDTO.getOrderId(),
                orderDetailDTO.getItemId(),
                orderDetailDTO.getDate(),
                orderDetailDTO.getAmount()
        );
        return orderItemDetailDAO.update(orderDetail);
    }

    @Override
    public boolean deleteOrderItemDetail(String orderItemDetailId) throws SQLException, ClassNotFoundException {
        return orderItemDetailDAO.delete(orderItemDetailId);
    }

    @Override
    public ArrayList<String> getAllOrderItemDetailIds() throws SQLException, ClassNotFoundException {
        return orderItemDetailDAO.getAllIds();
    }

    @Override
    public OrderDetail findById(String selectedOrderItemDetailId) throws SQLException, ClassNotFoundException {
        return orderItemDetailDAO.findById(selectedOrderItemDetailId);
    }
}