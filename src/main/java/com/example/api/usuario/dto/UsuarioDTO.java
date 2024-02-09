package com.example.api.usuario.dto;

import com.example.api.perfil.dto.PerfilDTO;

import lombok.Data;

@Data
public class UsuarioDTO  {

	private Long id;
	private String nome;
	private String cpf;
	Boolean ativo;
	private PerfilDTO perfil;
	private String senha;
}
