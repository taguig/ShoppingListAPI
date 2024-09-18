package com.example.shoppingapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingapi.models.Brand;
import com.example.shoppingapi.repositories.BrandRepository;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController

@RequestMapping(path = "/brand")
public class BrandController {

    private BrandRepository brandRepository;

    public BrandController(BrandRepository brandRep) {
        this.brandRepository = brandRep;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrand(@PathVariable long id) {
        return brandRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("")
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable long id) {
        return brandRepository.findById(id).map(brand -> {
            brandRepository.delete(brand);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Brand postMethodName(@RequestBody Brand entity) {

        return brandRepository.save(entity);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> putMethodName(@PathVariable long id, @RequestBody Brand entity) {
        // TODO: process PUT request
        return brandRepository.findById(id).map(brand -> {
            brand.setId(entity.getId());
            brand.setName(entity.getName());

            return ResponseEntity.ok(brandRepository.save(brand));
        }).orElse(ResponseEntity.notFound().build());
    }

}
