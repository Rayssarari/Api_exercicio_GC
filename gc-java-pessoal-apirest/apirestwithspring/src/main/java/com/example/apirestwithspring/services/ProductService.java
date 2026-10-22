package com.example.apirestwithspring.services;

import com.example.apirestwithspring.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.Objects;

@Service
public class ProductService {

    private final RestClient client;

    public ProductService(RestClient.Builder builder) {
        this.client = builder.baseUrl("https://dummyjson.com").build();
    }
//get delete e put
    public Product addANewProduct (Product product){
       return client.post()
                .uri("/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .body(Product.class);
    }

    public Product getProdutoById(int id){
        return Objects.requireNonNull(client.get()
                        .uri("/products/{id}", id)
                        .retrieve()
                        .body(Product.class));
    }

    public Product changeDescription(int id, String description) {
        return Objects.requireNonNull(client.put()
                .uri("/products/{id}", id)
                .body(Map.of("description", description))
                .retrieve()
                .body(Product.class));
    }

    public ResponseEntity<Void> deleteProductById(int id){
        return client.delete()
                .uri("/products/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

}
