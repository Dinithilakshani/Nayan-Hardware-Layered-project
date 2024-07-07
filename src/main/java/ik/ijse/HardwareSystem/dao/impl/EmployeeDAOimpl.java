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

    public Employee searchBy(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLunit.execute("SELECT * FROM employee WHERE eid = ?", id);

        Employee employee = null;
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String contact = resultSet.getString("contactnumber");
            employee = new Employee(id, name, address, contact);
        }
        return employee;
    }

    @Override
    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        return null;
    }

    public Boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLunit.execute("INSERT INTO Employee VALUES(?, ?, ?, ?)", entity.getId(), entity.getName(), entity.getAddress(), entity.getContactnumber());
    }

    public ArrayList<Employee> table() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            ResultSet resultSet = SQLunit.execute("SELECT * from Employee");
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getString("eid"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("contactnumber")
                );
                employees.add(employee);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return employees;
    }

    @Override
    public ArrayList<Employee> getall() {
        return null;
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        return searchBy(id);
    }

    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLunit.execute("UPDATE employee SET name = ?, address = ?, contactnumber = ? WHERE eid = ?", entity.getName(), entity.getAddress(), entity.getContactnumber(), entity.getId());
    }

    @Override
    public int delete(String id) throws SQLException, ClassNotFoundException {
        return 0;
    }


}
