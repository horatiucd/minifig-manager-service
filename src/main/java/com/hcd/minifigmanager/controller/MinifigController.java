package com.hcd.minifigmanager.controller;

import com.hcd.minifigmanager.exception.EntityNotFoundException;
import com.hcd.minifigmanager.model.Minifig;
import com.hcd.minifigmanager.repository.MinifigRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/minifigs")
@CrossOrigin(origins = "http://localhost:4200")
public class MinifigController {

    private final MinifigRepository minifigRepository;

    public MinifigController(MinifigRepository minifigRepository) {
        this.minifigRepository = minifigRepository;
    }

    @GetMapping
    public List<Minifig> getMinifigs() {
        return minifigRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Minifig> get(@PathVariable Long id) {
        Minifig minifig = minifigRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(minifig);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Minifig create(@RequestBody Minifig minifig) {
        return minifigRepository.save(minifig);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Minifig> update(@PathVariable Long id,
                                          @RequestBody Minifig minifig) {
        minifigRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Minifig entity = minifigRepository.save(minifig);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Minifig> delete(@PathVariable Long id) {
        Minifig minifig = minifigRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        minifigRepository.delete(minifig);
        return ResponseEntity.ok(minifig);
    }
}
