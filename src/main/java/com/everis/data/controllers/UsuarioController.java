package com.everis.data.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.data.models.Usuario;
import com.everis.data.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired 
	UsuarioService usuarioService;
	
	@GetMapping("/listar")
	public List<Usuario> obtenerUsuarios(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/buscar/{email}")
	public Usuario obtenerUsuarioByEmail(@PathVariable("email") String email)
	{
		return usuarioService.findByEmail(email);
	}

	@PutMapping("/guardar")
	public Usuario guardarUsuario (@RequestBody Usuario usuario)
	{
		return this.usuarioService.save(usuario);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public Usuario obtenerUsuario(@PathVariable("id") Long id) {
		Usuario usuario=usuarioService.buscarUsuario(id);
		if(usuario == null) {
			throw new RuntimeException("Usuario no encontrado"+id);
		}
		return usuario;
	}

}
