package com.biblio.libreria.maps;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.biblio.libreria.entities.AutorEntity;
import com.biblio.libreria.entities.LibroEntity;

public class LibroMapper implements RowMapper<LibroEntity>{

	@Override
    public LibroEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        LibroEntity libro = new LibroEntity();
        libro.setId(rs.getInt("id"));
        libro.setTitulo(rs.getString("titulo"));
        libro.setGenero(rs.getString("genero"));
        libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
        libro.setPrecio(rs.getDouble("precio"));
        libro.setStock(rs.getInt("stock"));
        AutorEntity autor = new AutorEntity();
        autor.setId(rs.getInt("autor_id"));
        libro.setAutor(autor);
        libro.setEstado(rs.getBoolean("estado"));
        libro.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
        libro.setUsuarioCreacion(rs.getString("usuario_creacion"));
        libro.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
        libro.setUsuarioModificacion(rs.getString("usuario_modificacion"));
        return libro;
    }
}
