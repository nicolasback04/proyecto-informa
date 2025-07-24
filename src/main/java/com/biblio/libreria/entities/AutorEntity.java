package com.biblio.libreria.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "autores")
public class AutorEntity implements Serializable {

	public static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;

	private String nacionalidad;

	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	private Boolean estado;

	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "usuario_creacion")
	private String usuarioCreacion;

	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	@Column(name = "usuario_modificacion")
	private String usuarioModificacion;
	
}
