package ik.ijse.HardwareSystem.controller;


import ik.ijse.HardwareSystem.dao.impl.SupplierDAOimpl;
import ik.ijse.HardwareSystem.entity.Supplier;
import ik.ijse.HardwareSystem.util.ValidateUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SupplierFormController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnback;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colDescription;


    @FXML
    private TableColumn<?, ?> colNum;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtname;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    private AnchorPane root;

    SupplierDAOimpl supplierDAO  = new SupplierDAOimpl();





    @FXML
    void btnClearOnAction(ActionEvent event) {this.clearFields();

    }





    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = this.txtid.getText();
        String name = this.txtname.getText();
        String address = this.txtAddress.getText();
        String tel = this.txtNumber.getText();



        Boolean i = supplierDAO.save(new Supplier(id,name,address,tel));

        if(i){
            new Alert(Alert.AlertType.CONFIRMATION,"Save Supplier").show();

        }else{
            new Alert(Alert.AlertType.ERROR,"Somthing Error").show();
        }

    }



    private void clearFields() {
        this.txtid.setText("");
        this.txtname.setText("");
        this.txtAddress.setText("");
        this.txtNumber.setText("");
    }





        @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String eid = this.txtid.getText();
        String name = this.txtname.getText();
        String emailaddress = this.txtAddress.getText();
        String contactnumber = this.txtNumber.getText();


            boolean i = supplierDAO.update(new Supplier(name,emailaddress,contactnumber,eid));

            if(i){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Supplier").show();

            }else{
                new Alert(Alert.AlertType.ERROR,"Somthing Error").show();
            }



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmail.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));
        colNum.setCellValueFactory(new PropertyValueFactory<>("Number"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Number"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("SupplierCompany"));


        loadTableData();

        Pattern patternId = Pattern.compile("^(S0)[0-9]{1,5}$");
        Pattern patternName = Pattern.compile("^[A-z]{3,}$");
        Pattern patternContact = Pattern.compile("^[0-9]{1,10}$");
        Pattern patterEmail = Pattern.compile("^[A-z][1-9]$");

        map.put(txtid, patternId);
        map.put(txtname, patternName);
        map.put(txtAddress, patterEmail);
        map.put(txtNumber, patternContact);

    }

    private void loadTableData() {
        ArrayList<Supplier> data = supplierDAO.table();
        tblSupplier.setItems(FXCollections.observableList(data));
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String  emailAddress = txtAddress.getText();

        try {
            Supplier supplierDto = supplierDAO.searchBy(emailAddress);

            if (supplierDto != null) {
                txtid.setText(supplierDto.getSupplierCompany());
                txtname.setText(supplierDto.getDescription());
                txtNumber.setText(supplierDto.getNumber());
                txtAddress.setText(supplierDto.getEmailAddress());


            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtOnKeyRelesed(KeyEvent keyEvent) {
        ValidateUtil.validation(map);

    }
}
