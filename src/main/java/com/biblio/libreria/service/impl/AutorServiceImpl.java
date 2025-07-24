package com.biblio.libreria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblio.libreria.entities.AutorEntity;
import com.biblio.libreria.repository.AutorRepository;
import com.biblio.libreria.service.IAutorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements IAutorService {

	private final AutorRepository repo;

	  @Override @Transactional
	  public AutorEntity create(AutorEntity a) {
	    Integer id = repo.insertar(a);
	    a.setId(id);
	    return a;
	  }

	  @Override @Transactional
	  public AutorEntity update(Integer id, AutorEntity a) {
	    a.setId(id);
	    repo.actualizar(a);
	    return a;
	  }

	  @Override @Transactional
	  public void delete(Integer id) {
	    repo.eliminar(id, "admin_system");
	  }

	  @Override
	    @Transactional(readOnly = true)
	    public AutorEntity getById(Integer id) {
	        AutorEntity existente = repo.obtenerPorId(id);
	        if (existente == null) {
	            throw new RuntimeException("Autor no encontrado con ID: " + id);
	        }
	        return existente;
	    }

	    @Override
	    @Transactional(readOnly = true)
	    public List<AutorEntity> getAll() {
	        return repo.obtenerTodos();
	    }

}
