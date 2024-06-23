package org.example.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.example.dao.CustomerDAOimpl;
import org.example.dto.CustomerDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class CustomerController implements Initializable {
    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;


    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;


    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNum;

    @FXML
    private TableView<CustomerDto> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    @FXML
    void btnClearOnAction(ActionEvent event) {
        this.clearFields();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String contactnumber = this.txtNumber.getText();

        CustomerDAOimpl customerDAO = new CustomerDAOimpl();
        int i = customerDAO.delete(contactnumber);

        if (i < 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Customer").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();
        }


    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = this.txtId.getText();
        String name = this.txtName.getText();
        String address = this.txtAddress.getText();
        String tel = this.txtNumber.getText();
        String email = this.txtEmail.getText();

        CustomerDAOimpl customerDAO = new CustomerDAOimpl();
        int i = customerDAO.save(id, name, address, tel, email);

        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Save Customer").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();

        }

    }


    private void clearFields() {
        this.txtId.setText("");
        this.txtName.setText("");
        this.txtAddress.setText("");
        this.txtNumber.setText("");
        this.txtEmail.setText("");

        CustomerDAOimpl customerDAO = new CustomerDAOimpl();

        //int i = customerModel.Clearcustomer (txtEmail,txtId,txtName,txtAddress,txtNumber);
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String eid = this.txtId.getText();
        String name = this.txtName.getText();
        String address = this.txtAddress.getText();
        String contactnumber = this.txtNumber.getText();
        String email = this.txtEmail.getText();


        CustomerDto customerDto = new CustomerDto(eid,name,address,contactnumber,email);
        boolean isUpdated = CustomerDAOimpl.update(customerDto);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNum.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        loadTableData();

        Pattern patternId = Pattern.compile("^(C0)[0-9]{1,5}$");
        Pattern patternnumber = Pattern.compile("^(1,9),{10}$");


        map.put(txtId, patternId);
        map.put(txtNumber,patternnumber);



    }

    private void loadTableData() {
        CustomerDAOimpl customerDAO = new CustomerDAOimpl();

        ArrayList<CustomerDto> data = customerDAO.tble();
        tblCustomer.setItems(FXCollections.observableList(data));
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String  contactnumber = txtNumber.getText();

        try {
            CustomerDto customerDto = CustomerDAOimpl.searchById(contactnumber);

            if (customerDto != null) {
                txtId.setText(customerDto.getId());
                txtName.setText(customerDto.getName());
                txtNumber.setText(customerDto.getTel());
                txtAddress.setText(customerDto.getAddress());
                txtEmail.setText(customerDto.getEmail());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void OnKeyRekesed(KeyEvent keyEvent) {
       // ValidateUtil.validation(map);

    }
}











