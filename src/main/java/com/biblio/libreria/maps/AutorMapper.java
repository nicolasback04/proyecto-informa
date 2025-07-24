package com.biblio.libreria.maps;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.biblio.libreria.entities.AutorEntity;

public class AutorMapper implements RowMapper<AutorEntity>{

	@Override
    public AutorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        AutorEntity autor = new AutorEntity();
        autor.setId(rs.getInt("id"));
        autor.setNombre(rs.getString("nombre"));
        autor.setNacionalidad(rs.getString("nacionalidad"));
        autor.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        autor.setEstado(rs.getBoolean("estado"));
        autor.setUsuarioCreacion(rs.getString("usuario_creacion"));
        autor.setFechaCreacion(rs.getDate("fecha_creacion"));
        autor.setUsuarioModificacion(rs.getString("usuario_modificacion"));
        autor.setFechaModificacion(rs.getDate("fecha_modificacion") != null 
            ? rs.getDate("fecha_modificacion") 
            : null);
        return autor;
    }
}

