package com.vdehoyos.tmbackend.domain.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum TaskPriority {
	ALTA("Alta"),
    MEDIA("Media"),
    BAJA("Baja");
    
    private final String displayName;
}
