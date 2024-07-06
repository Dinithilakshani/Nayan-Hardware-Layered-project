package ik.ijse.HardwareSystem.dao.impl;

import ik.ijse.HardwareSystem.dao.DAO.UserDAO;
import ik.ijse.HardwareSystem.dao.SQLunit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOimpl implements UserDAO {

    public boolean userCheck(String userName, String password) throws SQLException, ClassNotFoundException {


        ResultSet rst = SQLunit.execute("SELECT username,password from admin where username=? && password=?", userName, password);

       return rst.next();

    }

    public  boolean save(String user) throws SQLException, ClassNotFoundException {

        return SQLunit.execute("INSERT INTO admin VALUES(?,?,?)", user);
    }
}


