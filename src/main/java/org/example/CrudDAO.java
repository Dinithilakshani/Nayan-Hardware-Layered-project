package org.example;

import org.example.dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO  {
    public static CustomerDto searchById(String contactnumber) throws SQLException, ClassNotFoundException {
        return null;
    }

    public static boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public int delete(String contactnumber) throws SQLException, ClassNotFoundException ;

    public ArrayList<CustomerDto> tble() ;

    public ArrayList<String> getall() ;

    public CustomerDto search(String email ) throws SQLException, ClassNotFoundException ;


    public int save(String id, String name, String address, String tel, String email) throws SQLException, ClassNotFoundException ;

    }









