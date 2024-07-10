package ik.ijse.HardwareSystem.dao.impl;

import ik.ijse.HardwareSystem.dao.DAO.OrderdetailDAO;
import ik.ijse.HardwareSystem.dao.SQLunit;
import ik.ijse.HardwareSystem.entity.Item;
import ik.ijse.HardwareSystem.entity.Orderdetails;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

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

    @Override
    public boolean save(Orderdetails entity) throws SQLException, ClassNotFoundException {

        return false;
    }



    @Override
    public ArrayList<String> getalls() {
        return null;
    }

    @Override
    public Item itemsearch(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saves( Orderdetails orderdetails) throws SQLException, ClassNotFoundException {
        return SQLunit.execute("insert into order_detail (orderId,description,qty,unitprice,amount) values(?,?,?,?,?)",orderdetails.getOrderId(),orderdetails.getDescription(),orderdetails.getQty(),orderdetails.getPrice(),orderdetails.getAmount());
    }
}
