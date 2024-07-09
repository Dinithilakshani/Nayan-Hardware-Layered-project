package ik.ijse.HardwareSystem.bo.impl;

import ik.ijse.HardwareSystem.bo.impl.BO.EmployeeBO;
import ik.ijse.HardwareSystem.dao.DAO.EmployeeDAO;
import ik.ijse.HardwareSystem.dao.DAOFactory;
import ik.ijse.HardwareSystem.dao.impl.EmployeeDAOimpl;
import ik.ijse.HardwareSystem.dto.EmployeeDto;
import ik.ijse.HardwareSystem.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EMployeeBOimpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    public EmployeeDto searchBy(String id) throws SQLException, ClassNotFoundException {

        Employee e = employeeDAO.searchBy(id);
        EmployeeDto employee = new EmployeeDto(e.getId(),e.getAddress(),e.getName(),e.getContactnumber());
        return employee;
    }




    public boolean save(Employee entity ) throws SQLException, ClassNotFoundException {
return employeeDAO.save(entity);
    }

    @Override
    public int delete(String id) throws SQLException, ClassNotFoundException {
        return 0;
    }


    public ArrayList<Employee> table() {
        return employeeDAO.table();
    }



    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        return  employeeDAO.search(id);
    }


    public boolean update(Employee entity)throws SQLException, ClassNotFoundException  {
        return  employeeDAO.update(entity);
}





}
