/*package ik.ijse.HardwareSystem.bo.impl;

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
import ik.ijse.HardwareSystem.dto.OrderDto;
import ik.ijse.HardwareSystem.dto.OrderdetailsDto;
import ik.ijse.HardwareSystem.entity.Item;
import ik.ijse.HardwareSystem.entity.Order;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceorderBOimpl {

    CustomerDAO customerDAO = (CustomerDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO = (ItemDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    OrderdetailDAO orderdetailDAO = (OrderdetailDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    OrderDAO orderDAO = (OrdeDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    public boolean save(String orderId, String date, String customerId, Double amount, ObservableList<OrderdetailsDto> observableList) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            // Save order
            boolean saveOrder = save(new OrderDto(orderId, date, customerId));
            System.out.println(saveOrder);
            if (saveOrder) {
                // Save order details
                boolean saveOrderDetails = save(orderId, observableList);
                if (saveOrderDetails) {
                    // Update item quantities
                    boolean updatedQty = updateItemQty(observableList);
                    if (updatedQty) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
            return false;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    public boolean save(OrderDto Dto) throws SQLException, ClassNotFoundException {

        return orderDAO.save(new Order(Dto.getOrderId(), Dto.getCustomerID(),Dto.getDate()));
    }

    private boolean save(String orderId, ObservableList<OrderdetailsDto> observableList) throws SQLException, ClassNotFoundException {
        // Implement your save logic for OrderdetailsDto here
        // Return true if save is successful, otherwise return false
        return true;
    }

    private boolean updateItemQty(ObservableList<OrderdetailsDto> observableList) throws SQLException, ClassNotFoundException {
        for (OrderdetailsDto dto : observableList) {
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
}*/