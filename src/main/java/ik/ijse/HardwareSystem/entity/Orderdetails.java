package ik.ijse.HardwareSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Orderdetails {
    private String orderId;
    private String code;
    private String description;
    private double Price;
    private int qty;
    private double amount;



}



