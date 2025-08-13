package com.app.ecom;

import com.app.ecom.dto.ProductRequestDTO;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.model.User;
import com.app.ecom.service.ProductService;
import com.app.ecom.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
public class EcomApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomApplication.class, args);
    }


    @Bean
    public CommandLineRunner addDefaultUsers(UserService userService) {    //adding users at start
        return args -> {
            userService.addUser(UserRequest.builder().firstName("sau").lastName("sau").build());
            userService.addUser(UserRequest.builder().firstName("shu").lastName("shu").build());
            userService.addUser(UserRequest.builder().firstName("gau").lastName("gau").build());
        };
    }

    //adding default products in h2 db
    @Bean
    public CommandLineRunner addDefaultProducts(ProductService productService) {
        return args -> {
            List<ProductRequestDTO> defaultProducts = List.of(
                    ProductRequestDTO.builder()
                            .name("iPhone 16 Pro")
                            .description("Latest iPhone with amazing features")
                            .price(new BigDecimal("1099.99"))
                            .stockQuantity(50)
                            .category("Electronics")
                            .imageUrl("https://placehold.co/600x400")
                            .build(),

                    ProductRequestDTO.builder()
                            .name("Samsung Galaxy Z Flip 6")
                            .description("Foldable phone with futuristic design")
                            .price(new BigDecimal("1199.99"))
                            .stockQuantity(30)
                            .category("Electronics")
                            .imageUrl("https://placehold.co/600x401")
                            .build(),

                    ProductRequestDTO.builder()
                            .name("Sony WH-1000XM6")
                            .description("Noise-canceling over-ear headphones")
                            .price(new BigDecimal("399.99"))
                            .stockQuantity(75)
                            .category("Audio")
                            .imageUrl("https://placehold.co/600x402")
                            .build(),

                    ProductRequestDTO.builder()
                            .name("Dell XPS 15")
                            .description("High-performance laptop for professionals")
                            .price(new BigDecimal("1599.99"))
                            .stockQuantity(20)
                            .category("Computers")
                            .imageUrl("https://placehold.co/600x403")
                            .build(),

                    ProductRequestDTO.builder()
                            .name("Apple Watch Series 10")
                            .description("Next-gen smartwatch with health tracking")
                            .price(new BigDecimal("499.99"))
                            .stockQuantity(60)
                            .category("Wearables")
                            .imageUrl("https://placehold.co/600x404")
                            .build(),

                    ProductRequestDTO.builder()
                            .name("GoPro Hero 13")
                            .description("Adventure camera with 8K recording")
                            .price(new BigDecimal("449.99"))
                            .stockQuantity(40)
                            .category("Cameras")
                            .imageUrl("https://placehold.co/600x405")
                            .build(),

                    ProductRequestDTO.builder()
                            .name("Kindle Paperwhite Signature")
                            .description("E-reader with adjustable warm light")
                            .price(new BigDecimal("189.99"))
                            .stockQuantity(90)
                            .category("Books")
                            .imageUrl("https://placehold.co/600x406")
                            .build(),

                    ProductRequestDTO.builder()
                            .name("LG C3 OLED TV")
                            .description("Stunning OLED display with 4K support")
                            .price(new BigDecimal("2199.99"))
                            .stockQuantity(10)
                            .category("Home Entertainment")
                            .imageUrl("https://placehold.co/600x407")
                            .build(),

                    ProductRequestDTO.builder()
                            .name("Bose SoundLink Revolve+ II")
                            .description("Portable Bluetooth speaker with 360° sound")
                            .price(new BigDecimal("329.99"))
                            .stockQuantity(45)
                            .category("Audio")
                            .imageUrl("https://placehold.co/600x408")
                            .build(),

                    ProductRequestDTO.builder()
                            .name("Razer DeathAdder V3 Pro")
                            .description("Ergonomic wireless gaming mouse")
                            .price(new BigDecimal("149.99"))
                            .stockQuantity(85)
                            .category("Gaming")
                            .imageUrl("https://placehold.co/600x409")
                            .build()
            );

            defaultProducts.forEach(productService::createProduct);
        };
    }


}
