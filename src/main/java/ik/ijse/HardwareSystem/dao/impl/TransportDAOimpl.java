package ik.ijse.HardwareSystem.dao.impl;

import ik.ijse.HardwareSystem.dao.DAO.TransportDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import ik.ijse.HardwareSystem.dao.SQLunit;
import ik.ijse.HardwareSystem.entity.Transport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransportDAOimpl implements TransportDAO {
    public  ArrayList<Transport> table() {
        ArrayList<Transport> Transport = new ArrayList<>();
        try {

            ResultSet resultSet = SQLunit.execute("SELECT * from transportdetails");
            while (resultSet.next()) {

                Transport T = new Transport(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getString(4)

                );
                Transport.add(T);

            }
            return Transport;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<Transport> getall() {
        return null;
    }

    @Override
    public Transport search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  Transport searchBy(String vehicalid) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLunit.execute("SELECT * FROM transportdetails WHERE  id=?", vehicalid);

        Transport T = null;


        if (resultSet.next()) {
            String Id = resultSet.getString(3);
            String time = resultSet.getString(4);
            String date = resultSet.getString(2);
            String VId = resultSet.getString(5);
            String area = resultSet.getString(1);


            T = new Transport(Id, time, date, VId, area);
        }
        return T;
    }


    public Boolean save(Transport entity) throws SQLException, ClassNotFoundException {

        return SQLunit.execute("INSERT INTO transportdetails VALUES(?, ?, ?, ?,?)", entity.getVehicalId(), entity.getTid(), entity.getDate(), entity.getTtime(), entity.getTarea());
    }

    public boolean update(Transport entity) throws SQLException, ClassNotFoundException {

            return SQLunit.execute("UPDATE TransportDetails SET  T_area = ?, t_time = ?, T_id =?,T_Date WHERE id = ?",entity.getVehicalId(),entity.getDate(),entity.getTid(),entity.getTtime(),entity.getTarea());

    }

    public int delete(String id) throws SQLException, ClassNotFoundException {
            return SQLunit.execute("DELETE FROM tansportDetails WHERE id = ?",id);

    }

    public  ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        ObservableList<XYChart.Series<String, Integer>> datalist = FXCollections.observableArrayList();
        ResultSet resultSet = SQLunit.execute("select description , qty from order_detail");

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        while (resultSet.next()) {
            String description = resultSet.getString("description");
            Integer qty = resultSet.getInt("stockLevel");
            series.getData().add(new XYChart.Data<>(description, qty));
        }
        datalist.add(series);
        return datalist;
    }


}





