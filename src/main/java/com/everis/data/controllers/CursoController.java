package com.everis.data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.data.models.Curso;
import com.everis.data.services.CursoService;

@RestController
@RequestMapping("/api")
public class CursoController {

	@Autowired CursoService cursoService;
	
	@GetMapping("/cursos")
	public List<Curso> obtenerCursos(){
		return cursoService.findAll();
		
	}
	
	@PostMapping("/cursos")
	public Curso guardarCurso(@RequestBody Curso curso) {
		curso.setId(0L);
		cursoService.insertarCurso(curso);
		return curso;
		
	}
	
	@DeleteMapping("/cursos/{id}")
	public Curso obtenerCurso(@PathVariable("id") Long id) {
		Curso curso=cursoService.buscarCurso(id);
		if(curso == null) {
			throw new RuntimeException("Curso no encontrado"+id);
		}
		
		return curso;
	}
}
