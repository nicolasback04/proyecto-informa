package com.biblio.libreria.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.biblio.libreria.entities.AutorEntity;
import com.biblio.libreria.maps.AutorMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AutorRepository {

	private final JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall insertarCall, actualizarCall, eliminarCall;

	@PostConstruct
	private void init() {
		insertarCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName("insertar_autor")
				.withoutProcedureColumnMetaDataAccess().declareParameters(new SqlOutParameter("return", Types.INTEGER),
						new SqlParameter("p_nombre", Types.VARCHAR), new SqlParameter("p_nacionalidad", Types.VARCHAR),
						new SqlParameter("p_fecha_nacimiento", Types.DATE),
						new SqlParameter("p_usuario_creacion", Types.VARCHAR));

		actualizarCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("actualizar_autor")
				.withoutProcedureColumnMetaDataAccess().declareParameters(new SqlParameter("p_id", Types.INTEGER),
						new SqlParameter("p_nombre", Types.VARCHAR), new SqlParameter("p_nacionalidad", Types.VARCHAR),
						new SqlParameter("p_fecha_nacimiento", Types.DATE), new SqlParameter("p_estado", Types.BOOLEAN),
						new SqlParameter("p_usuario_modificacion", Types.VARCHAR));

		eliminarCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("eliminar_autor")
				.withoutProcedureColumnMetaDataAccess().declareParameters(new SqlParameter("p_id", Types.INTEGER),
						new SqlParameter("p_usuario_modificacion", Types.VARCHAR));
	}

	public Integer insertar(AutorEntity a) {
		return insertarCall.executeFunction(Integer.class,
				new MapSqlParameterSource().addValue("p_nombre", a.getNombre())
						.addValue("p_nacionalidad", a.getNacionalidad())
						.addValue("p_fecha_nacimiento", a.getFechaNacimiento())
						.addValue("p_usuario_creacion", a.getUsuarioCreacion()));
	}

	public void actualizar(AutorEntity a) {
		actualizarCall.execute(new MapSqlParameterSource().addValue("p_id", a.getId())
				.addValue("p_nombre", a.getNombre()).addValue("p_nacionalidad", a.getNacionalidad())
				.addValue("p_fecha_nacimiento", a.getFechaNacimiento()).addValue("p_estado", a.getEstado())
				.addValue("p_usuario_modificacion", a.getUsuarioModificacion()));
	}

	public void eliminar(Integer id, String usuario) {
		eliminarCall
				.execute(new MapSqlParameterSource().addValue("p_id", id).addValue("p_usuario_modificacion", usuario));
	}

	public AutorEntity obtenerPorId(Integer id) {
		String sql = "SELECT * FROM obtener_autor_por_id(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new AutorMapper());
	}

	public List<AutorEntity> obtenerTodos() {
		String sql = "SELECT * FROM obtener_todos_autores()";
		return jdbcTemplate.query(sql, new AutorMapper());
	}
}
