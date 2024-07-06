package ik.ijse.HardwareSystem.bo.impl.BO;

import ik.ijse.HardwareSystem.dto.CustomerDto;
import ik.ijse.HardwareSystem.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomeBo extends SuperBO {
    public CustomerDto searchBy(String contactnumber) throws SQLException, ClassNotFoundException ;


    public boolean update(Customer dto) throws SQLException, ClassNotFoundException ;


    public int delete(String id) throws SQLException, ClassNotFoundException;


    public ArrayList<CustomerDto> table();



    public ArrayList<CustomerDto> getall() ;


    public Customer search(String id) throws SQLException, ClassNotFoundException;



    public boolean save(CustomerDto dto) throws SQLException, ClassNotFoundException ;


}
