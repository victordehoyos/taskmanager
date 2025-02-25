package com.vdehoyos.tmbackend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
	
	private Long roleId;
	private String roleName;
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String email;
}
