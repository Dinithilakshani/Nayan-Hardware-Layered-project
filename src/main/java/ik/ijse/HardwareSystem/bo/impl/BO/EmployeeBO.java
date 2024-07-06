package ik.ijse.HardwareSystem.bo.impl.BO;

import ik.ijse.HardwareSystem.dto.EmployeeDto;
import ik.ijse.HardwareSystem.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public EmployeeDto searchBy(String id) throws SQLException, ClassNotFoundException;





    public boolean save(Employee entity ) throws SQLException, ClassNotFoundException;



    public int delete(String id) throws SQLException, ClassNotFoundException;



    public ArrayList<Employee> table() ;




    public Employee search(String id) throws SQLException, ClassNotFoundException;


    public boolean update(Employee entity)throws SQLException, ClassNotFoundException;



}
