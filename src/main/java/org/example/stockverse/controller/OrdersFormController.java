package org.example.stockverse.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.example.stockverse.bo.BOFactory;
import org.example.stockverse.bo.custom.CustomerBO;
import org.example.stockverse.bo.custom.ItemBO;
import org.example.stockverse.bo.custom.OrderBO;
import org.example.stockverse.db.DBConnection;
import org.example.stockverse.dto.OrderDTO;
import org.example.stockverse.entity.Customer;
import org.example.stockverse.view.tdm.CustomerTM;
import org.example.stockverse.view.tdm.ItemTM;
import org.example.stockverse.view.tdm.OrderTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class OrdersFormController implements Initializable {

        @FXML
        private AnchorPane OrderApane;

        @FXML
        private TableView<OrderTM> OrderTbl;

        @FXML
        private TableColumn<CustomerTM, String> colCustomerId;

        @FXML
        private TableColumn<OrderTM, LocalDate> colDate;

        @FXML
        private TableColumn<ItemTM, String> colItemId;

        @FXML
        private TableColumn<OrderTM, String> colOrderId;

        @FXML
        private TableColumn<OrderTM, String> colPaymentId;

        @FXML
        private TableColumn<OrderTM, Integer> colQty;

        @FXML
        private TableColumn<OrderTM, Double> colTotalAmount;

        @FXML
        private ComboBox<String> custIdComboOrder;

        @FXML
        private Label dateLbl;

        @FXML
        private Label lblItemId;

        @FXML
        private Label lblItemName;

        @FXML
        private Label lblItemPrice;

        @FXML
        private Label lblOrderId;

        @FXML
        private Label lblOrderQty;

        @FXML
        private Label lblOrderedQty;

        @FXML
        private Button orderbtnD;

        @FXML
        private Button orderbtnR;

        @FXML
        private Button orderbtnU;

        @FXML
        private Button orderreportbtn;

        @FXML
        void OrderRepportOnAction(ActionEvent event) {
            try {
                Connection connection = DBConnection.getDbConnection().getConnection();

//            Map<String, Object> parameters = new HashMap<>();
//            today - 2024 - 02 - 02
//            TODAY -

//            parameters.put("today",LocalDate.now().toString());
//            <key , value>
//            Initialize a map to hold the report parameters
//            These parameters can be used inside the report (like displaying today's date)

                // Initialize a map to hold the report parameters
                // These parameters can be used inside the report (like displaying today's date)
                Map<String, Object> parameters = new HashMap<>();

                // Put the current date into the map with two different keys ("today" and "TODAY")
                // You can refer to these keys in the Jasper report if needed
                parameters.put("today", LocalDate.now().toString());
                parameters.put("TODAY", LocalDate.now().toString());

                // Compile the Jasper report from a JRXML file (report template)
                // The report template is located in the "resources/report" folder of the project
                JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/OrderDetailReport.jrxml"));

                // Fill the report with the compiled report object, parameters, and a database connection
                // This prepares the report with real data from the database
                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        jasperReport,
                        parameters,
                        connection
                );

                // Display the report in a viewer (this is a built-in JasperReports viewer)
                // 'false' indicates that the window should not close the entire application when closed
                JasperViewer.viewReport(jasperPrint, false);
            } catch (JRException e) {
                new Alert(Alert.AlertType.ERROR, "Fail to load report..!");
                e.printStackTrace();
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Data empty..!");
                e.printStackTrace();
            }
        }

    @FXML
    private TextField txtOrderDesc;

    public static OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
    public static CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    public static ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    //OrderItemDetailModel orderItemDetailModel = new OrderItemDetailModel();
    //PaymentModel paymentModel = new PaymentModel();

    public double itemPrice = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("Order_Id"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("Item_Id"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("Payment_Id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("Customer_Id"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        try {
            loadCustomerIds();
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIds = customerBO.getAllCustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerIds);
        custIdComboOrder.setItems(observableList);
    }

    private void refreshPage() throws SQLException {
        refreshTable();

        txtOrderDesc.setText("");
        lblOrderedQty.setText("");
        lblItemPrice.setText("");
        lblOrderId.setText("");
        custIdComboOrder.getSelectionModel().clearSelection();
        lblItemId.setText("");
        dateLbl.setText("");
        lblItemName.setText("");

        orderbtnD.setDisable(true);
        orderbtnU.setDisable(true);
    }

   private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> orderDTOS = orderBO.getAllOrders();
        ObservableList<OrderTM> orderTMS = FXCollections.observableArrayList();

        if (orderDTOS.isEmpty()) {
            return;
        }

        /*for (OrderDTO orderDTO : orderDTOS) {
            String paymentId = null;
            PaymentDTO paymentDTO = paymentModel.getPaymentByOrderId(orderDTO.getOrder_Id());

            if (paymentDTO != null) {
                paymentId = paymentDTO.getPaymentId();
            } else {
                System.out.println("No payment found for order ID: " + orderDTO.getOrder_Id());
            }

            OrderTM orderTM = new OrderTM(
                    orderDTO.getOrder_Id(),
                    orderItemDetailModel.findById(orderDTO.getOrder_Id()).getItemId(),
                    paymentId,
                    orderDTO.getCust_Id(),
                    orderDTO.getOrder_qty(),
                    orderItemDetailModel.findById(orderDTO.getOrder_Id()).getDate(),
                    orderItemDetailModel.findById(orderDTO.getOrder_Id()).getAmount()
            );
            orderTMS.add(orderTM);
        }
        OrderTbl.setItems(orderTMS);*/
    }

    @FXML
    void OrderTblOnMouseClicked(MouseEvent event) throws SQLException, ClassNotFoundException {
       OrderTM selectedOrder = (OrderTM) OrderTbl.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            lblOrderId.setText(selectedOrder.getOrder_Id());
            txtOrderDesc.setText(orderBO.findById(selectedOrder.getOrder_Id()).getDescription());
            lblOrderedQty.setText(String.valueOf(selectedOrder.getOrder_qty()));
            lblItemPrice.setText(String.valueOf(itemBO.findById(selectedOrder.getItem_Id()).getPrice()));
            custIdComboOrder.setValue(selectedOrder.getCust_Id());
            lblItemId.setText(selectedOrder.getItem_Id());
            lblItemName.setText(String.valueOf(itemBO.findById(selectedOrder.getItem_Id()).getItem_Name()));
            //dateLbl.setText(String.valueOf(orderItemDetailModel.findById(selectedOrder.getOrder_Id()).getDate()));

            orderbtnU.setDisable(false);
            orderbtnD.setDisable(false);
        }
    }

    @FXML
    void OrderResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void OrderdeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String order_id = lblOrderId.getText();
        boolean isDeleted = orderBO.deleteOrder(order_id);

        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Order deleted...!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete order...!").show();
        }
    }

    @FXML
    void OrderupdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String order_id = lblOrderId.getText();
        String description = txtOrderDesc.getText();
        int qty = Integer.parseInt(lblOrderedQty.getText());
        String cust_id = custIdComboOrder.getValue();

        OrderDTO orderDTO = new OrderDTO(order_id, description, qty, cust_id);

        boolean isUpdated = orderBO.updateOrder(orderDTO);

        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Order updated...!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update order...!").show();
        }
    }

    @FXML
    void custIdComboOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectCustomerIds = String.valueOf(custIdComboOrder.getSelectionModel().getSelectedItem());
        if (selectCustomerIds != null) {
            Customer customerDTO = customerBO.findById(selectCustomerIds);
        }
    }
    }
