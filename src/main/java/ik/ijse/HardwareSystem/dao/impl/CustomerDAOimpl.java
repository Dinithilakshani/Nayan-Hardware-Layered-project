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


        ResultSet resultSet = SQLunit.execute("SELECT * FROM customer WHERE  contactnumber=?",contactnumber);

        Customer customerDto = null;


        if (resultSet.next()) {
            String customerId = resultSet.getString(2);
            String customerName = resultSet.getString(1);
            String contact = resultSet.getString(4);
            String customerAddress = resultSet.getString(3);
            String customerEmail = resultSet.getString(5);


            customerDto = new Customer(customerId, customerName, contact, customerAddress, customerEmail);
        }
        return customerDto;
    }







@Override
    public ObservableList<XYChart.Series<String, Integer>> getDataToBarChart() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLunit.execute("UPDATE customer SET  name = ?, address = ?, email = ? , contactnumber = ?WHERE id = ?",entity.getId(),entity.getAddress(),entity.getEmail(),entity.getName(),entity.getContact());

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

           return null;
    }




    @Override
    public ArrayList<String> getalls() {

        ArrayList<String> allEmail = new ArrayList<>();
        try {
            ResultSet resultSet = SQLunit.execute("SELECT email FROM customer");
            while (resultSet.next()) {
                allEmail.add(resultSet.getString(1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allEmail;
    }



    public Customer search(String id ) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLunit.execute("SELECT * FROM customer WHERE  id=?",id);

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

    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return   SQLunit.execute("INSERT INTO customer (id,name,address,email,contactnumber ) VALUES(?, ?, ?, ?,?)",entity.getId(),entity.getName(),entity.getAddress(),entity.getEmail(),entity.getContact());

    }
}






