/*package lk.ijse.hardwareManagment.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.hardwareManagment.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemFormController {

        @FXML
        private Button btnClear;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML
        private TableColumn<?, ?> colCode;

        @FXML
        private TableColumn<?, ?> colDescription;

        @FXML
        private TableColumn<?, ?> colQty;

        @FXML
        private TableView<?> tblItem;

        @FXML
        private TextField txtCode;

        @FXML
        private TextField txtDescription;


        @FXML
        void btnClearOnACtion(ActionEvent event) {
            this.clearFields()

        }

        private void clearFields() {
            this.txtid.setText("");
            this.txtname.setText("");
            this.txtAddress.setText("");
            this.txtNumber.setText("");
        }
        }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {

        }

        @FXML
        void btnSaveOnAction(ActionEvent event) {
            String id = this.txtid.getText();
            String name = this.txtname.getText();
            String address = this.txtAddress.getText();
            String tel = this.txtNumber.getText();
            String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?)";

            try {
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, id);
                pstm.setObject(4, name);
                pstm.setObject(3, address);
                pstm.setObject(2, tel);
                boolean isSaved = pstm.executeUpdate() > 0;
                if (isSaved) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var10) {
                (new Alert(Alert.AlertType.ERROR, var10.getMessage(), new ButtonType[0])).show();
            }

        }

        }

        @FXML
        void btnUpdateOnACtion(ActionEvent event) {
            String eid = this.txtid.getText();
            String name = this.txtname.getText();
            String address = this.txtAddress.getText();
            String contactnumber = this.txtNumber.getText();
            String sql = "UPDATE Employee SET name = ?, address = ?, contactnumber = ? WHERE eid = ?";

            try {
                PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
                pstm.setObject(4, name);
                pstm.setObject(3, address);
                pstm.setObject(2, contactnumber);
                pstm.setObject(1, eid);
                if (pstm.executeUpdate() > 0) {
                    (new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!", new ButtonType[0])).show();
                    this.clearFields();
                }
            } catch (SQLException var8) {
                (new Alert(Alert.AlertType.ERROR, var8.getMessage(), new ButtonType[0])).show();
            }

        }


        }

    }*/
package ik.ijse.HardwareSystem.controller;

import ik.ijse.HardwareSystem.dao.impl.ItemDAOimpl;
import ik.ijse.HardwareSystem.entity.Item;
import ik.ijse.HardwareSystem.util.ValidateUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ItemFormController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    ItemDAOimpl itemDAO = new ItemDAOimpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("desctription"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("QtyOnHeand"));

        loadTableData();

        Pattern patternId = Pattern.compile("^(I0)[0-9]{1,5}$");

        Pattern patterqty = Pattern.compile("^{100}$");

        map.put(txtCode, patternId);

        map.put(txtQty,patterqty);
    }


    private void loadTableData() {

        ArrayList<Item> data = itemDAO.table();
        tblItem.setItems(FXCollections.observableList( data));




    }


    @FXML
    void btnClearOnACtion(ActionEvent event) {
        this.clearFields();

    }

    private void clearFields() {
        this.txtCode.setText("");
        this.txtDescription.setText("");
        this.txtQty.setText("");
        this.txtPrice.setText("");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = this.txtCode.getText();
        String sql = "DELETE FROM item WHERE code = ?";


        int i = itemDAO.delete(id);


        if(i>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Item").show();

        }else{
            new Alert(Alert.AlertType.ERROR,"Somthing Error").show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String code = this.txtCode.getText();
        String description = this.txtDescription.getText();
        int qtyOnHeand = Integer.parseInt(this.txtQty.getText());
        double price = Double.parseDouble(this.txtPrice.getText());

        ItemDAOimpl itemDAOimpl = new ItemDAOimpl();
        Boolean i = itemDAOimpl.save(new Item(code,description,qtyOnHeand,price));

        if(i){
            new Alert(Alert.AlertType.CONFIRMATION,"Save Item").show();

        }else{
            new Alert(Alert.AlertType.ERROR,"Somthing Error").show();
        }
    }


    @FXML
    void btnUpdateOnACtion(ActionEvent event) throws SQLException, ClassNotFoundException {
        String code = this.txtCode.getText();
        String description = this.txtDescription.getText();
        int qty = Integer.parseInt(this.txtQty.getText());
        double  price = Double.parseDouble(this.txtPrice.getText());


        Boolean i = itemDAO.update(new Item(code,description,qty,price));

        if (0>1) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Item").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();
        }
    }

        @FXML
        void txtSearchOnAction(ActionEvent event) {
            String  code = txtCode.getText();

            try {
                Item itemDto = itemDAO.searchBy(code);

                if (itemDto != null) {
                    txtCode.setText(itemDto.getCode());
                    txtDescription.setText(itemDto.getDesctription());
                    txtPrice.setText(String.valueOf(itemDto.getPrice()));
                    txtQty.setText(String.valueOf(itemDto.getQtyOnHeand()));

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
