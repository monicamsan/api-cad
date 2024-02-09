package com.example.api.perfil.controller;

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

import com.example.api.perfil.dto.PerfilDTO;
import com.example.api.perfil.service.PerfilService;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	//Listar todos
	//@Operation(summary = "Get user by ID", description = "Obtém um usuário pelo ID")
	@GetMapping("/all")
	public ResponseEntity<List<PerfilDTO>> listaTodos(){
		Optional<List<PerfilDTO>> perfil = perfilService.listaTodos();
		return perfil.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}


	//Listar por ID
	@GetMapping("/{id}")
	public ResponseEntity<PerfilDTO> buscaPorId(@PathVariable Long id){
		Optional<PerfilDTO> perfil = perfilService.buscaPorId(id);
		return perfil.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}


	//Salvar
	@PostMapping()
	public ResponseEntity<PerfilDTO> salva(@RequestBody PerfilDTO perfilDTO){
		PerfilDTO perfil = perfilService.salva(perfilDTO);
		return ResponseEntity.ok(perfil);
	}


	//Update
	@PutMapping()
	public ResponseEntity<PerfilDTO> atualiza(@RequestBody PerfilDTO perfilDTO){
		PerfilDTO perfil = perfilService.salva(perfilDTO);
		return ResponseEntity.ok(perfil);
	}


	//Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> exclui(@PathVariable Long id){
		var result = perfilService.exclui(id);
		return ResponseEntity.ok(result);
	}

}
