package com.generalstore.service.impl;

import com.generalstore.dto.ProductDTO;
import com.generalstore.entity.Product;
import com.generalstore.exception.GeneralStoreException;
import com.generalstore.repository.ProductRepository;
import com.generalstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getProducts() {
        return convertProductsToProductDTOs(productRepository.findAll());
    }

    @Override
    public List<ProductDTO> searchProductByName(String productName) {
        return convertProductsToProductDTOs(productRepository.findByProductNameStartingWith(productName));
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        if (productRepository.existsByProductNameAndCost(productDTO.getProductName(), productDTO.getCost())) {
            throw new GeneralStoreException("Product already exists in store!.");
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product, "productId");
        BeanUtils.copyProperties(productRepository.save(product), productDTO);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    private List<ProductDTO> convertProductsToProductDTOs(List<Product> products) {
        return products.stream().map(product -> {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            return productDTO;
        }).collect(Collectors.toList());
    }
}
