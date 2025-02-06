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
import org.example.stockverse.bo.custom.CustomerBO;
import org.example.stockverse.bo.custom.ItemBO;
import org.example.stockverse.dto.ItemDTO;
import org.example.stockverse.entity.Item;
import org.example.stockverse.view.tdm.ItemTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddItemFormController implements Initializable {

    @FXML
    private AnchorPane Apaneitem;

    @FXML
    private TableColumn<?, ?> colCiditrm;

    @FXML
    private TableColumn<?, ?> colqtyitem;

    @FXML
    private Button ibtnD;

    @FXML
    private Button ibtnR;

    @FXML
    private Button ibtnS;

    @FXML
    private Button ibtnU;

    @FXML
    private Label iemlblQty;

    @FXML
    private TableColumn<?, ?> itemcolname;

    @FXML
    private TableColumn<?, ?> itemcolprice;

    @FXML
    private Label itemidlbl;

    @FXML
    private Label itemlblname;

    @FXML
    private Label itemlbprice;

    @FXML
    private TableView<ItemTM> itemtbl;

    @FXML
    private Label lblItem;

    @FXML
    private TextField txritem;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtprice;

    public static ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCiditrm.setCellValueFactory(new PropertyValueFactory<>("Item_Id"));
        itemcolname.setCellValueFactory(new PropertyValueFactory<>("Item_Name"));
        colqtyitem.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        itemcolprice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        refreshTable();

        String nextItemId = itemBO.getNextItemId();
        lblItem.setText(nextItemId);

        txritem.setText("");
        txtprice.setText("");
        txtQty.setText("");

        ibtnS.setDisable(false);

        ibtnD.setDisable(true);
        ibtnU.setDisable(true);
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS = itemBO.getAllItems();
        ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();

        for (ItemDTO itemDTO : itemDTOS) {
            ItemTM itemTM = new ItemTM(
                    itemDTO.getItem_Id(),
                    itemDTO.getItem_Name(),
                    itemDTO.getQty(),
                    itemDTO.getPrice()
            );
            itemTMS.add(itemTM);
        }
        itemtbl.setItems(itemTMS);
    }

    @FXML
    void ItemMouseOnClicked(MouseEvent event) {
        ItemTM selectedItem = itemtbl.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblItem.setText(selectedItem.getItem_Id());
            txritem.setText(selectedItem.getItem_Name());
            txtprice.setText(selectedItem.getPrice().toString());
            txtQty.setText(String.valueOf(selectedItem.getQty()));

            ibtnS.setDisable(true);

            ibtnU.setDisable(false);
            ibtnD.setDisable(false);
        }
    }

    @FXML
    void iResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void iSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String item_id = lblItem.getText();
        String name = txritem.getText();
        Double price = Double.valueOf(txtprice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        if (!lblItem.getText().isEmpty() && !txritem.getText().isEmpty() && !txtQty.getText().isEmpty() && !txtprice.getText().isEmpty()) {
            ItemDTO itemDTO = new ItemDTO(item_id, name, qty, price);

            boolean isSaved = itemBO.saveItem(itemDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Item saved...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save item...!").show();
            }
        }
    }

    @FXML
    void ideleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Item_Id = lblItem.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            boolean isDeleted = itemBO.deleteItem(Item_Id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Item deleted...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete item...!").show();
            }
        }
    }

    @FXML
    void iupdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String item_id = lblItem.getText();
        String name = txritem.getText();
        Double price = Double.valueOf(txtprice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        if (!lblItem.getText().isEmpty() && !txritem.getText().isEmpty() && !txtQty.getText().isEmpty() && !txtprice.getText().isEmpty()) {
            ItemDTO itemDTO = new ItemDTO(item_id, name, qty, price);

            boolean isSaved = itemBO.updateItem(itemDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Item updated...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update item...!").show();
            }
        }

    }

}