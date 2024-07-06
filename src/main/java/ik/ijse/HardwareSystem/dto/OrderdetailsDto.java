package ik.ijse.HardwareSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderdetailsDto {
    private String orderId;
    private String code;
    private String description;
    private double Price;
    private int qty;
    private double amount;



}



