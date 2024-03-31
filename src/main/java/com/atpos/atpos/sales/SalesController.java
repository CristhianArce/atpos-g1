package com.atpos.atpos.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }


    @PreAuthorize("hasRole('SELLER')")
    @PostMapping("/sale")
    Invoice save(@RequestBody Sale sale) {
        return salesService.processSale(sale);
    }
}
