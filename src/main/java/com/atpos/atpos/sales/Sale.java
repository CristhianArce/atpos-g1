package com.atpos.atpos.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale {
    private String customerId;
    private List<Product> productList;
    private LocalDateTime issueDate = LocalDateTime.now();
}
