package ik.ijse.HardwareSystem.dao.impl;

import ik.ijse.HardwareSystem.dao.DAO.OrderdetailDAO;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import ik.ijse.HardwareSystem.dao.SQLunit;
import ik.ijse.HardwareSystem.entity.Orderdetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderdetailDAOimpl implements OrderdetailDAO {
    @Override
    public Orderdetails searchBy(String contactnumber) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Orderdetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public int delete(String id) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public ArrayList<Orderdetails> table() {
        return null;
    }

    @Override
    public ArrayList<Orderdetails> getall() {
        return null;
    }

    @Override
    public Orderdetails search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(Orderdetails entity) throws SQLException, ClassNotFoundException {

        return SQLunit.execute("insert into order_detail values(?,?,?,?,?)", entity.getCode(), entity.getPrice(), entity.getQty(), entity.getDescription(), entity.getAmount());


    }

    @Override
    public ArrayList<String> getalls() {
        return null;
    }
}
