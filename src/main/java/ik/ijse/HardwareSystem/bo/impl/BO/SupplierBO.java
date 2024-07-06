package ik.ijse.HardwareSystem.bo.impl.BO;

import ik.ijse.HardwareSystem.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO{
    public ArrayList<Supplier> table() ;


    public  Supplier searchBy(String emailAddress) throws SQLException, ClassNotFoundException;


    public int save(Supplier entity ) throws SQLException, ClassNotFoundException ;


    public int delete(String id) throws SQLException, ClassNotFoundException ;

    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException;


}
