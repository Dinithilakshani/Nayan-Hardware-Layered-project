package ik.ijse.HardwareSystem.dao.impl;

import ik.ijse.HardwareSystem.dao.DAO.OrderDAO;
import ik.ijse.HardwareSystem.dao.SQLunit;
import ik.ijse.HardwareSystem.entity.Order;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;
import java.util.ArrayList;



public class OrdeDAOimpl implements OrderDAO  {


    @Override
    public Order searchBy(String contactnumber) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public ArrayList<Order> table() {
        return null;
    }

    @Override
    public ArrayList<Order> getall() {
        return null;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }



    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        System.out.println(entity);
        return SQLunit.execute("insert into orders values(?,?,?)",entity.getOrderId(),entity.getDate(),entity.getCustomerID());
    }

    @Override
    public ArrayList<String> getalls() {
        return null;
    }




}









