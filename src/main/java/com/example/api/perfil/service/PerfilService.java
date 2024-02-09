package com.example.api.perfil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.api.perfil.dto.PerfilDTO;
import com.example.api.perfil.model.PerfilEntity;
import com.example.api.perfil.repository.PerfilRepository;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * Busca um Perfil por ID
	 * @param 
	 * @return
	 * 
	 * */
	public Optional<PerfilDTO> buscaPorId(Long id) {
		Optional<PerfilEntity> perfil = this.perfilRepository.findById(id);

		if(perfil.isPresent()) {
			return Optional.of(modelMapper.map(perfil.get(), PerfilDTO.class));
		}
		return Optional.empty();
	}


	/*
	 * Lista todos os perfils
	 */

	public Optional<List<PerfilDTO>> listaTodos() {
		List<PerfilEntity> perfils = this.perfilRepository.findAll();
		if(!perfils.isEmpty()) {
			return Optional.of(
					perfils.stream()
					.map(perfil -> modelMapper.map(perfil, PerfilDTO.class))
					.collect(Collectors.toList()));

		}
		return Optional.empty();
	}

	/*
	 * Salva e atualiza perfil
	 */

	public PerfilDTO salva(PerfilDTO perfilDTO) {
		var perfil = new PerfilEntity() ;
		try {
			
			perfil = modelMapper.map(perfilDTO, PerfilEntity.class);
			this.perfilRepository.save(perfil);
			
		}catch (DataIntegrityViolationException e) {
			System.out.println("Não foi possível salvar este usuário");
		}

		return modelMapper.map(perfil, PerfilDTO.class);
		
	}


	/*
	 * Salva exclui
	 */
	public Boolean exclui(Long id) {
		try {
			
			Optional<PerfilEntity> perfil = this.perfilRepository.findById(id);
			
			if(!perfil.isPresent()) {
				System.out.println("O usuário informado não existe");
				return false;
			}
			
			this.perfilRepository.deleteById(id);
			
			
		}catch (DataIntegrityViolationException e) {
			System.out.println("Não foi possível excluir este usuário");
		}
		return true;

	}

}
