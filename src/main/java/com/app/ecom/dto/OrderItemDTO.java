package com.app.ecom.dto;

import com.app.ecom.model.Order;
import com.app.ecom.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
}
