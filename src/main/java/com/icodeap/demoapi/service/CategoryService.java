package com.icodeap.demoapi.service;

import com.icodeap.demoapi.entity.Category;
import com.icodeap.demoapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de la lógica de negocio para la entidad Category.
 * Esta clase actúa como intermediario entre el controlador y el repositorio.
 */
@Service
public class CategoryService {

    private final CategoryRepository repository;

    // Inyección de dependencias por constructor (mejor práctica)
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * Retorna todas las categorías existentes.
     */
    public List<Category> findAll() {
        return repository.findAll();
    }

    /**
     * Busca una categoría por su ID.
     * @param id identificador de la categoría
     * @return Optional con la categoría si existe
     */
    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Crea una nueva categoría en la base de datos.
     * Valida que no exista otra con el mismo nombre.
     */
    public Category save(Category category) {
        // Validar nombre duplicado
        repository.findByNameIgnoreCase(category.getName())
                .ifPresent(existing -> {
                    throw new RuntimeException("El nombre de la categoría ya existe: " + existing.getName());
                });

        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        return repository.save(category);
    }

    /**
     * Actualiza los datos de una categoría existente.
     */
    public Category update(Long id, Category category) {
        return repository.findById(id)
                .map(existing -> {
                    // Validar que el nuevo nombre no esté duplicado en otra categoría
                    repository.findByNameIgnoreCase(category.getName())
                            .filter(cat -> !cat.getId().equals(id))
                            .ifPresent(cat -> {
                                throw new RuntimeException("El nombre de la categoría ya está en uso: " + cat.getName());
                            });

                    existing.setName(category.getName());
                    existing.setDescription(category.getDescription());
                    existing.setUpdatedAt(LocalDateTime.now());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    /**
     * Elimina una categoría por su ID.
     */
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: la categoría con ID " + id + " no existe.");
        }
        repository.deleteById(id);
    }
}

