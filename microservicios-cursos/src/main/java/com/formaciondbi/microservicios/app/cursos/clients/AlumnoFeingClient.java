package com.formaciondbi.microservicios.app.cursos.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formaciondbi.microservicios.commond.alumnos.models.entity.Alumno;

@FeignClient(name = "microservicio-usuarios")
public interface AlumnoFeingClient {

	@GetMapping("/alumnos-por-curso")
	public Iterable<Alumno> obtenerAlumnosPorCurso(@RequestParam List<Long> ids);
}
