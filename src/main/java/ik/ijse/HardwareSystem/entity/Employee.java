package ik.ijse.HardwareSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    private String name;
    private String Address;
    private String id;
    private String contactnumber;
}
