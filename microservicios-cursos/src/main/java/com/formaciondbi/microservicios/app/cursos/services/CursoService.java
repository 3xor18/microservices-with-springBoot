package com.formaciondbi.microservicios.app.cursos.services;

import com.formaciondbi.microservicios.app.cursos.models.entity.Curso;
import com.formaciondbi.microservicios.commods.services.CommonsService;

public interface CursoService extends CommonsService<Curso> {

	public Curso findCursoByIdAlumno(Long id);

	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
}