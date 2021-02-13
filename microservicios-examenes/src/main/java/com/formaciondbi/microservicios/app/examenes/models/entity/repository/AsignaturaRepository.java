package com.formaciondbi.microservicios.app.examenes.models.entity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.formaciondbi.microservicios.commons.examenes.models.entity.Asignatura;

public interface AsignaturaRepository extends PagingAndSortingRepository<Asignatura, Long> {

}
