package com.formaciondbi.microservicios.app.examenes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formaciondbi.microservicios.app.examenes.models.entity.repository.AsignaturaRepository;
import com.formaciondbi.microservicios.app.examenes.models.entity.repository.ExamenRepository;
import com.formaciondbi.microservicios.commods.services.CommonsServiceImpl;
import com.formaciondbi.microservicios.commons.examenes.models.entity.Asignatura;
import com.formaciondbi.microservicios.commons.examenes.models.entity.Examen;

@Service
public class ExamenServiceImpl extends CommonsServiceImpl<Examen, ExamenRepository> implements ExamenService {
	@Autowired
	private AsignaturaRepository asignaturaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Asignatura> findAllAsignaturas() {
		return (List<Asignatura>) asignaturaRepository.findAll();
	}

}
