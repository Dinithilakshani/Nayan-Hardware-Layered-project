package ik.ijse.HardwareSystem.bo.impl.BO;

import ik.ijse.HardwareSystem.entity.Order;
import ik.ijse.HardwareSystem.entity.Orderdetails;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PlaceorderBO {
    public boolean saveOrder(String orderId, String date, String customerId, Double
            amount, Orderdetails orderdetails, ObservableList<Orderdetails> observableList) throws SQLException;
     boolean save(Order entity) throws SQLException, ClassNotFoundException;
     boolean updateItemQty(ObservableList<Orderdetails> observableList) throws SQLException, ClassNotFoundException;






}
