package com.biblio.libreria.service.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblio.libreria.dtos.LibroDTO;
import com.biblio.libreria.entities.AutorEntity;
import com.biblio.libreria.entities.LibroEntity;
import com.biblio.libreria.repository.LibroRepository;
import com.biblio.libreria.service.ILibroService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements ILibroService{

	private final LibroRepository repo;

    @Override @Transactional
    public LibroDTO create(LibroDTO dto) {
        LibroEntity entity = new LibroEntity();
        entity.setTitulo(dto.getTitulo());
        entity.setGenero(dto.getGenero());
        entity.setAnioPublicacion(dto.getAnioPublicacion());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        AutorEntity autor = new AutorEntity();
        autor.setId(dto.getAutorId());
        entity.setAutor(autor);
        entity.setUsuarioCreacion(dto.getUsuarioCreacion());

        Integer id = repo.insertar(entity);
        entity.setId(id);
        dto.setId(id);
        return dto;
    }

    @Override @Transactional
    public LibroDTO update(Integer id, LibroDTO dto) {
        LibroEntity entity = new LibroEntity();
        entity.setId(id);
        entity.setTitulo(dto.getTitulo());
        entity.setGenero(dto.getGenero());
        entity.setAnioPublicacion(dto.getAnioPublicacion());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        AutorEntity autor = new AutorEntity();
        autor.setId(dto.getAutorId());
        entity.setAutor(autor);
        entity.setEstado(dto.getEstado());
        entity.setUsuarioModificacion(dto.getUsuarioModificacion());

        repo.actualizar(entity);
        dto.setId(id);
        return dto;
    }

    @Override @Transactional
    public void delete(Integer id) {
        repo.eliminar(id, "admin_system");
    }


    @Override
    @Transactional(readOnly = true)
    public LibroDTO getById(Integer id) {
        LibroEntity e = repo.obtenerPorId(id);
        if (e == null) {
            throw new RuntimeException("Libro no encontrado con ID: " + id);
        }
        return mapToDTO(e);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LibroDTO> getAll() {
        return repo.obtenerTodos().stream()
                   .map(this::mapToDTO)
                   .toList();
    }

    // Mappers
    private LibroEntity mapToEntity(LibroDTO d) {
        LibroEntity e = new LibroEntity();
        e.setTitulo(d.getTitulo());
        e.setGenero(d.getGenero());
        e.setAnioPublicacion(d.getAnioPublicacion());
        e.setPrecio(d.getPrecio());
        e.setStock(d.getStock());
        AutorEntity autor = new AutorEntity();
        autor.setId(d.getAutorId());
        e.setAutor(autor);
        e.setUsuarioCreacion(d.getUsuarioCreacion()); // o modificación según contexto
        return e;
    }

    private LibroDTO mapToDTO(LibroEntity e) {
        if (e == null) {
            throw new IllegalArgumentException("No se puede mapear una entidad nula");
        }

        return LibroDTO.builder()
            .id(e.getId())
            .titulo(e.getTitulo())
            .genero(e.getGenero())
            .anioPublicacion(e.getAnioPublicacion())
            .precio(e.getPrecio())
            .stock(e.getStock())
            .autorId(e.getAutor() != null ? e.getAutor().getId() : null)
            .autorNombre(e.getAutor() != null ? e.getAutor().getNombre() : null) // si existe
            .estado(e.getEstado())
            .fechaCreacion(e.getFechaCreacion())
            .usuarioCreacion(e.getUsuarioCreacion())
            .fechaModificacion(e.getFechaModificacion())
            .usuarioModificacion(e.getUsuarioModificacion())
            .build();
    }

}
