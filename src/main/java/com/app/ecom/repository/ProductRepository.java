package com.app.ecom.repository;

import com.app.ecom.dto.ProductResponseDTO;
import com.app.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();

    @Query(value = "SELECT p from products p where p.active = true AND p.stockQuantity>0 AND lower(p.name) LIKE lower(concat('%',:keyword,'%'))")
    List<Product> searchProduct(@Param("keyword") String keyword);
}
