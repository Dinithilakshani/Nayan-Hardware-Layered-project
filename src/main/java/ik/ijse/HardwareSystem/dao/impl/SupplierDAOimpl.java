package ik.ijse.HardwareSystem.dao.impl;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import ik.ijse.HardwareSystem.dao.DAO.SupplierDAO;
import ik.ijse.HardwareSystem.dao.SQLunit;
import ik.ijse.HardwareSystem.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOimpl implements SupplierDAO {

    public  ArrayList<Supplier > table() {
        ArrayList<Supplier> Supplier = new ArrayList<>();
        try {
            ResultSet resultSet = SQLunit.execute("SELECT * from supplier");
            while (resultSet.next()) {

                Supplier T = new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(4),
                        resultSet.getString(3)
                );
                Supplier.add(T);

            }
            return Supplier;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }




    @Override
    public ArrayList<Supplier> getall() {
        return null;
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  Supplier searchBy(String emailAddress) throws SQLException, ClassNotFoundException{

        ResultSet resultSet = SQLunit.execute("SELECT * FROM supplier WHERE  imeladdress=?",emailAddress);
       Supplier T = null;


        if (resultSet.next()) {
            String Supplierid = resultSet.getString(3);
            String Suppliername = resultSet.getString(1);
            String contact = resultSet.getString(4);
            String Address = resultSet.getString(2);


            T = new Supplier(Supplierid, Suppliername, contact, Address);
        }
        return T;
    }

    @Override
    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        return null;
    }


    public Boolean save(Supplier entity ) throws SQLException, ClassNotFoundException {

            return SQLunit.execute("INSERT INTO supplier VALUES(?, ?, ?, ?)",entity.getSupplierCompany(),entity.getDescription(),entity.getDescription(),entity.getEmailAddress(),entity.getNumber());

        }


    public int delete(String id) throws SQLException, ClassNotFoundException {

            return SQLunit.execute("DELETE FROM supplier WHERE SId = ?",id);

    }

    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {


            return SQLunit.execute("UPDATE supplier SET SName = ?, emailaddress = ?, contactnumber = ? WHERE SId = ?",entity.getSupplierCompany(),entity.getNumber(),entity.getDescription(),entity.getEmailAddress());



}

}