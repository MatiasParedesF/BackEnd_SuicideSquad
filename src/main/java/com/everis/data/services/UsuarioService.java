package com.everis.data.services;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.util.json.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.data.models.Usuario;
import com.everis.data.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository ur;

	public List<Usuario> findAll() {
		return ur.findAll();
	}
	
	public void eliminarUsuario(Long id) {
		ur.deleteById(id);
	}
	
	public Usuario save(@Valid Usuario usuario) {
		// hash password
        String hashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        usuario.setPassword(hashed);
        return ur.save(usuario);
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

	public Usuario findOneByEmail(String email) { //ARREGLAR
		System.out.println(email);
		Usuario usuario = ur.findOneByEmail(email);
		System.out.println(usuario);
		if(usuario != null)
		{
			return usuario;
		}
		return null;
	}

}
