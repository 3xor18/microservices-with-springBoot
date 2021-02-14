package com.formaciondbi.microservicios.app.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formaciondbi.microservicios.app.cursos.clients.RespuestaFeingClient;
import com.formaciondbi.microservicios.app.cursos.models.entity.Curso;
import com.formaciondbi.microservicios.app.cursos.models.entity.repository.CursoRepository;
import com.formaciondbi.microservicios.commods.services.CommonsServiceImpl;

@Service
public class CursoServiceImpl extends CommonsServiceImpl<Curso, CursoRepository> implements CursoService {

	@Autowired
	private RespuestaFeingClient client;

	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByIdAlumno(Long id) {
		return repository.findCursoByIdAlumno(id);
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
		return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
	}

}
