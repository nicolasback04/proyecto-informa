package com.biblio.libreria.service;

import java.util.List;

import com.biblio.libreria.dtos.LibroDTO;

public interface ILibroService {

	LibroDTO create(LibroDTO libroDTO);
	
    LibroDTO update(Integer id, LibroDTO libroDTO);
    
    void delete(Integer id);
    
    LibroDTO getById(Integer id);
    
    List<LibroDTO> getAll();
}
