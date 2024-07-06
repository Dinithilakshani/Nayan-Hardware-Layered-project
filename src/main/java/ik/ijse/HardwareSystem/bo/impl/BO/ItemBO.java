package ik.ijse.HardwareSystem.bo.impl.BO;

import ik.ijse.HardwareSystem.entity.Item;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO{
    public Item searchBy(String code) throws SQLException, ClassNotFoundException ;

    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException ;

    public int delete(String id) throws SQLException, ClassNotFoundException;

    public int save(Item entity) throws SQLException, ClassNotFoundException;

    public boolean update(Item entity) throws SQLException, ClassNotFoundException;

    public ArrayList<Item> table();


    public Item search(String code) throws SQLException, ClassNotFoundException;


    public ArrayList<Item> getall() ;


    }

