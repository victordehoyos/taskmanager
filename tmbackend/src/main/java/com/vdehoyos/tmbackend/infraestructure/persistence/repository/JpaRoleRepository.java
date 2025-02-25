package com.vdehoyos.tmbackend.infraestructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vdehoyos.tmbackend.infraestructure.persistence.entity.RoleEntity;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
	
	Optional<RoleEntity> findByName(String name);

}
