package ik.ijse.HardwareSystem.controller;


import ik.ijse.HardwareSystem.dao.impl.TransportDAOimpl;
import ik.ijse.HardwareSystem.db.DbConnection;
import ik.ijse.HardwareSystem.entity.Transport;
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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TransportFormController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClier;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colArera;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colVehicalid;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableView<Transport> tblTransport;

    @FXML
    private TextField texTime;

    @FXML
    private TextField textId;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtVehical;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();


    private AnchorPane root;
    TransportDAOimpl transportDAOimpl = new TransportDAOimpl();

    @FXML
    void BtnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = this.textId.getText();
        String area = this.txtArea.getText();
        String date = String.valueOf(this.txtDate.getValue());
        String time = this.texTime.getText();
        String vehical = this.txtVehical.getText();

        Boolean i = transportDAOimpl.save(new Transport(id,area,date,time,vehical));

        if (i ) {
            new Alert(Alert.AlertType.CONFIRMATION, "Save Transport").show();
            loadTableData();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();
        }

    }
    @FXML
    void btnClierOnAction(ActionEvent event) {
        this.clearFields();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = this.textId.getText();
        String sql = "DELETE FROM tansportDetails WHERE T_id = ?";

        int i = transportDAOimpl.delete(id);


        try {
            PreparedStatement pstm = DbConnection.getDbConnection().getConnection().prepareStatement(sql);
            pstm.setObject(1, id);
            if (pstm.executeUpdate() > 0) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Transport deleted!", new ButtonType[0])).show();
                this.clearFields();
                loadTableData();
            }
        } catch (SQLException var5) {
            (new Alert(Alert.AlertType.ERROR, var5.getMessage(), new ButtonType[0])).show();
        }

    }

    private void clearFields() {
        this.textId.setText("");
        this.txtDate.setAccessibleText("");
        this.txtArea.setText("");
        this.texTime.setText("");
        this.txtVehical.setText("");
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = this.textId.getText();

        String area = this.txtArea.getText();
        String time = this.texTime.getText();
        String vehical = this.txtVehical.getText();
        String date = this.txtDate.getPromptText();

        Boolean i = transportDAOimpl.update(new Transport(id,area,time,vehical,date));

        if (0>1) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Transport").show();
            loadTableData();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Error").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colArera.setCellValueFactory(new PropertyValueFactory<>("Tarea"));
        colId.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("Ttime"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colVehicalid.setCellValueFactory(new PropertyValueFactory<>("vehicalId"));
        loadTableData();

        Pattern patternId = Pattern.compile("^([0-9]{5,}$)");
        ;  //[0-9 a-z]{10}
        Pattern patternvehical = Pattern.compile("^([0-9]{5,}$)");

        map.put(textId, patternId);

        map.put(txtVehical, patternvehical);

    }


     void loadTableData() {
        ArrayList<Transport> data = transportDAOimpl.table();
        tblTransport.setItems(FXCollections.observableList(data));
    }

    @FXML
    void comVehicalOnAction(ActionEvent event) {


        String id = txtVehical.getText();

        try {
            Transport transportDeto = transportDAOimpl.searchBy(id);

            if (transportDeto != null) {
                textId.setText(transportDeto.getTid());
                txtArea.setText(transportDeto.getTarea());
                texTime.setText(transportDeto.getTtime());
                txtDate.setValue(LocalDate.parse(transportDeto.getDate()));
                txtVehical.setText(transportDeto.getVehicalId());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtVehicalOnACtion(ActionEvent event) {
    }

    public void txtOnKeyRelesed(KeyEvent keyEvent) {
        ValidateUtil.validation(map);

    }


}






