package ik.ijse.HardwareSystem.dao.DAO;

import java.sql.SQLException;

public interface UserDAO {

    public boolean userCheck(String userName, String password) throws SQLException, ClassNotFoundException;
    public boolean save(String user) throws SQLException, ClassNotFoundException ;

    }
