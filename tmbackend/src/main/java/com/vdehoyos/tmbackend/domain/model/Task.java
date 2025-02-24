package com.vdehoyos.tmbackend.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

	private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private User user;
    private User supervisor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	
}
