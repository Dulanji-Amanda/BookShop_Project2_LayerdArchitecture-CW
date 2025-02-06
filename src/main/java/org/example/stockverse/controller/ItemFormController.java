package org.example.stockverse.controller;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.stockverse.bo.BOFactory;
import org.example.stockverse.bo.custom.CustomerBO;
import org.example.stockverse.bo.custom.ItemBO;
import org.example.stockverse.bo.custom.OrderBO;
import org.example.stockverse.dto.ItemDTO;
import org.example.stockverse.dto.OrderDTO;
import org.example.stockverse.entity.Item;
import org.example.stockverse.view.tdm.ItemTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private AnchorPane Apaneitem;

    @FXML
    private Button addItemBtn;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private TableColumn<ItemTM, String> colCiditrm;

    @FXML
    private TableColumn<ItemTM, Integer> colqtyitem;

    @FXML
    private TableColumn<ItemTM, String> itemcolname;

    @FXML
    private TableColumn<ItemTM, Double> itemcolprice;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblOrderID;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblQty;

    @FXML
    private TableView<ItemTM> itemtbl;

    @FXML
    private Label totalPriceLbl;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtItemDescription;

    @FXML
    private Button placeOrderBtn;

    @FXML
    private Button resetbtn;

    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    private final ObservableList<ItemTM> itemList = FXCollections.observableArrayList();
    private final ArrayList<ItemTM> itemListArrayList = new ArrayList<>();
    private double totalPrice = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCiditrm.setCellValueFactory(new PropertyValueFactory<>("Item_Id"));
        itemcolname.setCellValueFactory(new PropertyValueFactory<>("Item_Name"));
        colqtyitem.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        itemcolprice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        itemtbl.setItems(itemList);
        try {
            loadNextOrderId();
            loadCustomerId();
            loadItemId();
            refreshPage();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Initialization Error", "Failed to initialize the form: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemId() throws SQLException, ClassNotFoundException {
        ArrayList<String> itemIds = itemBO.getAllItemIds();
        cmbItemId.setItems(FXCollections.observableArrayList(itemIds));
    }

    private void loadCustomerId() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIds = customerBO.getAllCustomerIds();
        cmbCustomerId.setItems(FXCollections.observableArrayList(customerIds));
    }

    private void loadNextOrderId() throws SQLException, ClassNotFoundException {
        lblOrderID.setText(orderBO.getNextOrderId());
    }

    @FXML
    void cmbItemIdOnAction(ActionEvent event) {
        try {
            String selectedItemId = cmbItemId.getValue();
            if (selectedItemId != null) {
                Item itemDTO = itemBO.findById(selectedItemId);
                if (itemDTO != null) {
                    lblItemName.setText(itemDTO.getItem_Name());
                    lblPrice.setText(String.valueOf(itemDTO.getPrice()));
                    lblQty.setText(String.valueOf(itemDTO.getQty()));
                } else {
                    showAlert(Alert.AlertType.WARNING, "Item Not Found", "No item found with the selected ID.");
                }
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch item details: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException {
        clearForm();
        refreshTable();
    }

    private void clearForm() {
        lblItemName.setText("");
        lblPrice.setText("");
        lblQty.setText("");
        txtQty.setText("");
        cmbCustomerId.setValue(null);
        cmbItemId.setValue(null);

        placeOrderBtn.setDisable(false);
        resetbtn.setDisable(true);
    }

    private void refreshTable() throws SQLException {
        itemList.clear();
        itemList.addAll(itemListArrayList);
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        totalPrice = itemListArrayList.stream()
                .mapToDouble(item -> item.getQty() * item.getPrice())
                .sum();
        totalPriceLbl.setText(String.format("Total: %.2f", totalPrice));
    }

    @FXML
    void addItemBtnOnAction(ActionEvent event) {
        try {
            if (cmbItemId.getValue() == null || txtQty.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please select an item and enter a quantity.");
                return;
            }

            String itemId = cmbItemId.getValue();
            String itemName = lblItemName.getText();
            double itemPrice = Double.parseDouble(lblPrice.getText());
            int itemQty = Integer.parseInt(txtQty.getText());

            if (itemQty <= 0) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Quantity must be greater than zero.");
                return;
            }

            ItemTM existingItem = itemListArrayList.stream()
                    .filter(item -> item.getItem_Id().equals(itemId))
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                itemQty += existingItem.getQty();
                itemListArrayList.remove(existingItem);
            }

            itemListArrayList.add(new ItemTM(itemId, itemName, itemQty, itemPrice));
            refreshTable();
            clearForm();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid quantity entered.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to add item: " + e.getMessage());
        }
    }

    @FXML
    void placeOrderBtnOnAction(ActionEvent event) {
        try {
            if (cmbCustomerId.getValue() == null || itemListArrayList.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please select a customer and add items.");
                return;
            }

            ArrayList<ItemDTO> items = new ArrayList<>();
            for (ItemTM item : itemListArrayList) {
                items.add(new ItemDTO(item.getItem_Id(), item.getItem_Name(), item.getQty(), item.getPrice()));
            }

            String orderId = lblOrderID.getText();
            OrderDTO orderDTO = new OrderDTO(orderId, "", items.size(), cmbCustomerId.getValue());

            boolean isOrderSaved = itemBO.saveOrderWithItems(orderDTO, items, totalPrice, txtItemDescription.getText());

            if (isOrderSaved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Order placed successfully!");
                itemListArrayList.clear();
                refreshTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to place the order.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to place the order: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void resetbtnOnAction(ActionEvent event) {
        try {
            refreshPage();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to reset the form: " + e.getMessage());
        }
    }

    @FXML
    void ItemMouseOnClicked(MouseEvent event) {
        ItemTM selectedItem = itemtbl.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            cmbItemId.setValue(selectedItem.getItem_Id());
            lblItemName.setText(selectedItem.getItem_Name());
            lblPrice.setText(String.valueOf(selectedItem.getPrice()));
            txtQty.setText(String.valueOf(selectedItem.getQty()));

            placeOrderBtn.setDisable(true);
            resetbtn.setDisable(false);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
    }
}