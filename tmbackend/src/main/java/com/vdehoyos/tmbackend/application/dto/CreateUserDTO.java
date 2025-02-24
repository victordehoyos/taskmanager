package com.vdehoyos.tmbackend.application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateUserDTO extends BaseUserDTO {
	
	private Long roleId;

}
