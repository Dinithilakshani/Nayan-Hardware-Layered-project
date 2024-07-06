package ik.ijse.HardwareSystem.bo.impl.BO;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import ik.ijse.HardwareSystem.entity.Transport;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TransportBO extends SuperBO{
    public ArrayList<Transport> table() ;


    public Transport searchBy(String id) throws SQLException, ClassNotFoundException ;


    public int save(Transport entity) throws SQLException, ClassNotFoundException ;

    public boolean update(Transport entity) throws SQLException, ClassNotFoundException ;

    public int delete(String id) throws SQLException, ClassNotFoundException ;

    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException ;

}
