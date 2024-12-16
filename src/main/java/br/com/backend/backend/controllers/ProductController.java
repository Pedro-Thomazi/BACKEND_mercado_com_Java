package br.com.backend.backend.controllers;

import br.com.backend.backend.domains.product.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public void registerProduct(@RequestBody @Valid DataProduct data) {
        var product = new Product(data);
        this.repository.save(product);
    }

    @GetMapping
    @Transactional
    public List<DataGetProducts> getAllProducts() {
        return this.repository.findAll().stream().map(DataGetProducts::new).toList();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        var product = this.repository.findById(id);

        if (product.isPresent()) return ResponseEntity.ok(product.get());

        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public void alterPrice(@RequestBody DataAlterProduct data, @PathVariable Long id) {
        var product = this.repository.getReferenceById(id);
        product.alterProduct(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void notInStock(@PathVariable Long id) {
        var product = this.repository.getReferenceById(id);
        product.notInStock();
    }
}
