package com.example.apirestwithspring.controllers;

import com.example.apirestwithspring.entities.Product;
import com.example.apirestwithspring.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productApi")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/product/add/{product_name}")
    public Product createProduct(@PathVariable String product_name) {
       return service.addANewProduct(new Product(product_name));
    }
    @GetMapping("/products/{id}")
    public Product qualProduto(@PathVariable int id){
        return service.getProdutoById(id);
    }

    @PutMapping("/products/{id}")
    public Product mudarDescricao (@PathVariable int id, String description){
        return service.changeDescription(id, description);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable int id){
        return service.deleteProductById(id);
    }

}
