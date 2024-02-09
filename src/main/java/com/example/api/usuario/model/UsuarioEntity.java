package com.example.api.usuario.model;

import java.io.Serializable;

import com.example.api.perfil.model.PerfilEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema= "BD_SCHEMA", name = "TB_USUARIO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(schema= "BD_SCHEMA", name ="GR_USUARIO", sequenceName = "SQ_USUARIO", allocationSize = 1)
public class UsuarioEntity  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="GR_PERFIL")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="SENHA")
	private String senha;
	
	@Column(name="CPF")
	private String cpf;
	
	@Column(name="ATIVO")
	Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name="perfil_id")
	private PerfilEntity perfil;

}
