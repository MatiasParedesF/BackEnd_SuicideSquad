package com.everis.data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.everis.data.models.Curso;
import com.everis.data.services.CursoService;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

	@Autowired CursoService cursoService;
	
	@PutMapping("/guardar")
	public Curso guardarCurso(@RequestBody Curso curso) {
		
		Curso NewCurso=this.cursoService.buscarCurso(curso.getId());
		System.out.println(NewCurso);
		if(NewCurso==null){
			return this.cursoService.insertarCurso(curso);
		}else{
			throw new RuntimeException("El curso ya existe "+curso.getId());
		}
		
	}

	@PutMapping("/modificar")
	public Curso modifcarCurso(@RequestBody Curso curso){
		System.out.println(curso);
		try{
			System.out.println(curso);
			return this.cursoService.insertarCurso(curso);
		}
		catch(Exception e){

			throw new RuntimeException("Fallo el modifcado de "+curso.getId());

		}

	}

	@GetMapping("/listar")
	public List<Curso> obtenerCursos(){
		return this.cursoService.findAll();
		
	}
	
	@GetMapping("/buscar/{id}")
	public Curso obtenerCursoById(@PathVariable("id") Long id)
	{
		return this.cursoService.buscarCurso(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public Curso obtenerCurso(@PathVariable("id") Long id) {
		Curso curso=cursoService.buscarCurso(id);
		if(curso == null) {
			throw new RuntimeException("Curso no encontrado"+id);
		}
		else{
			this.cursoService.eliminarCurso(id);
			return curso;
		}
		
	}
}
