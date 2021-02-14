package com.formaciondbi.microservicios.app.cursos.models.entity.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.formaciondbi.microservicios.app.cursos.models.entity.Curso;

public interface CursoRepository extends PagingAndSortingRepository<Curso, Long> {

	@Query("select c from Curso c join fetch c.cursosAlumnos a where a.alumnoId=?1")
	public Curso findCursoByIdAlumno(Long id);

	@Modifying
	@Query("DELETE FROM CursoAlumno ca WHERE ca.alumnoId=?1")
	public void eliminarCursoAlumnoPorId(Long id);
}
