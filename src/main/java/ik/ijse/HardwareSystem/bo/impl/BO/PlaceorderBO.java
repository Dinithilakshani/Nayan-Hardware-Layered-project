package ik.ijse.HardwareSystem.bo.impl.BO;

import ik.ijse.HardwareSystem.entity.Customer;
import ik.ijse.HardwareSystem.entity.Item;
import ik.ijse.HardwareSystem.entity.Order;
import ik.ijse.HardwareSystem.entity.Orderdetails;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceorderBO {
    public boolean saveOrder(String orderId, String date, String customerId, Double
            amount, Orderdetails orderdetails, ObservableList<Orderdetails> observableList) throws SQLException;
     boolean save(Order entity) throws SQLException, ClassNotFoundException;
     boolean updateItemQty(ObservableList<Orderdetails> observableList) throws SQLException, ClassNotFoundException;

    public ArrayList<Item> getall() ;
    public ArrayList<String> getalls() ;
    public Item itemsearch(String code) throws SQLException, ClassNotFoundException ;
    public Item searchBy(String description) throws SQLException, ClassNotFoundException ;
    public Customer search(String id ) throws SQLException, ClassNotFoundException ;









    }
