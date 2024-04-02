package com.atpos.atpos.sales;

import org.springframework.stereotype.Service;

@Service
public class SalesService {

    public Invoice processSale(Sale sale){
        var invoice = new Invoice();
        invoice.setProductList(sale.getProductList());
        invoice.setIssueDate(sale.getIssueDate());
        invoice.setCustomerId(sale.getCustomerId());
        var totalPrice = sale.getProductList().stream().mapToDouble(Product::getTotalPrice).sum();
        invoice.setTotalPrice(totalPrice);
        return invoice;
    }
}
