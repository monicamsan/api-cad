package com.example.api.perfil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.perfil.model.PerfilEntity;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
}
