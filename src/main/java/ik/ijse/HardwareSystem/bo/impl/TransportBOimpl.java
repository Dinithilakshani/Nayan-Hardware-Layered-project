package ik.ijse.HardwareSystem.bo.impl;

import ik.ijse.HardwareSystem.dao.DAO.TransportDAO;
import ik.ijse.HardwareSystem.dao.DAOFactory;
import ik.ijse.HardwareSystem.dto.TransportDeto;
import ik.ijse.HardwareSystem.entity.Transport;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransportBOimpl {

    TransportDAO transportDAO = (TransportDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.TRANSPORT);

    public ArrayList<Transport> table() {
        ArrayList<Transport> transports = transportDAO.table();
        ArrayList<TransportDeto> transportDetos = new ArrayList<>();
        for (Transport t : transports) {
            TransportDeto transportDeto =new TransportDeto(t.getTarea(),t.getTtime(),t.getDate(),t.getVehicalId(),t.getTid());
        }
        return transports;

    }


    public TransportDeto searchBy(String id) throws SQLException, ClassNotFoundException {

        Transport t = transportDAO.searchBy(id);
        TransportDeto transportDeto = new TransportDeto(t.getTarea(),t.getTtime(),t.getVehicalId(),t.getDate(),t.getDate());

        return transportDeto;

    }


    public boolean save(Transport entity) throws SQLException, ClassNotFoundException {
        return transportDAO.save(entity);
    }

    public boolean update(Transport entity) throws SQLException, ClassNotFoundException {

        return transportDAO.update(entity);
    }

    public int delete(String id) throws SQLException, ClassNotFoundException {
        return transportDAO.delete(id);
    }

    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        return transportDAO.getDataToBarChart();
    }
}
