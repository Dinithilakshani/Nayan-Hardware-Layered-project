package ik.ijse.HardwareSystem.dao.impl;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import ik.ijse.HardwareSystem.dao.DAO.EmployeeDAO;
import ik.ijse.HardwareSystem.dao.SQLunit;
import ik.ijse.HardwareSystem.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOimpl implements EmployeeDAO {
    public  Employee searchBy(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLunit.execute("SELECT * FROM employee WHERE  contactnumber=?",id);

        Employee T = null;


        if (resultSet.next()) {
            String Supplierid = resultSet.getString(3);
            String Suppliername = resultSet.getString(1);
            String contact = resultSet.getString(4);
            String Address = resultSet.getString(2);


            T = new Employee(Supplierid, Suppliername, contact, Address);
        }
        return T;
    }

    @Override
    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        return null;
    }


    public Boolean save(Employee  entity ) throws SQLException, ClassNotFoundException {

            return SQLunit.execute("INSERT INTO Employee VALUES(?, ?, ?,?)",entity.getName(),entity.getId(),entity.getContactnumber(),entity.getAddress());
    }



    public int delete(String id) throws SQLException, ClassNotFoundException {

            return SQLunit.execute("DELETE FROM Employee WHERE eid = ?",id);

    }



    public ArrayList<Employee> table() {
        ArrayList<Employee> Employee = new ArrayList<>();

        try {

            ResultSet resultSet = SQLunit.execute("SELECT * from Employee");
            while (resultSet.next()) {

                Employee T = new Employee(
                        resultSet.getString(4),
                        resultSet.getString(3),
                        resultSet.getString(1),
                        resultSet.getString(2)

                );
                Employee.add(T);

            }
            return Employee;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Employee> getall() {
        return null;
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


    public boolean update(Employee entity)throws SQLException, ClassNotFoundException  {
        return SQLunit.execute("UPDATE employee SET contactnumber = ?, address = ?,  name = ? WHERE eid = ?",entity.getName(),entity.getContactnumber(),entity.getAddress(),entity.getId());

    }
}




