package com.example.api.perfil.model;

import java.io.Serializable;
import java.util.List;

import com.example.api.usuario.model.UsuarioEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(schema= "BD_SCHEMA", name = "TB_PERFIL")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(schema= "BD_SCHEMA", name ="GR_PERFIL", sequenceName = "SQ_PERFIL", allocationSize = 1)
public class PerfilEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="GR_PERFIL")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="ADMIN")
	Boolean admin;
	
	@Column(name="ATIVO")
	Boolean ativo;
	
	@OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
	private List<UsuarioEntity> usuarios;

}
