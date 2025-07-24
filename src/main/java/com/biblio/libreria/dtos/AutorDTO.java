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
public class AutorDTO implements Serializable {

	public static final long serialVersionUID = 1L;
	
	private Integer id;
    private String nombre;
    private String nacionalidad;
    private Date fechaNacimiento;
    private Boolean estado;
    private Date fechaCreacion;
    private String usuarioCreacion;
    private Date fechaModificacion;
    private String usuarioModificacion;
}
