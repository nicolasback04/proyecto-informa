package com.biblio.libreria.service;

import java.util.List;

import com.biblio.libreria.entities.AutorEntity;

public interface IAutorService {

	AutorEntity create(AutorEntity autor);
    AutorEntity update(Integer id, AutorEntity autor);
    void delete(Integer id);
    AutorEntity getById(Integer id);
    List<AutorEntity> getAll();
}
