package org.example.dao;

import org.example.SQLunit;
import org.example.db.DbConnection;
import org.example.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOimpl implements CustomerDAO {
    public static CustomerDto searchById(String contactnumber) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLunit.execute("SELECT * FROM customer WHERE  contactnumber=?");

        CustomerDto customerDto = null;

        if (resultSet.next()) {
            String customerId = resultSet.getString(2);
            String customerName = resultSet.getString(1);
            String contact = resultSet.getString(4);
            String customerAddress = resultSet.getString(3);
            String customerEmail = resultSet.getString(5);


            customerDto = new CustomerDto(customerId, customerName, contact, customerAddress, customerEmail);
        }
        return customerDto;
    }

    public static boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return SQLunit.execute("update customer set name = ?,address = ?,email = ? , contactnumber = ? where id =?",customerDto.getId(),customerDto.getAddress(),customerDto.getEmail(),customerDto.getAddress());

    }


    public int delete(String contactnumber) throws SQLException, ClassNotFoundException {


            return SQLunit.execute("DELETE FROM customer WHERE contactnumber = ?",contactnumber);

    }


        public ArrayList<CustomerDto> tble() {
        ArrayList<CustomerDto> Customer = new ArrayList<>();


        try {
            ResultSet resultSet = SQLunit.execute("select * from Customer");
            while (resultSet.next()) {


                CustomerDto customerDto = new CustomerDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)


                );
                Customer.add(customerDto);


            }
            return Customer;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        }


    public ArrayList<String> getall() {
        ArrayList<String> allEmail = new ArrayList<>();
        try {
            ResultSet resultSet = SQLunit.execute("select email from customer");

            while (resultSet.next()) {
                allEmail.add(String.valueOf(resultSet.getString(1)));
            }
            return allEmail;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public CustomerDto search(String email ) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLunit.execute("SELECT * FROM customer WHERE  email=?",email);

        CustomerDto customerDto = null;


        if (resultSet.next()) {
            String customerId = resultSet.getString(2);
            String customerName = resultSet.getString(1);
            String contact = resultSet.getString(4);
            String customerAddress = resultSet.getString(3);
            String customerEmail = resultSet.getString(5);


            customerDto = new CustomerDto(customerId, customerName, contact, customerAddress, customerEmail);
        }
        return customerDto;


    }

    public int save(String id, String name, String address, String tel, String email) throws SQLException, ClassNotFoundException {
        return   SQLunit.execute("INSERT INTO customer VALUES(?, ?, ?, ?,?)",name,address,tel,email);

    }
}






