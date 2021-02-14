package com.formaciondbi.microservicios.app.cursos.services;

import java.util.List;

import com.formaciondbi.microservicios.app.cursos.models.entity.Curso;
import com.formaciondbi.microservicios.commods.services.CommonsService;
import com.formaciondbi.microservicios.commond.alumnos.models.entity.Alumno;

public interface CursoService extends CommonsService<Curso> {

	public Curso findCursoByIdAlumno(Long id);

	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);

	public Iterable<Alumno> obtenerAlumnosPorCurso(List<Long> ids);

	public void eliminarCursoAlumnoPorId(Long id);
}
