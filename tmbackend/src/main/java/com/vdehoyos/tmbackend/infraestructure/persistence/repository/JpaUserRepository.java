package com.vdehoyos.tmbackend.infraestructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vdehoyos.tmbackend.infraestructure.persistence.entity.RoleEntity;
import com.vdehoyos.tmbackend.infraestructure.persistence.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

	public List<UserEntity> findByRole(RoleEntity entity);
	public UserEntity findByEmail(String email);
	
}
