package com.app.ecom.service;

import com.app.ecom.dto.CartItemRequestDTO;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Product;
import com.app.ecom.model.User;
import com.app.ecom.repository.CartItemRepository;
import com.app.ecom.repository.ProductRepository;
import com.app.ecom.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public boolean addToCart(String userId, CartItemRequestDTO request) {

        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        //If user not found
        if (userOptional.isEmpty()) {
            return false;
        }

        Optional<Product> productOptional = productRepository.findById(request.getProductId());
        //If prod not found
        if (productOptional.isEmpty()) {
            return false;
        }

        Product product = productOptional.get();
        //if insufficient quantity
        if (product.getStockQuantity() < request.getQuantity()) {
            return false;
        }

        User user = userOptional.get();

        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (existingCartItem != null) {
            //If cart already exists Update the cart with quantity and price
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        } else {
            //Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    @Transactional
    public Boolean deleteItemFromCart(String userId, Long productId) {
        long deleteCount = 0;

        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        //If user not found
        if (userOptional.isEmpty()) {
            return false;
        }

        Optional<Product> productOptional = productRepository.findById(productId);
        //If product not found
        if (productOptional.isEmpty()) {
            return false;
        }

        Product product = productOptional.get();
        User user = userOptional.get();

        deleteCount = cartItemRepository.deleteByUserAndProduct(user, product);
        if (deleteCount > 0) {
            return true;
        }
        return false;

    }

    public List<CartItem> getCart(String userId) {
       return userRepository.findById(Long.valueOf(userId))
                .map(user-> cartItemRepository.findByUser(user)).orElse(Collections.emptyList());
    }

    public void clearCart(String userId) {
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if(userOptional.isPresent()) {
            cartItemRepository.deleteByUser(userOptional.get());
        }
    }
}
