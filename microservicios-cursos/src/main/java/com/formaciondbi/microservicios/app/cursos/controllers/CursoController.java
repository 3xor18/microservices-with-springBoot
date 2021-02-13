package com.formaciondbi.microservicios.app.cursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formaciondbi.microservicios.app.cursos.models.entity.Curso;
import com.formaciondbi.microservicios.app.cursos.services.CursoService;
import com.formaciondbi.microservicios.commods.controllers.CommondControl;
import com.formaciondbi.microservicios.commond.alumnos.models.entity.Alumno;
import com.formaciondbi.microservicios.commons.examenes.models.entity.Examen;

@RestController
public class CursoController extends CommondControl<Curso, CursoService> {

	@Value("${config.balanceador.test}")
	private String balancedadorTest;

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Curso> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();
		cursoDB.setNombre(curso.getNombre());

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}

	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@Valid @RequestBody List<Alumno> alumnos, BindingResult result,
			@PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Curso> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();

		alumnos.forEach(a -> {
			cursoDB.addAlumno(a);
		});

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}

	@PutMapping("/{id}/eliminar-alumnos")
	public ResponseEntity<?> eliminarAlumno(@Valid @RequestBody Alumno alumno, BindingResult result,
			@PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Curso> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();

		cursoDB.removeAlumno(alumno);

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}

	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id) {
		Curso c = service.findCursoByIdAlumno(id);
		if (c != null) {
			List<Long> examenesId = (List<Long>) service.obtenerExamenesIdsConRespuestasAlumno(id);

			List<Examen> examenes = c.getExamenes().stream().map(examen -> {
				if (examenesId.contains(examen.getId())) {
					examen.setRespondido(true);
				}
				return examen;
			}).collect(Collectors.toList());

			c.setExamenes(examenes);
		}
		return ResponseEntity.ok(c);
	}

	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@Valid @RequestBody List<Examen> examenes, BindingResult result,
			@PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Curso> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();

		examenes.forEach(cursoDB::addExamenes);

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}

	/**
	 * @param id
	 * @param examenIn
	 * @return
	 */
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@Valid @RequestBody Examen examenIn, BindingResult result,
			@PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Curso> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();

		cursoDB.removeExamenes(examenIn);

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}

	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		Map<String, Object> response = new HashMap<>();
		response.put("balanceador", balancedadorTest);
		response.put("cursos", service.findAll());
		return ResponseEntity.ok(response);
	}

}
