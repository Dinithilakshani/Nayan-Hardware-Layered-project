package ik.ijse.HardwareSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Customer {



    private String id;
    private String name;
    private String address;
    private String Email;

    private String contact;



}

