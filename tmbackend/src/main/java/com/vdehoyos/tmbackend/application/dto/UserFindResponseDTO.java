package com.vdehoyos.tmbackend.application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserFindResponseDTO extends BaseUserDTO {

	private RoleDTO role;
	
}
