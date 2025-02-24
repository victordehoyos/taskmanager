package com.vdehoyos.tmbackend.infraestructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vdehoyos.tmbackend.domain.model.Role;
import com.vdehoyos.tmbackend.domain.model.User;
import com.vdehoyos.tmbackend.domain.repository.UserRepository;
import com.vdehoyos.tmbackend.infraestructure.persistence.entity.UserEntity;
import com.vdehoyos.tmbackend.infraestructure.persistence.mapper.RoleEntityMapper;
import com.vdehoyos.tmbackend.infraestructure.persistence.mapper.UserEntityMapper;
import com.vdehoyos.tmbackend.infraestructure.persistence.repository.JpaUserRepository;

@Repository
public class UserRepositoryAdapter implements UserRepository {
	
	private final JpaUserRepository jpaUserRepository;
	private final UserEntityMapper userEntityMapper;
	private final RoleEntityMapper roleEntityMapper;
	
	public UserRepositoryAdapter(JpaUserRepository jpaUserRepository, UserEntityMapper userEntityMapper, RoleEntityMapper roleEntityMapper) {
		this.jpaUserRepository = jpaUserRepository;
		this.userEntityMapper = userEntityMapper;
		this.roleEntityMapper = roleEntityMapper;
	}

	@Override
	public User save(User user) {
		UserEntity entity = userEntityMapper.toEntity(user);
		return userEntityMapper.toDomain(jpaUserRepository.save(entity));
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findById(Long id) {
		return jpaUserRepository.findById(id).map(userEntityMapper::toDomain);
	}

	@Override
	public List<User> findByRole(Role role) {
		List<UserEntity> userEntities = jpaUserRepository.findByRole(roleEntityMapper.toEntity(role));
		return userEntityMapper.toDomainList(userEntities);
	}

	@Override
	public List<User> findAll() {
		List<UserEntity> userEntities = jpaUserRepository.findAll();
		return userEntityMapper.toDomainList(userEntities);
	}

	@Override
	public User findByEmail(String email) {
		return userEntityMapper.toDomain(jpaUserRepository.findByEmail(email));
	}

}
