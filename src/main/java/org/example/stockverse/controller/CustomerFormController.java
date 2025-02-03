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
import org.example.stockverse.bo.custom.UserBO;
import org.example.stockverse.db.DBConnection;
import org.example.stockverse.dto.CustomerDTO;
import org.example.stockverse.entity.User;
import org.example.stockverse.view.tdm.CustomerTM;
import org.example.stockverse.view.tdm.UserTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CustomerFormController implements Initializable {

    @FXML
    private AnchorPane Apane;

    @FXML
    private Button btnD;

    @FXML
    private Button btnR;

    @FXML
    private Button btnS;

    @FXML
    private Button btnU;


    @FXML
    private Button btnJR;

    @FXML
    private TableColumn<CustomerTM, String> colAddress;

    @FXML
    private TableColumn<CustomerTM, String> colCid;

    @FXML
    private TableColumn<CustomerTM, String> colName;

    @FXML
    private TableColumn<UserTM, String> colUid;

    @FXML
    private TableColumn<CustomerTM, Integer> colcontact;

    @FXML
    private ComboBox<Object> combouID;

    @FXML
    private Label custidlbl;

    @FXML
    private Label labelcontact;

    @FXML
    private Label lbl;

    @FXML
    private Label lbladdress;

    @FXML
    private Label lblblid;

    @FXML
    private Label lblname;

    @FXML
    private TableView<CustomerTM> tbl;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtcontact;

    @FXML
    private TextField txtcustname;

    public static CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    public static UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCid.setCellValueFactory(new PropertyValueFactory<>("Cust_Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Cust_Name"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colUid.setCellValueFactory(new PropertyValueFactory<>("User_Id"));

        try {
            loadUserIds();
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        refreshTable();

        String nextCustomerID = String.valueOf(customerBO.getNextCustomerId());
        lbl.setText(nextCustomerID);

        txtcustname.setText("");
        txtcontact.setText("");
        txtaddress.setText("");

        btnS.setDisable(false);
        btnD.setDisable(true);
        btnU.setDisable(true);
    }

    private void refreshTable() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = customerBO.getAllCustomers();
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDTO customerDTO : customerDTOS) {
            CustomerTM customerTM = new CustomerTM(customerDTO.getCust_Id(), customerDTO.getCust_Name(), customerDTO.getContact(), customerDTO.getAddress(), customerDTO.getUser_Id());
            customerTMS.add(customerTM);
        }
        tbl.setItems(customerTMS);
    }


    @FXML
    void ReportOnAction(ActionEvent event) {
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
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/org/example/stockverse/report/CustomerReport.jrxml"));

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
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Data empty..!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void ResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        combouID.setValue(null);
        combouID.setPromptText("Select User_Id");
        refreshPage();
    }

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Cust_Id = lbl.getText();
        String Cust_Name = txtcustname.getText();
        Integer Contact = Integer.valueOf(txtcontact.getText());
        String Address = txtaddress.getText();
        Object User_Id = combouID.getValue();

        // Define regex patterns for validation
        String namePattern = "^[A-Za-z ]+$";
        String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
        String addressPattern = "^[\\d\\D\\s,.-]+$";

//        Validate each field using regex patterns
        boolean isValidName = Cust_Name.matches(namePattern);
        boolean isValidContact = String.valueOf(Contact).matches(String.valueOf(contactPattern));
        boolean isValidAddress = Address.matches(addressPattern);

        // Reset input field styles
        txtcustname.setStyle(txtcustname.getStyle() + ";-fx-border-color:  #091057;");
        txtcontact.setStyle(txtcontact.getStyle() + ";-fx-border-color: #091057;");
        txtaddress.setStyle(txtaddress.getStyle() + ";-fx-border-color:  #091057;");

        // Highlight invalid fields in red

        if (!isValidName) {
            txtcustname.setStyle(txtcustname.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidContact) {
            txtcontact.setStyle(txtcontact.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidAddress) {
            txtaddress.setStyle(txtaddress.getStyle() + ";-fx-border-color: red;");
        }

        // Save customer if all fields are valid
        if (isValidName && isValidContact && isValidAddress) {
            CustomerDTO customerDTO = new CustomerDTO(Cust_Id, Cust_Name, Contact, Address, (String) User_Id);

            boolean isSaved = customerBO.saveCustomer(customerDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
            }
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Cust_Id = lbl.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            boolean isDeleted = customerBO.deleteCustomer(Cust_Id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }
    }
    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Cust_Id = lbl.getText();
        String Cust_Name = txtcustname.getText();
        Integer Contact = Integer.valueOf(txtcontact.getText());
        String Address = txtaddress.getText();
        Object User_Id = combouID.getValue();

        // Define regex patterns for validation
        String namePattern = "^[A-Za-z ]+$";
        String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
        String addressPattern = "^[\\d\\D\\s,.-]+$";

//        Validate each field using regex patterns
        boolean isValidName = Cust_Name.matches(namePattern);
        boolean isValidContact = String.valueOf(Contact).matches(String.valueOf(contactPattern));
        boolean isValidAddress = Address.matches(addressPattern);

        // Reset input field styles
        txtcustname.setStyle(txtcustname.getStyle() + ";-fx-border-color:  #091057;");
        txtcontact.setStyle(txtcontact.getStyle() + ";-fx-border-color: #091057;");
        txtaddress.setStyle(txtaddress.getStyle() + ";-fx-border-color:  #091057;");

        // Highlight invalid fields in red

        if (!isValidName) {
            txtcustname.setStyle(txtcustname.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidContact) {
            txtcontact.setStyle(txtcontact.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidAddress) {
            txtaddress.setStyle(txtaddress.getStyle() + ";-fx-border-color: red;");
        }

        // Save customer if all fields are valid
        if (isValidName && isValidContact && isValidAddress) {
            CustomerDTO customerDTO = new CustomerDTO(Cust_Id, Cust_Name, Contact, Address, (String) User_Id);

            boolean isSaved = customerBO.updateCustomer(customerDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Customer updated...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to updated customer...!").show();
            }
        }
    }

    @FXML
    void onClickedTable(MouseEvent event) {
        CustomerTM selectedItem = tbl.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lbl.setText(selectedItem.getCust_Id());

            txtcustname.setText(selectedItem.getCust_Name());
            txtcontact.setText(String.valueOf(selectedItem.getContact()));
            txtaddress.setText(selectedItem.getAddress());
            combouID.setValue(selectedItem.getUser_Id());

            btnS.setDisable(true);
            btnD.setDisable(false);
            btnU.setDisable(false);
        }
    }

    private void loadUserIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> userIds = userBO.getAllUserIds();
        ObservableList<Object> observableList = FXCollections.observableArrayList(userIds);
        combouID.setItems(observableList);
    }

    @FXML
    void combouIDOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedUserId = String.valueOf(combouID.getSelectionModel().getSelectedItem());
        if (selectedUserId != null) {
            User userDTO = userBO.findById(selectedUserId);
        }
    }

}

