package com.vdehoyos.tmbackend.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {

	private Long id;
	private String name;
	private String email;
	private Role role;
	private Date createdAt;
	private Date updatedAt;
	
	public boolean isValidEmail(String email) {
		return email.contains("@");
	}
	
}
