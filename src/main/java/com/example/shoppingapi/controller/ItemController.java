package com.example.shoppingapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingapi.models.Item;
import com.example.shoppingapi.repositories.ItemRepository;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController

@RequestMapping(path = "/item")
public class ItemController {

    private ItemRepository itemRepository;

    public ItemController(ItemRepository itemsRep) {
        this.itemRepository = itemsRep;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable long id) {
        return itemRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable long id) {
        return itemRepository.findById(id).map(Item -> {
            itemRepository.delete(Item);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Item postMethodName(@RequestBody Item entity) {

        return itemRepository.save(entity);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> putMethodName(@PathVariable long id, @RequestBody Item entity) {

        return itemRepository.findById(id).map(item -> {
            item.setId(entity.getId());
            item.setName(entity.getName());
            item.setPrice(entity.getPrice());
            item.setCalories(entity.getCalories());
            return ResponseEntity.ok(itemRepository.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }

}
