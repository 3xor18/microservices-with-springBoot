package com.formaciondbi.microservicios.app.examenes.services;

import java.util.List;

import com.formaciondbi.microservicios.commods.services.CommonsService;
import com.formaciondbi.microservicios.commons.examenes.models.entity.Asignatura;
import com.formaciondbi.microservicios.commons.examenes.models.entity.Examen;

public interface ExamenService extends CommonsService<Examen> {

	public List<Examen> findByNombre(String term);

	public List<Asignatura> findAllAsignaturas();
}
