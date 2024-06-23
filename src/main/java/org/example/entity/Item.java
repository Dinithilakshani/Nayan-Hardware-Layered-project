package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String  code;
    private String desctription;
    private int QtyOnHeand;
    private double Price;


}
