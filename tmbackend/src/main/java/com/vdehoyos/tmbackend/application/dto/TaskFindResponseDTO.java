package com.vdehoyos.tmbackend.application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaskFindResponseDTO extends BaseTaskDTO {

	private Long id;
	private String status;
}
