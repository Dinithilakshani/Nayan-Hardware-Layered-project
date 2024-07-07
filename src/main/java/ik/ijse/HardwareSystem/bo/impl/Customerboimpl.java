package ik.ijse.HardwareSystem.bo.impl;

import ik.ijse.HardwareSystem.dao.DAO.CustomerDAO;
import ik.ijse.HardwareSystem.dao.impl.CustomerDAOimpl;
import ik.ijse.HardwareSystem.dto.CustomerDto;
import ik.ijse.HardwareSystem.bo.impl.BO.CustomeBo;
import ik.ijse.HardwareSystem.dao.DAOFactory;
import ik.ijse.HardwareSystem.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class Customerboimpl implements CustomeBo {
    CustomerDAO customerDAO = (CustomerDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);


    public CustomerDto searchBy(String id) throws SQLException, ClassNotFoundException {

        Customer c = customerDAO.search(id);
        CustomerDto customer = new CustomerDto(c.getId(),c.getName(),c.getAddress(),c.getContact(),c.getEmail());
        return customer;
    }

    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        // return customerDAO.add(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getEmail()));
    }


    public int delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }


    public ArrayList<CustomerDto> table() {
        ArrayList<Customer> customers = customerDAO.table();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer c : customers) {
            CustomerDto customerDto = new CustomerDto(c.getId(), c.getName(), c.getAddress(), c.getContact(), c.getEmail());
        }
        return customerDtos;
    }



        public ArrayList<CustomerDto> getall() {
            ArrayList<Customer>customers =customerDAO.getall();
            ArrayList<CustomerDto>customerDtos = new ArrayList<>();
            for(Customer c : customers) {
                CustomerDto customerDto = new CustomerDto(c.getId(), c.getName(), c.getAddress(), c.getContact(), c.getEmail());
            }
            return customerDtos;

            }





        public Customer search(String id) throws SQLException, ClassNotFoundException{
            return customerDAO.search(id);


    }

    public boolean save(CustomerDto dto) throws SQLException, ClassNotFoundException {
 return customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getEmail(),dto.getTel()));
    }


}
