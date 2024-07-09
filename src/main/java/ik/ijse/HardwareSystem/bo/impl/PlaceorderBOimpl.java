package ik.ijse.HardwareSystem.bo.impl;

import ik.ijse.HardwareSystem.bo.impl.BO.PlaceorderBO;
import ik.ijse.HardwareSystem.dao.DAO.CustomerDAO;
import ik.ijse.HardwareSystem.dao.DAO.ItemDAO;
import ik.ijse.HardwareSystem.dao.DAO.OrderDAO;
import ik.ijse.HardwareSystem.dao.DAO.OrderdetailDAO;
import ik.ijse.HardwareSystem.dao.DAOFactory;
import ik.ijse.HardwareSystem.dao.impl.CustomerDAOimpl;
import ik.ijse.HardwareSystem.dao.impl.ItemDAOimpl;
import ik.ijse.HardwareSystem.dao.impl.OrdeDAOimpl;
import ik.ijse.HardwareSystem.dao.impl.OrderdetailDAOimpl;
import ik.ijse.HardwareSystem.db.DbConnection;
import ik.ijse.HardwareSystem.entity.Customer;
import ik.ijse.HardwareSystem.entity.Item;
import ik.ijse.HardwareSystem.entity.Order;
import ik.ijse.HardwareSystem.entity.Orderdetails;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceorderBOimpl implements PlaceorderBO  {

    CustomerDAO customerDAO = (CustomerDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO = (ItemDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    OrderdetailDAO orderdetailDAO = (OrderdetailDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    OrderDAO orderDAO = (OrdeDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    public boolean saveOrder(String orderId, String date, String customerId, Double
            amount, Orderdetails orderdetails,ObservableList<Orderdetails> observableList) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);
Order order = new Order(orderId, date,customerId,amount);

            //String orderId, String date, String customerID, String email
            boolean saveOrder = save(order);
            System.out.println(saveOrder);
            if (saveOrder == true) {



                boolean saveOrderDetails = orderdetailDAO.saves(orderdetails);
                if (saveOrderDetails == true) {

                    boolean b = updateItemQty(observableList);

                    if (b == true) {
                        connection.commit();
                        return true;
                    }
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return orderDAO.save(entity);
    }


    public boolean updateItemQty(ObservableList<Orderdetails> observableList) throws SQLException, ClassNotFoundException {
        for (Orderdetails dto : observableList) {
            ItemDAOimpl itemDAOimpl = new ItemDAOimpl();
            Item itemDto = itemDAOimpl.search(dto.getCode());
            boolean result = itemDAOimpl.update(new Item(dto.getCode(), dto.getDescription(), itemDto.getQtyOnHeand() - dto.getQty(), dto.getPrice()));
            System.out.println(result);
            if (!result) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<String> getalls() {
        return null;
    }

    @Override
    public ArrayList<Item> getall() {
        return null;
    }

    @Override
    public Item search(String code) {
        return null;
    }

    @Override
    public Item searchBy(String description) {
        return null;
    }

    @Override
    public Customer searchs(String s) {
        return null;
    }
}