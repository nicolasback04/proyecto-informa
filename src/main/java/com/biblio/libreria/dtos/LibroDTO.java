package com.biblio.libreria.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroDTO implements Serializable{

	public static final long serialVersionUID = 1L;

	private Integer id;
    private String titulo;
    private String genero;
    private Integer anioPublicacion;
    private double precio;
    private Integer stock;
    private Integer autorId;
    private String autorNombre;
    private Boolean estado;
    private Date fechaCreacion;
    private String usuarioCreacion;
    private Date fechaModificacion;
    private String usuarioModificacion;
}
