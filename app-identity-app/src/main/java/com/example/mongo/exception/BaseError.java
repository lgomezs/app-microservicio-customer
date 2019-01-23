package com.example.mongo.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseError implements Serializable{
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private String title;
}
