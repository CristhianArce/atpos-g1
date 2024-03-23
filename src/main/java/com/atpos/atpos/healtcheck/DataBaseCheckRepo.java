package com.atpos.atpos.healtcheck;

import com.atpos.atpos.inventory.ProductInStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBaseCheckRepo extends JpaRepository<ProductInStock, Long> {

    @Query(value = "SELECT 1", nativeQuery = true)
    Integer selectOne();
}
