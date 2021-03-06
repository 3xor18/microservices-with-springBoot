package com.formaciondbi.microservicios.app.usuarios.services;

import java.util.List;

import com.formaciondbi.microservicios.commods.services.CommonsService;
import com.formaciondbi.microservicios.commond.alumnos.models.entity.Alumno;

public interface AlumnoService extends CommonsService<Alumno> {

	public List<Alumno> findByNombreOrApellido(String text);

	Iterable<Alumno> findAllbyId(Iterable<Long> ids);

	public void eliminarCursoAlumnoPorId(Long id);
}
