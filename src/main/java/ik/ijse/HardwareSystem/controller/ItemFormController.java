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
ItemDAOimpl itemDAOimpl = new ItemDAOimpl();
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
    public void loadTableData() {

        ArrayList<Item> item = itemDAOimpl.table();
        tblItem.setItems(FXCollections.observableList(item));
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
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String code = this.txtCode.getText();
        String description = this.txtDescription.getText();
        int qtyOnHeand = Integer.parseInt(this.txtQty.getText());
        double price = Double.parseDouble(this.txtPrice.getText());


        boolean i = itemDAOimpl.save(new Item(code,description,qtyOnHeand,price));

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

        boolean i = itemDAOimpl.update(new Item(code,description,qty,price));

        if (i ) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Item").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();
        }
    }
    @FXML
    void txtDescriptionSearchOnaction(ActionEvent event) {

        String  description = txtDescription.getText();

        try {
            Item itemDto = itemDAOimpl.searchBy(description);

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


