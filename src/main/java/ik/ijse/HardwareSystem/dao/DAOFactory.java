package ik.ijse.HardwareSystem.dao;


import ik.ijse.HardwareSystem.dao.impl.*;


public class DAOFactory {
    //singleton
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    //enum
    public enum DAOType{
        CUSTOMER,ITEM,EMPLOYEE,SUPPLIER,TRANSPORT,ORDER,ORDER_DETAIL,QUERY
    }
    public SuperDAO getDAO(DAOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerDAOimpl();
            case ITEM:
                return new ItemDAOimpl();
            case ORDER:
                return new OrdeDAOimpl();
            case ORDER_DETAIL:
                return new OrderdetailDAOimpl();
            case QUERY:
                return new QueryDAOimpl();
            case TRANSPORT:
                return  new TransportDAOimpl();
            case EMPLOYEE:
                return new EmployeeDAOimpl();
            case SUPPLIER:
                return new SupplierDAOimpl();
            default:
                return null;
        }

    }
}
