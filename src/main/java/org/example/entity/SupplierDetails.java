package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor

public class SupplierDetails {

   private String SupplierCompany;
   private  String itemcode;
   private double price;
   private String Description;
   private int qtyonhandsupplier;

}
