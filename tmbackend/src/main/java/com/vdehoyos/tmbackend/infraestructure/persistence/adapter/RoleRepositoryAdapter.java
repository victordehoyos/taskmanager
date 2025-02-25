package com.vdehoyos.tmbackend.infraestructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vdehoyos.tmbackend.domain.model.Role;
import com.vdehoyos.tmbackend.domain.repository.RoleRepository;
import com.vdehoyos.tmbackend.infraestructure.persistence.entity.RoleEntity;
import com.vdehoyos.tmbackend.infraestructure.persistence.mapper.RoleEntityMapper;
import com.vdehoyos.tmbackend.infraestructure.persistence.repository.JpaRoleRepository;

@Repository
public class RoleRepositoryAdapter implements RoleRepository {

	private final JpaRoleRepository jpaRoleRepository;
	private final RoleEntityMapper roleEntityMapper;
	
	public RoleRepositoryAdapter(JpaRoleRepository jpaRoleRepository, RoleEntityMapper roleEntityMapper) {
		this.jpaRoleRepository = jpaRoleRepository;
		this.roleEntityMapper = roleEntityMapper;
	}

	@Override
	public Optional<Role> findById(Long id) {
		return jpaRoleRepository.findById(id).map(roleEntityMapper::toDomain);
	}

	@Override
	public Optional<Role> findByName(String name) {
		return jpaRoleRepository.findByName(name).map(roleEntityMapper::toDomain);
	}

	@Override
	public List<Role> findAll() {
		List<RoleEntity> roleEntities = jpaRoleRepository.findAll();
		return roleEntityMapper.toDomainList(roleEntities);
	}

}
