package com.icodeap.demoapi.controller;

import com.icodeap.demoapi.entity.Category;
import com.icodeap.demoapi.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar categorías.
 * Expone los endpoints que permiten operaciones CRUD sobre Category.
 */
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil en desarrollo)
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    /**
     * Obtiene la lista completa de categorías.
     * Ejemplo: GET /api/categories
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = service.findAll();
        return ResponseEntity.ok(categories);
    }

    /**
     * Obtiene una categoría específica por su ID.
     * Ejemplo: GET /api/categories/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva categoría.
     * Ejemplo: POST /api/categories
     * Body: { "name": "Electrónica", "description": "Productos tecnológicos" }
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Category category) {
        try {
            Category newCategory = service.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    /**
     * Actualiza una categoría existente.
     * Ejemplo: PUT /api/categories/1
     * Body: { "name": "Electrodomésticos", "description": "Productos del hogar" }
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category) {
        try {
            Category updated = service.update(id, category);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            String message = ex.getMessage().contains("no encontrada") ? "Categoría no encontrada" : ex.getMessage();
            HttpStatus status = message.contains("no encontrada") ? HttpStatus.NOT_FOUND : HttpStatus.CONFLICT;
            return ResponseEntity.status(status).body(message);
        }
    }

    /**
     * Elimina una categoría por ID.
     * Ejemplo: DELETE /api/categories/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}

