package com.webservice.sample.controller;

import com.webservice.sample.dto.BookRequest;
import com.webservice.sample.dto.BookResponse;
import com.webservice.sample.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookRequest request) {
        BookResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/books/" + created.id())).body(created);
    }

    @GetMapping
    public List<BookResponse> findAll() { return service.findAll(); }

    @GetMapping("/category/{category}")
    public List<BookResponse> findByCategory(@PathVariable String category){
        return service.findByCategory(category);
    }

    @GetMapping("/price")
    public List<BookResponse> findByPriceBetween(@RequestParam("min") Integer min, @RequestParam("max") Integer max){
        return service.findByPriceBetween(min,max);
    }

    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable Long id) { return service.findById(id); }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
