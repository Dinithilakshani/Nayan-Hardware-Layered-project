package ik.ijse.HardwareSystem.dao.DAO;

import ik.ijse.HardwareSystem.dao.CrudDAO;
import ik.ijse.HardwareSystem.entity.Orderdetails;

import java.sql.SQLException;

public interface OrderdetailDAO extends CrudDAO<Orderdetails> {

    public boolean saves(Orderdetails orderdetails) throws SQLException, ClassNotFoundException;

}
