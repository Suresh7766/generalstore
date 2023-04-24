package com.generalstore.controller;

import com.generalstore.dto.ProductDTO;
import com.generalstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller to access product apis.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    /**
     * Get all products.
     *
     * @return list of products
     */
    @GetMapping("products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    /**
     * Search product with productName.
     *
     * @param productName productName
     * @return list of products
     */
    @GetMapping("search-product")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String productName) {
        return ResponseEntity.ok(productService.searchProductByName(productName));
    }

    /**
     * Add new product to the store.
     *
     * @param productDTO product request
     * @return stored product
     */
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    /**
     * Delete product with productId.
     *
     * @param productId productId
     */
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Integer productId) {
        productService.deleteProduct(productId);
    }

}
