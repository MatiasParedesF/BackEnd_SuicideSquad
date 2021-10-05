package com.everis.data.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.data.models.Curso;
import com.everis.data.repositories.CursoRepository;




@Service
public class CursoService {

	@Autowired
	private CursoRepository cr;

	public Curso insertarCurso(@Valid Curso curso) {
		// TODO Auto-generated method stub
		return cr.save(curso);
	}

	public List<Curso> findAll() {
		// retorna una lista de cursos
		return cr.findAll();
	}

	public void eliminarCurso(Long id) {
		cr.deleteById(id);
	}

	public Curso buscarCurso(Long id) {

		Optional<Curso> oCurso= cr.findById(id);
		
		if(oCurso.isPresent()) {
			return oCurso.get();
		}
		
		return null;
	}

	public void modificarCurso(@Valid Curso curso) {
		cr.save(curso);
		
	}

}