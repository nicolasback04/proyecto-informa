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

import com.biblio.libreria.entities.AutorEntity;
import com.biblio.libreria.service.IAutorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
public class AutorController {

	private final IAutorService autorService;

    @PostMapping
    public ResponseEntity<AutorEntity> create(@RequestBody AutorEntity autor) {
        AutorEntity creado = autorService.create(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorEntity> update(
            @PathVariable Integer id,
            @RequestBody AutorEntity autor) {
        AutorEntity actualizado = autorService.update(id, autor);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorEntity> getById(@PathVariable Integer id) {
        AutorEntity autor = autorService.getById(id);
        return ResponseEntity.ok(autor);
    }

    @GetMapping
    public ResponseEntity<List<AutorEntity>> getAll() {
        List<AutorEntity> lista = autorService.getAll();
        return ResponseEntity.ok(lista);
    }
}
