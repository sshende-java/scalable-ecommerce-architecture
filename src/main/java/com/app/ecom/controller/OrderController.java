package com.app.ecom.controller;

import com.app.ecom.dto.OrderResponseDTO;
import com.app.ecom.model.Order;
import com.app.ecom.repository.OrderRepository;
import com.app.ecom.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @RequestHeader("X-User-ID") String userId) {

        Optional<OrderResponseDTO> orderResponseDTO = orderService.createOrder(userId);

        return orderResponseDTO.map(
                        order -> new ResponseEntity(order, HttpStatus.CREATED)).
                orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }


//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//
//    }

}
