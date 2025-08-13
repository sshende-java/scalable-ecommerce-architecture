package com.app.ecom.service;

import com.app.ecom.dto.OrderItemDTO;
import com.app.ecom.dto.OrderResponseDTO;
import com.app.ecom.model.*;
import com.app.ecom.repository.OrderRepository;
import com.app.ecom.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final UserRepository userRepository;


    private final OrderRepository orderRepository;

    @Transactional
    public Optional<OrderResponseDTO> createOrder(String userId) {

        //validate user
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        User user = userOptional.get();


        //validate cart to see if cart has items
        List<CartItem> cartItems = cartService.getCart(userId);
        if (cartItems.isEmpty()) {
            return Optional.empty();
        }

        //calculate total price
        BigDecimal totalPrice = cartItems.stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        //create order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        //transform cart to orderItems
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> new OrderItem(
                null,
                cartItem.getProduct(),
                cartItem.getQuantity(),
                cartItem.getPrice(),
                order
        )).toList();

        order.setItems(orderItems);
        Order savedOrder = orderRepository.save(order);


        //clear cart
        cartService.clearCart(userId);

        return Optional.of(mapOrderToOrderResponseDTO(savedOrder));
    }

    private OrderResponseDTO mapOrderToOrderResponseDTO(Order savedOrder) {
        return new OrderResponseDTO(
                savedOrder.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                savedOrder.getItems().stream().map(item -> new OrderItemDTO(item.getId(),item.getProduct(),item.getQuantity(),item.getPrice())).toList(),
                savedOrder.getCreatedAt()
        );
    }
}
