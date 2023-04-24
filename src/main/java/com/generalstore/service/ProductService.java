package com.generalstore.service;

import com.generalstore.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProducts();

    List<ProductDTO> searchProductByName(String productName);

    void addProduct(ProductDTO productDTO);

    void deleteProduct(Integer productId);
}
