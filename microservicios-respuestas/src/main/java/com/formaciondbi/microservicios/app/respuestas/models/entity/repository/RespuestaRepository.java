package com.formaciondbi.microservicios.app.respuestas.models.entity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.formaciondbi.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {

	@Query("SELECT r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id=?1 and e.id=?2")
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);

	@Query("SELECT e.id FROM Respuesta r join r.alumno a join r.pregunta p join p.examen e where a.id=?1 GROUP BY e.id")
	public Iterable<Long> findExamenesIdsConRespuestaByAlumno(Long alumnoId);
}