package com.atpos.atpos.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private double unitPrice;
    private UUID code = UUID.randomUUID();
    private double totalPrice;
    private String description;

}
