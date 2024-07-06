/*package ik.ijse.HardwareSystem.controller;

import ik.ijse.HardwareSystem.dao.DAO.CustomerDAO;
import ik.ijse.HardwareSystem.dao.impl.CustomerDAOimpl;
import ik.ijse.HardwareSystem.dao.impl.ItemDAOimpl;
import ik.ijse.HardwareSystem.dao.impl.OrdeDAOimpl;
import ik.ijse.HardwareSystem.dao.impl.OrderdetailDAOimpl;
import ik.ijse.HardwareSystem.db.DbConnection;
import ik.ijse.HardwareSystem.entity.Customer;
import ik.ijse.HardwareSystem.entity.Item;
import ik.ijse.HardwareSystem.entity.Order;
import ik.ijse.HardwareSystem.entity.Orderdetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> ColAction;

    @FXML
    private TableColumn<?, ?> ColAmount;

    @FXML
    private TableColumn<?, ?> ColOrderId;

    @FXML
    private ComboBox<Integer> ComItemcode;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddtoCard;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnPrint;


    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colOrderdate;


    @FXML
    private TableColumn<?, ?> colUnitprice;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private ComboBox<Customer> comEmail;

    @FXML
    private TableView<Orderdetails> tblOrders;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtOrderaId;

    @FXML
    private TextField txtNetPrice;

    @FXML
    private TextField txtcuustomerId;


    @FXML
    private TextField txtQtY;

    @FXML
    private TextField txtQtyONHENAD;

    @FXML
    private TextField txtUnitprice;

    @FXML
    private TextField txtItemcode;


    @FXML
    private DatePicker txtdate;
    OrderdetailDAOimpl orderdetailDAOimpl = new OrderdetailDAOimpl();
    OrdeDAOimpl ordeDAOimpl = new OrdeDAOimpl();

    CustomerDAOimpl customerDAOimpl = new CustomerDAOimpl();

    ItemDAOimpl itemDAOimpl = new ItemDAOimpl();
    private CustomerDAO customerDAO;

    private ObservableList<Orderdetails> observableList = FXCollections.observableArrayList();
    private double fullTotal = 0;


    @FXML
    void btnAddtoCardOnACtion(ActionEvent event) {
        String orderId = txtOrderaId.getText();
        String code = txtItemcode.getText();
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQtY.getText());
        double unitPrice = Double.parseDouble(txtUnitprice.getText());

        double amount = (unitPrice * qty);

        Orderdetails orderDto = new Orderdetails(orderId, code, description, unitPrice, qty, (unitPrice * qty));
        observableList.add(orderDto);
        tblOrders.setItems(observableList);
        txtNetPrice.setText(String.valueOf(fullTotal));
    }


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String orderId = txtOrderaId.getText();
        String date = String.valueOf(txtdate.getValue());

        String customerId = txtcuustomerId.getText();

        double total = 10.0;

        int i = ordeDAOimpl.save(new Order(orderId, date, customerId));
        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "save Order..!").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "save Order..!").show();
        }
    }

    @FXML
    void comEmailOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        Customer customerDto = customerDAOimpl.search(String.valueOf(comEmail.getValue()));
        txtcuustomerId.setText(customerDto.getName());

    }

    @FXML
    void comOrderidOnACtion(ActionEvent event) throws SQLException, ClassNotFoundException {
        String code = String.valueOf(ComItemcode.getValue());

        Item itemDto = itemDAOimpl.search(code);
        txtDescription.setText(itemDto.getDesctription());
        txtUnitprice.setText(String.valueOf(itemDto.getPrice()));
        txtQtyONHENAD.setText(String.valueOf(itemDto.getQtyOnHeand()));


    }

    @FXML
    void txtOnKeyRelesed(KeyEvent event) {

    }

    @FXML
    void btnPrintOnACtion(ActionEvent event) throws SQLException, JRException {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/Report/orders.jrxml");
            if (inputStream != null) {
                JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                Map<String, Object> data = new HashMap<>();
                data.put("Customer Email", comEmail.getValue());
                data.put("NetTotal", "3000");

                Connection connection = DbConnection.getDbConnection().getConnection();
                if (connection != null) {
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, connection);
                    JasperViewer.viewReport(jasperPrint, false);
                } else {
                    System.err.println("Failed to obtain database connection.");
                }
            } else {
                System.err.println("Failed to load orders.jrxml");
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCustomerValues();
        setItemCOde();

        ColOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitprice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        ColAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void setItemCOde() {

        ArrayList<Item> all = itemDAOimpl.getall();
        ArrayList<Integer> itemCode = new ArrayList<>();

        for (Item itemDto : all) {
            itemCode.add(Integer.valueOf(itemDto.getCode()));
        }
        txtItemcode.setText(FXCollections.observableList(itemCode).toString());
    }


    private void setCustomerValues() {

        ArrayList<Customer> allEmail = customerDAOimpl.getall();
        comEmail.setItems(FXCollections.observableList(allEmail));
    }


    @FXML
    void comitemcodeOnACtion(ActionEvent event) throws SQLException, ClassNotFoundException {
        String code = String.valueOf(ComItemcode.getValue());

        Item itemDto = itemDAOimpl.search(code);
        txtDescription.setText(itemDto.getDesctription());
        txtUnitprice.setText(String.valueOf(itemDto.getPrice()));
        txtQtyONHENAD.setText(String.valueOf(itemDto.getQtyOnHeand()));
        ComItemcode.setValue(Integer.valueOf(itemDto.getCode()));
    }


    public void btnAddOnAction(ActionEvent event) {

    }


    public void txtDescriptionOnAction(ActionEvent event) {
        String code = txtItemcode.getText();
        try {
            Item itemDto = itemDAOimpl.searchBy(code);

            if (itemDto != null) {

                txtUnitprice.setText(String.valueOf(itemDto.getPrice()));
                txtQtyONHENAD.setText(String.valueOf(itemDto.getQtyOnHeand()));
                txtItemcode.setText(itemDto.getCode());

            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


}
*/







