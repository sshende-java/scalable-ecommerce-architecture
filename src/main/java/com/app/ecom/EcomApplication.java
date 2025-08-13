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

}
