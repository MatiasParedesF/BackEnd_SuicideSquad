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

import com.everis.data.models.Usuario;
import com.everis.data.services.UsuarioService;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired 
	UsuarioService usuarioService;
	
	@PutMapping("/guardar")
	public Usuario guardarUsuario (@RequestBody Usuario usuario)
	{
		Usuario NewUsuario = this.usuarioService.findOneByEmail(usuario.getEmail()); 
		System.out.println(NewUsuario);
		if (NewUsuario==null)
		{
			return this.usuarioService.save(usuario);
		}
		else
		{
			throw new RuntimeException("El email ya existe "+usuario.getEmail());
		}
	}

	@PutMapping("/modificar")
	public Usuario modificarUsuario (@RequestBody Usuario usuario)
	{
		try{
			return this.usuarioService.save(usuario);
		}
		catch(Exception e) {
			throw new RuntimeException("Fallo el modificado de "+usuario.getEmail());
		}
	}

	@PutMapping("/verificar")
	public Usuario verificarUsuario (@RequestBody Usuario usuario)
	{
		try{
			Usuario UserDB = this.usuarioService.findOneByEmail(usuario.getEmail());
			if (UserDB != null)
			{
				return null; // this.usuarioService.hashCode()
			}	
			else
			{
				return null;
			}
		}
		catch(Exception e) {
			throw new RuntimeException("Fallo el modificado de "+usuario.getEmail());
		}
	}

	@GetMapping("/listar")
	public List<Usuario> obtenerUsuarios()
	{
		return this.usuarioService.findAll();
	}
		
	@GetMapping("/buscar/{id}")
	public Usuario obtenerUsuarioById(@PathVariable("id") Long id)
	{
		Usuario usuario=this.usuarioService.buscarUsuario(id);
		
		if(usuario == null) 
		{
			throw new RuntimeException("Usuario no encontrado " + id);
		}
		else
		{
			return usuario;
		}
	}

	@GetMapping("/buscar/{email}")
	public Usuario obtenerUsuarioByEmail(@PathVariable("email") String email)
	{
		Usuario usuario=this.usuarioService.findOneByEmail(email);
		
		if(usuario == null) 
		{
			throw new RuntimeException("Usuario no encontrado "+email);
		}
		else
		{
			return usuario;
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public Usuario obtenerUsuario(@PathVariable("id") Long id) 
	{
		Usuario usuario=this.usuarioService.buscarUsuario(id);
		
		if(usuario == null) 
		{
			throw new RuntimeException("Usuario no encontrado "+id);
		}
		else
		{
			this.usuarioService.eliminarUsuario(id);
			return usuario;
		}
	}

}
