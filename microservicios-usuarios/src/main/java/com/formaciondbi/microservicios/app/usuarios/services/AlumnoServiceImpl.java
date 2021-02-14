package com.formaciondbi.microservicios.app.usuarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formaciondbi.microservicios.app.usuarios.client.CursoFeingClient;
import com.formaciondbi.microservicios.app.usuarios.models.repository.AlumnoRepository;
import com.formaciondbi.microservicios.commods.services.CommonsServiceImpl;
import com.formaciondbi.microservicios.commond.alumnos.models.entity.Alumno;

@Service
public class AlumnoServiceImpl extends CommonsServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

	@Autowired
	private CursoFeingClient clientCurso;

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String text) {
		return repository.findByNombreOrApellido(text);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAllbyId(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public void eliminarCursoAlumnoPorId(Long id) {
		clientCurso.eliminarCursoAlumnoPorId(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		super.deleteById(id);
		this.eliminarCursoAlumnoPorId(id);
	}
	
	

}
