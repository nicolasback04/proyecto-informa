package com.biblio.libreria.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblio.libreria.dtos.ApiResponse;
import com.biblio.libreria.dtos.LibroDTO;
import com.biblio.libreria.service.ILibroService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroController {

	private final ILibroService libroService;

    @PostMapping
    public ResponseEntity<ApiResponse<LibroDTO>> create(@RequestBody @Valid LibroDTO libroDTO) {
        LibroDTO creado = libroService.create(libroDTO);
        ApiResponse<LibroDTO> response = new ApiResponse<>(true, "Libro creado exitosamente", creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LibroDTO>> update(
            @PathVariable Integer id,
            @RequestBody @Valid LibroDTO libroDTO) {
        LibroDTO actualizado = libroService.update(id, libroDTO);
        ApiResponse<LibroDTO> response = new ApiResponse<>(true, "Libro actualizado exitosamente", actualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        libroService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Libro eliminado exitosamente", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LibroDTO>> getById(@PathVariable Integer id) {
        LibroDTO libro = libroService.getById(id);
        ApiResponse<LibroDTO> response = new ApiResponse<>(true, "Libro encontrado", libro);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<LibroDTO>>> getAll() {
        List<LibroDTO> lista = libroService.getAll();
        ApiResponse<List<LibroDTO>> response = new ApiResponse<>(true, "Lista de libros obtenida exitosamente", lista);
        return ResponseEntity.ok(response);
    }
}
