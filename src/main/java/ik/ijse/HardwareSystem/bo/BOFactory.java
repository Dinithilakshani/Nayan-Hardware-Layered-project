package ik.ijse.HardwareSystem.bo;

import ik.ijse.HardwareSystem.bo.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}


    public static BOFactory getBoFactory(){

        return boFactory== null ? boFactory= new BOFactory(): boFactory;
    }

    public enum BOType{
        CUSTOMER,ITEM,SUPPLIER,EMPLOYEE,TRANSPORT,PLACEORDER
    }
   public SuperBO getBOType(BOType type){
        switch (type){
            case CUSTOMER:
                return (SuperBO) new Customerboimpl();
            case ITEM:
                return (SuperBO) new ItemBOimpl();
            case SUPPLIER:
                return (SuperBO) new SupplierBOimpl();
            case EMPLOYEE:
                return (SuperBO) new EMployeeBOimpl();
            case TRANSPORT:
                return (SuperBO) new TransportBOimpl();
            //case PLACEORDER:
               // return (SuperBO) new PlaceorderBOimpl();
            default:
                return null;
        }
    }
}


