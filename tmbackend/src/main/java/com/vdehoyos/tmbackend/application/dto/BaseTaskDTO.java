package com.vdehoyos.tmbackend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseTaskDTO {
	
	private String title;
    private String description;
    private String priority;
    private BaseUserDTO user;
    private BaseUserDTO supervisor;

}
