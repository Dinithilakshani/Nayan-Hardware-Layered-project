package ik.ijse.HardwareSystem.dao.impl;

import ik.ijse.HardwareSystem.dao.DAO.CustomerDAO;
import ik.ijse.HardwareSystem.dao.SQLunit;
import ik.ijse.HardwareSystem.entity.Customer;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOimpl implements CustomerDAO {
    public  Customer searchBy(String contactnumber) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLunit.execute("SELECT * FROM customer WHERE  contactnumber=?");

        Customer T = null;

        if (resultSet.next()) {
            String customerId = resultSet.getString(2);
            String customerName = resultSet.getString(1);
            String contact = resultSet.getString(4);
            String customerAddress = resultSet.getString(3);
            String customerEmail = resultSet.getString(5);


            T = new Customer(customerId, customerName, contact, customerAddress, customerEmail);
        }
        return T;
    }

    @Override
    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLunit.execute("update customer set name = ?,address = ?,email = ? , contactnumber = ? where id =?",entity.getId(),entity.getAddress(),entity.getEmail(),entity.getAddress());

    }


    public int delete(String id) throws SQLException, ClassNotFoundException {


            return SQLunit.execute("DELETE FROM customer WHERE contactnumber = ?",id);

    }


        public ArrayList<Customer> table() {
            ArrayList<Customer> Customer = new ArrayList<>();


            try {
                ResultSet resultSet = SQLunit.execute("SELECT * from Customer");
                while (resultSet.next()) {


                    Customer T = new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)


                    );
                    Customer.add(T);


                }
                return Customer;

            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);

            }


           }



    @Override
    public ArrayList<Customer> getall() {

        ArrayList<Customer> allEmail = new ArrayList<>();
        try {
            ResultSet resultSet = SQLunit.execute("select email from customer");

            while (resultSet.next()) {
        new Customer(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5)

        );
            }
            return allEmail;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }





    public Customer search(String email ) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLunit.execute("SELECT * FROM customer WHERE  email=?",email);

        Customer T = null;


        if (resultSet.next()) {
            String customerId = resultSet.getString(2);
            String customerName = resultSet.getString(1);
            String contact = resultSet.getString(4);
            String customerAddress = resultSet.getString(3);
            String customerEmail = resultSet.getString(5);


            T = new Customer(customerId, customerName, contact, customerAddress, customerEmail);
        }
        return T;


    }

    public Boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return   SQLunit.execute("INSERT INTO customer VALUES(?, ?, ?, ?,?)",entity.getName(),entity.getId(),entity.getAddress(),entity.getEmail(),entity.getContact());

    }
}






