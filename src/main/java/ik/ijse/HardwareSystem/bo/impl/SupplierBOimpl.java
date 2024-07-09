package ik.ijse.HardwareSystem.bo.impl;

import ik.ijse.HardwareSystem.dao.DAO.SupplierDAO;
import ik.ijse.HardwareSystem.dao.DAOFactory;
import ik.ijse.HardwareSystem.dao.impl.SupplierDAOimpl;
import ik.ijse.HardwareSystem.dto.SupplierDto;
import ik.ijse.HardwareSystem.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOimpl {

    SupplierDAO supplierDAO = (SupplierDAOimpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
    public ArrayList<SupplierDto> table() {
        ArrayList<Supplier> suppliers = supplierDAO.table();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        for (Supplier s : suppliers) {
            SupplierDto supplierDto =new SupplierDto(s.getSupplierCompany(),s.getEmailAddress(),s.getNumber(),s.getDescription());
        }
        return supplierDtos;
    }




    public SupplierDto searchBy(String emailAddress) throws SQLException, ClassNotFoundException{

        Supplier s = supplierDAO.searchBy(emailAddress);
        SupplierDto supplierDto = new SupplierDto(s.getSupplierCompany(),s.getDescription(),s.getEmailAddress(),s.getNumber());

        return supplierDto;

    }


    public boolean save(Supplier entity ) throws SQLException, ClassNotFoundException {

return supplierDAO.save(entity);
    }




    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {


        return supplierDAO.update(entity);


    }

}
