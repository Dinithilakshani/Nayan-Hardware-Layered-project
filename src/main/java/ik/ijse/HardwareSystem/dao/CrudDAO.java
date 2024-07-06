package ik.ijse.HardwareSystem.dao;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO {
    public T searchBy(String contactnumber) throws SQLException, ClassNotFoundException ;
    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException;

    public  boolean update(T entity) throws SQLException, ClassNotFoundException ;
    public int delete(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<T> table() ;
    public ArrayList<T> getall() ;
    public T search(String id ) throws SQLException, ClassNotFoundException ;
    public Boolean save(T entity) throws SQLException, ClassNotFoundException;


}
