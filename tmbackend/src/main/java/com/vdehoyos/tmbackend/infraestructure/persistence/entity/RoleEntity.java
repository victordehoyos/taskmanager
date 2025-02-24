package com.vdehoyos.tmbackend.infraestructure.persistence.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "role_name", nullable = false)
	private String name;
	
	@Column(name = "role_description", nullable = true)
	private String description;
	
	@OneToMany(mappedBy = "role")
	private Set<UserEntity> users;
	
}
