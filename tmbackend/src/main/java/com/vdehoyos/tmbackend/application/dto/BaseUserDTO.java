package com.vdehoyos.tmbackend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class BaseUserDTO {

	@Getter @Setter
	private String name;
	@Getter @Setter
	private String email;
	
}
