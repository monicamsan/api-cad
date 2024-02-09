package com.example.api.usuario.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.api.usuario.dto.LoginDTO;
import com.example.api.usuario.dto.UsuarioDTO;
import com.example.api.usuario.model.UsuarioEntity;
import com.example.api.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * Busca um Usuario por ID
	 * @param 
	 * @return
	 * 
	 * */
	public Optional<UsuarioDTO> buscaPorId(Long id) {
		Optional<UsuarioEntity> usuario = this.usuarioRepository.findById(id);

		if(usuario.isPresent()) {
			return Optional.of(modelMapper.map(usuario.get(), UsuarioDTO.class));
		}
		return Optional.empty();
	}


	/*
	 * Lista todos os usuarios
	 */

	public Optional<List<UsuarioDTO>> listaTodos() {
		List<UsuarioEntity> usuarios = this.usuarioRepository.findAll();
		if(!usuarios.isEmpty()) {
			return Optional.of(
					usuarios.stream()
					.map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
					.collect(Collectors.toList()));

		}
		return Optional.empty();
	}

	/*
	 * Salva e atualiza usuario
	 */

	public UsuarioDTO salva(UsuarioDTO usuarioDTO) {
		var usuario = new UsuarioEntity() ;
		try {

			usuario = modelMapper.map(usuarioDTO, UsuarioEntity.class);
			this.usuarioRepository.save(usuario);

		}catch (DataIntegrityViolationException e) {
			System.out.println("Não foi possível salvar este usuário");
		}

		return modelMapper.map(usuario, UsuarioDTO.class);

	}


	/*
	 * Salva exclui
	 */
	public Boolean exclui(Long id) {
		try {

			Optional<UsuarioEntity> usuario = this.usuarioRepository.findById(id);

			if(!usuario.isPresent()) {
				System.out.println("O usuário informado não existe");
				return false;
			}

			this.usuarioRepository.deleteById(id);


		}catch (DataIntegrityViolationException e) {
			System.out.println("Não foi possível excluir este usuário");
		}
		return true;

	}

	/*
	 * Login
	 */
	public Optional<UsuarioDTO> buscaPorUsuarioESenha(LoginDTO login) {
		UsuarioEntity usuario = null; 
		Optional<UsuarioEntity> result =  Optional.ofNullable(usuario);

		try {
           var nome = login.getNome();
           var senha = login.getSenha().trim();
			result =   this.usuarioRepository.findByNomeAndSenha(nome, senha);
			
			if(!result.isPresent()) {
				System.out.println("O usuário informado não existe");
				return Optional.empty();
			}

		}catch (Exception e) {
			System.out.println("Não foi possível consultar os dados");
		}
		return Optional.of(modelMapper.map(result.get(), UsuarioDTO.class));
	}
	

}
