package com.example.mongo.util;

import lombok.Getter;


@Getter
public enum ErrorType {
	ERROR_GENERIC("999999","Error","Ups, ocurrio un error inesperado.");
	
	private String code;
	private String title;
	private String message;
	private ErrorType(String code,String title, String message) {
		this.code = code;
		this.title = title;
		this.message = message;
	}	
	
}
