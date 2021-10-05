package com.everis.data.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.data.models.Usuario;
import com.everis.data.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository ur;

	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return ur.findAll();
	}
	
	public void eliminarUsuario(Long id) {
		ur.deleteById(id);
	}
	
	public Usuario buscarUsuario(Long id) {
		
		Optional<Usuario> oUsuario = ur.findById(id);
		
		if(oUsuario.isPresent()) {
			return oUsuario.get();
		}
		
		return null;
	}
	
	public void modificarUsuario(@Valid Usuario usuario) {
		
		ur.save(usuario);
	}

}
