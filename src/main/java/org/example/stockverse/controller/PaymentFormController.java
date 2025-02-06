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
import org.example.stockverse.bo.BOFactory;
import org.example.stockverse.bo.custom.ItemBO;
import org.example.stockverse.bo.custom.OrderBO;
import org.example.stockverse.bo.custom.PaymentBO;
import org.example.stockverse.dto.OrderDTO;
import org.example.stockverse.dto.PaymentDTO;
import org.example.stockverse.entity.Order;
import org.example.stockverse.entity.Payment;
import org.example.stockverse.view.tdm.OrderTM;
import org.example.stockverse.view.tdm.PaymentTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {

    @FXML
    private AnchorPane ApanePayment;

    @FXML
    private Button btnRPayment;

    @FXML
    private TableColumn<PaymentTM, Double> colAmountPayment;

    @FXML
    private TableColumn<OrderTM, String> colOidPaymnt;

    @FXML
    private TableColumn<PaymentTM, LocalDate> colPaymentDate;

    @FXML
    private TableColumn<PaymentTM, Integer> colcontactPayment;

    @FXML
    private TableColumn<PaymentTM, String> colpaymentId;

    @FXML
    private ComboBox<String> combouIDPayment;

    @FXML
    private Label labelcontactPayment;

    @FXML
    private Label lblPayment;

    @FXML
    private Label lblPaymentDate;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblPaymontAmount;

    @FXML
    private Label lblidPayment;

    @FXML
    private TableView<PaymentTM> tblPayment;

    @FXML
    private TextField txtAmountPayment;

    @FXML
    private TextField txtPaymentDate;

    @FXML
    private TextField txtcontactPayment;

    public static PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);

    public static OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colpaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colAmountPayment.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        colcontactPayment.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colOidPaymnt.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        txtPaymentDate.setEditable(false);
        txtAmountPayment.setEditable(false);
        txtcontactPayment.setEditable(false);

        combouIDPayment.setEditable(false);
        try {
            loadOrderIds();
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        refreshTable();

        String nextPaymentId = paymentBO.getNextPaymentId();
        lblPaymentId.setText(nextPaymentId);

        txtAmountPayment.setText("");
        txtcontactPayment.setText("");
        txtPaymentDate.setText("");

    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        List<Payment> paymentDTOS = paymentBO.getAllPayments();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

        for (Payment paymentDTO : paymentDTOS) {
            PaymentTM paymentTM = new PaymentTM(
                    paymentDTO.getPaymentId(),
                    paymentDTO.getAmount(),
                    paymentDTO.getContact(),
                    paymentDTO.getPaymentDate(),
                    paymentDTO.getOrderId()
                    );
            paymentTMS.add(paymentTM);
        }
        tblPayment.setItems(paymentTMS);
    }

    private void loadOrderIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> orderIds = orderBO.getAllOrderIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(orderIds);
        combouIDPayment.setItems(observableList);
    }

    @FXML
    void combouIDPaymentOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedOrderId = combouIDPayment.getSelectionModel().getSelectedItem();
        if (selectedOrderId != null) {
            Order orderDTO = orderBO.findById(selectedOrderId);
        }
    }

    @FXML
    void ResetOnActionPayment(ActionEvent event) throws SQLException, ClassNotFoundException {
        combouIDPayment.setValue(null);
        combouIDPayment.setPromptText("Select order Id");

        refreshPage();
    }

    @FXML
    void onClickedTable(MouseEvent event) {
        PaymentTM selectedItem = tblPayment.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblPaymentId.setText(selectedItem.getPaymentId());
            txtAmountPayment.setText(String.valueOf(selectedItem.getAmount()));
            txtcontactPayment.setText(String.valueOf(selectedItem.getContact()));
            txtPaymentDate.setText(String.valueOf(selectedItem.getPaymentDate()));
            combouIDPayment.setValue(selectedItem.getOrderId());
        }
    }
}
