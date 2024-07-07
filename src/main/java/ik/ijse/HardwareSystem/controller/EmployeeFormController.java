package ik.ijse.HardwareSystem.controller;


import ik.ijse.HardwareSystem.dao.impl.EmployeeDAOimpl;
import ik.ijse.HardwareSystem.entity.Employee;
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

public class EmployeeFormController  implements Initializable {

   @FXML
    private Button BtnClear;

    @FXML
    private Button BtnSave;

    @FXML
    private Button BtnUpdate;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSearch;


    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<?, ?> colAdresss;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableView<Employee> tblEmployee;


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

    EmployeeDAOimpl employeeDAO = new EmployeeDAOimpl();
    @FXML
    void BtnClearOnAction(ActionEvent event) {
        this.clearFields();

    }

    @FXML
    void BtnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

            String id = this.txtid.getText();
            String name = this.txtname.getText();
            String address = this.txtAddress.getText();
            String tel = this.txtNumber.getText();

            Boolean isSaved = employeeDAO.save(new Employee(id, name, address, tel));

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved Successfully").show();
                loadTableData();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error Saving Employee").show();
            }
        }


        private void clearFields() {
        this.txtid.setText("");
        this.txtname.setText("");
        this.txtAddress.setText("");
        this.txtNumber.setText("");
    }


    @FXML
    void BtnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String eid = this.txtid.getText();
        String name = this.txtname.getText();
        String address = this.txtAddress.getText();
        String contactnumber = this.txtNumber.getText();

        boolean i = employeeDAO.update(new Employee(eid,name,address,contactnumber));
        if (i) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Employee").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();


        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNumber.setCellValueFactory(new PropertyValueFactory<>("contactnumber"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAdresss.setCellValueFactory(new PropertyValueFactory<>("address"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        loadTableData();

        Pattern patternId = Pattern.compile("^(E0)[0-9]{1,5}$");
        Pattern patternName = Pattern.compile("^[A-z]{3,}$");  //[0-9 a-z]{10}
        Pattern patternContact = Pattern.compile("^[0-9]{10}$");
        Pattern patternAddress = Pattern.compile("^[A-z] {5}$");

        map.put(txtid, patternId);
        map.put(txtname, patternName);
        map.put(txtAddress, patternAddress);
        map.put(txtNumber,patternContact);




    }



    private void loadTableData() {
        ArrayList<Employee> data = employeeDAO.table();
        tblEmployee.setItems(FXCollections.observableList(data));
    }

    @FXML
    void txtsearchOnAction(ActionEvent event) {
        String  contactnumber = txtNumber.getText();

        try {
            Employee employeeDto = employeeDAO.searchBy(contactnumber);

            if (employeeDto != null) {
                txtid.setText(employeeDto.getId());
                txtname.setText(employeeDto.getName());
                txtNumber.setText(employeeDto.getContactnumber());
                txtAddress.setText(employeeDto.getAddress());


            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtOnKeyReleased(KeyEvent keyEvent) {
       ValidateUtil.validation(map);

    }
}
