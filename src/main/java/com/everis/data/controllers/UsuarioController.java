package com.everis.data.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.data.models.Usuario;
import com.everis.data.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> obtenerUsuarios(){
		return usuarioService.findAll();
	}
	
	public void eliminarUsuario(Long id) {
		
		usuarioService.eliminarUsuario(id);
		
	}
	

}
