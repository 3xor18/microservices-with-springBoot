package com.formaciondbi.microservicios.app.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formaciondbi.microservicios.app.respuestas.models.entity.Respuesta;
import com.formaciondbi.microservicios.app.respuestas.models.entity.repository.RespuestaRepository;

@Service
public class RespuestaServiceImpl implements RespuestaService {

	@Autowired
	private RespuestaRepository repository;

	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		return repository.saveAll(respuestas);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamenesIdsConRespuestaByAlumno(Long alumnoId) {
		return repository.findExamenesIdsConRespuestasByAlumno(alumnoId);
	}

}
