package com.vdehoyos.tmbackend.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum TaskStatus {
	PENDING("Pendiente"), 
	IN_PROGRESS("En Progreso"), 
	DONE("Completada");
	
	private final String displayName;
	
	@JsonValue
    public String getValue() {
        return displayName;
    }

    @JsonCreator
    public static TaskStatus fromString(String value) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Estado de tarea no válido: " + value);
    }
}
