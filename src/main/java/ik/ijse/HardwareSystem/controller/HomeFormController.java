package ik.ijse.HardwareSystem.controller;

//import ik.ijse.HardwareSystem.dao.impl.ItemDAOimpl;

import ik.ijse.HardwareSystem.db.DbConnection;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HomeFormController {

    public Label lblCustomerCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblEmployeeCount11;

    @FXML
    private Label lblSupplierCount;

    @FXML
    private BarChart<String, Integer> chartItem;



    private int customerCount;
    private int suppliercount;
    private int employeecount;

    private volatile boolean stop = false;


    public void initialize() throws SQLException, ClassNotFoundException {

        timeNow();

        LocalDate date = LocalDate.now();
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("EEEE, MMM dd");
        String formattedDate = date.format(dateformatter);
        lblDate.setText(formattedDate);

        try {
            this.customerCount = this.getCustomerCount();
        } catch (SQLException var2) {
            (new Alert(Alert.AlertType.ERROR, var2.getMessage(), new ButtonType[0])).show();
        }

        this.setCustomerCount(this.customerCount);

        try {
            this.suppliercount = this.getSuppliercount();
        } catch (SQLException var2) {
            (new Alert(Alert.AlertType.ERROR, var2.getMessage(), new ButtonType[0])).show();
        }
        this.setSuppliercount(this.suppliercount);


        try {
            this.employeecount = this.getEmployeecount();
        } catch (SQLException var2) {
            (new Alert(Alert.AlertType.ERROR, var2.getMessage(), new ButtonType[0])).show();
        }
        this.setLblEmployeeCount11(this.employeecount);
        //setDataToBarChart();

    }

    /*public void setDataToBarChart() throws SQLException, ClassNotFoundException {

        ItemDAOimpl itemDAOimpl = new ItemDAOimpl();

            ObservableList<XYChart.Series<String, Integer>> barChartData = itemDAOimpl.getDataToBarChart();

            chartItem.setData(barChartData);

    }
*/

    private void setCustomerCount(int customerCount) {
        System.out.println(customerCount);
        if (customerCount != 0) {
            lblCustomerCount.setText(String.valueOf(customerCount));
        }
    }

    private int getCustomerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS customer_count FROM customer";
        Connection connection = DbConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next() ? resultSet.getInt("customer_count") : 0;
    }


public void timeNow(){
    Thread thread = new Thread(()->{
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        while (!stop){
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
            final String timenow = sdf.format(new Date());
            Platform.runLater(()->{
                lblTime.setText(timenow);
            });
        }
    });
    thread.start();
}


private void setSuppliercount(int suppliercount) {
        System.out.println(suppliercount);
        if (suppliercount != 0) {
            lblSupplierCount.setText(String.valueOf(suppliercount));
        }
    }

    private int getSuppliercount() throws SQLException {
        String sql = "SELECT COUNT(*) AS supplier_count FROM supplier";
        Connection connection = DbConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next() ? resultSet.getInt("supplier_count") : 0;
    }

    private void setLblEmployeeCount11(int employeecount) {
        System.out.println(employeecount);
        if (employeecount != 0) {
            lblEmployeeCount11.setText(String.valueOf(employeecount));
        }
    }

    private int getEmployeecount() throws SQLException {
        String sql = "SELECT COUNT(*) AS employee_count  FROM employee";
        Connection connection = DbConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next() ? resultSet.getInt("employee_count") : 0;
    }

    
}
