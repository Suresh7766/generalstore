package com.generalstore.utility;

import com.generalstore.entity.Product;
import com.generalstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final ProductRepository productRepository;

    @PostConstruct
    public void insertProducts() {
        Random random = new Random();
        productRepository.saveAll(IntStream.rangeClosed(1, 5).mapToObj(value -> {
            Product product = new Product();
            product.setProductName(random.nextInt(10000) + "-Product");
            product.setCost(Double.valueOf(random.nextInt(1000)));
            return product;
        }).collect(Collectors.toList()));
    }
}
