package com.example.api.usuario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.usuario.dto.LoginDTO;
import com.example.api.usuario.dto.UsuarioDTO;
import com.example.api.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	//Listar todos
	@GetMapping("/all")
	public ResponseEntity<List<UsuarioDTO>> listaTodos(){
		Optional<List<UsuarioDTO>> usuario = usuarioService.listaTodos();
		return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}


	//Listar por ID
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscaPorId(@PathVariable Long id){
		Optional<UsuarioDTO> usuario = usuarioService.buscaPorId(id);
		return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}


	//Salvar
	@PostMapping()
	public ResponseEntity<UsuarioDTO> salva(@RequestBody UsuarioDTO usuarioDTO){
		UsuarioDTO usuario = usuarioService.salva(usuarioDTO);
		return ResponseEntity.ok(usuario);
	}


	//Update
	@PutMapping()
	public ResponseEntity<UsuarioDTO> atualiza(@RequestBody UsuarioDTO usuarioDTO){
		UsuarioDTO usuario = usuarioService.salva(usuarioDTO);
		return ResponseEntity.ok(usuario);
	}


	//Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> exclui(@PathVariable Long id){
		var result = usuarioService.exclui(id);
		return ResponseEntity.ok(result);
	}
	
	//Login
	@PostMapping("/login")
	public ResponseEntity<UsuarioDTO> buscaPorUsuarioESenha(@RequestBody LoginDTO login){
		var result = usuarioService.buscaPorUsuarioESenha(login);
		return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}


}
