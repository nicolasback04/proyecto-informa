package com.biblio.libreria.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.biblio.libreria.entities.LibroEntity;
import com.biblio.libreria.maps.LibroMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LibroRepository {
	
	private final JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall insertarLibroCall;
    private SimpleJdbcCall actualizarLibroCall;
    private SimpleJdbcCall eliminarLibroCall;
    private SimpleJdbcCall obtenerPorIdCall;
    private SimpleJdbcCall obtenerTodosCall;

    @PostConstruct
    private void init() {
        insertarLibroCall = new SimpleJdbcCall(jdbcTemplate)
            .withFunctionName("insertar_libro")
            .withoutProcedureColumnMetaDataAccess()
            .declareParameters(
                new SqlParameter("p_titulo", Types.VARCHAR),
                new SqlParameter("p_autor_id", Types.INTEGER),
                new SqlParameter("p_anio_publicacion", Types.INTEGER),
                new SqlParameter("p_genero", Types.VARCHAR),
                new SqlParameter("p_precio", Types.NUMERIC),
                new SqlParameter("p_stock", Types.INTEGER),
                new SqlParameter("p_usuario_creacion", Types.VARCHAR)
            );

        actualizarLibroCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("actualizar_libro")
            .withoutProcedureColumnMetaDataAccess()
            .declareParameters(
                new SqlParameter("p_id", Types.INTEGER),
                new SqlParameter("p_titulo", Types.VARCHAR),
                new SqlParameter("p_autor_id", Types.INTEGER),
                new SqlParameter("p_anio_publicacion", Types.INTEGER),
                new SqlParameter("p_genero", Types.VARCHAR),
                new SqlParameter("p_precio", Types.NUMERIC),
                new SqlParameter("p_stock", Types.INTEGER),
                new SqlParameter("p_estado", Types.BOOLEAN),
                new SqlParameter("p_usuario_modificacion", Types.VARCHAR)
            );

        eliminarLibroCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("eliminar_libro")
            .withoutProcedureColumnMetaDataAccess()
            .declareParameters(
                new SqlParameter("p_id", Types.INTEGER),
                new SqlParameter("p_usuario_modificacion", Types.VARCHAR)
            );

        obtenerPorIdCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("obtener_libro_por_id")
            .withoutProcedureColumnMetaDataAccess()
            .declareParameters(new SqlParameter("p_id", Types.INTEGER))
            .returningResultSet("result", new LibroMapper());

        obtenerTodosCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("obtener_todos_libros")
            .withoutProcedureColumnMetaDataAccess()
            .returningResultSet("result", new LibroMapper());
    }

    public Integer insertar(LibroEntity libro) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("p_titulo", libro.getTitulo())
            .addValue("p_autor_id", libro.getAutor().getId())
            .addValue("p_anio_publicacion", libro.getAnioPublicacion())
            .addValue("p_genero", libro.getGenero())
            .addValue("p_precio", libro.getPrecio())
            .addValue("p_stock", libro.getStock())
            .addValue("p_usuario_creacion", libro.getUsuarioCreacion());
        return insertarLibroCall.executeFunction(Integer.class, params);
    }

    public void actualizar(LibroEntity libro) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("p_id", libro.getId())
            .addValue("p_titulo", libro.getTitulo())
            .addValue("p_autor_id", libro.getAutor().getId())
            .addValue("p_anio_publicacion", libro.getAnioPublicacion())
            .addValue("p_genero", libro.getGenero())
            .addValue("p_precio", libro.getPrecio())
            .addValue("p_stock", libro.getStock())
            .addValue("p_estado", libro.getEstado())
            .addValue("p_usuario_modificacion", libro.getUsuarioModificacion());
        actualizarLibroCall.execute(params);
    }

    public LibroEntity obtenerPorId(Integer id) {
        String sql = "SELECT * FROM obtener_libro_por_id(?)";
        List<LibroEntity> lista = jdbcTemplate.query(sql, new Object[]{id}, new LibroMapper());
        return lista.isEmpty() ? null : lista.get(0);
    }


    public List<LibroEntity> obtenerTodos() {
        String sql = "SELECT * FROM obtener_todos_libros()";
        return jdbcTemplate.query(sql, new LibroMapper());
    }

    public void eliminar(Integer id, String usuario) {
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("p_id", id)
            .addValue("p_usuario_modificacion", usuario);
        eliminarLibroCall.execute(params);
    }
    
}
