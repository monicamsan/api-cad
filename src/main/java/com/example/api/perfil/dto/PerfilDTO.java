package com.example.api.perfil.dto;

import lombok.Data;

@Data
public class PerfilDTO {
	
	private Long id;
	private String nome;
	private Boolean admin;
	private Boolean ativo;

}
