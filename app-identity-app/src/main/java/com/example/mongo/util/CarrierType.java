package com.example.mongo.util;

import lombok.Getter;

@Getter
public enum CarrierType {
	MOVISTAR("MOVISTAR"),
	CLARO("CLARO"),
	ENTEL("ENTEL"),
	BITEL("BITEL");
	
	private String name;
	
	private CarrierType(String name) {
		this.name = name;
	}
	
}
