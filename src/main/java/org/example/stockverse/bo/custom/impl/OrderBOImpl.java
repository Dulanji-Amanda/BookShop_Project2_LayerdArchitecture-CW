package org.example.stockverse.bo.custom.impl;

import org.example.stockverse.bo.custom.OrderBO;
import org.example.stockverse.dao.DAOFactory;
import org.example.stockverse.dao.custom.OrderDAO;
import org.example.stockverse.dto.OrderDTO;
import org.example.stockverse.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);

    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        String nextId = orderDAO.getNextId();
        if (nextId != null) {
            int id = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("O%03d", id);
        } else {
            return "O001";
        }
    }

    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Order order = new Order(orderDTO.getOrder_Id(), orderDTO.getDescription(), orderDTO.getOrder_qty(), orderDTO.getCust_Id());
        return orderDAO.save(order);
    }

    // Method to get all orders
    public ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<Order> orders = (ArrayList<Order>) orderDAO.getAll();
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrder_Id(order.getOrder_Id());
            orderDTO.setDescription(order.getDescription());
            orderDTO.setOrder_qty(order.getOrder_qty());
            orderDTO.setCust_Id(order.getCust_Id());
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;

    }

    public boolean updateOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Order order = new Order(orderDTO.getOrder_Id(), orderDTO.getDescription(), orderDTO.getOrder_qty(), orderDTO.getCust_Id());
        return orderDAO.update(order);
    }

    public boolean deleteOrder(String order_id) throws SQLException, ClassNotFoundException {
        orderDAO.delete(order_id);
        return false;
    }


    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException {
        return orderDAO.getAllIds();

    }

    public Order findById(String orderId) throws SQLException, ClassNotFoundException {
        return orderDAO.findById(orderId);

    }
}
